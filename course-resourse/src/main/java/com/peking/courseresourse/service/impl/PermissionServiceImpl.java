package com.peking.courseresourse.service.impl;

import authority.AuthorityUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.PermissionDao;
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.PermissionService;
import com.peking.courseresourse.service.RoleHasPermissionsService;
import com.peking.courseresourse.service.UserHasRolesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    private UserHasRolesService userRoleService;

    @Autowired
    private RoleHasPermissionsService rolePermissionService;



    @Override
    public Map<String, Object> toTree(HttpServletRequest request) {


        // 创建返回结果map
        Map<String, Object> data = new HashMap<>();
        List<Menu> menus = new ArrayList<>();
        List<Menu> parentMenu = new ArrayList<>();

        // 封装权限集合
        Set<String> set = new HashSet<>();

        // 根据当期用户获取菜单
        String uId = JwtUtils.getMemberIdByJwtToken(request);

        // 根据用户id查询对应的角色id
        List<Integer> rIds = userRoleService.list(new QueryWrapper<UserHasRolesEntity>().eq("u_id",uId).select("r_id")).stream().map(UserHasRolesEntity::getRId).collect(Collectors.toList());

        // 根据角色查询对应的权限id
        List<Integer> pIds = rolePermissionService.list(new QueryWrapper<RoleHasPermissionsEntity>().in("r_id", rIds).select("p_id")).stream().map(RoleHasPermissionsEntity::getPId).collect(Collectors.toList());

        // 根据权限id查出权限
        // 查出所有权限-->转成对应的菜单对象
        permissionService.list(new QueryWrapper<PermissionEntity>().in("id",pIds))
                .stream()
                .forEach(permission -> {
                    Menu menu = new Menu();
                    BeanUtils.copyProperties(permission,menu);
                    menu.setTitle(permission.getName());
                    menus.add(menu);
                });
        // list转树形结构
        // 1. 先找到根节点
        for (Menu menu : menus) {
            if (menu.getPId() == 0) {
                menu.setChild(new ArrayList<Menu>());
                parentMenu.add(menu);
            }
        }

        // 根据根节点找到子节点
        for (Menu menu : parentMenu) {
            menu.getChild().add(findChild(menu,menus,set));
        }

        // 保存用户权限
        AuthorityUtils.setAuthority(uId,set);
        MenuKey menuKey1 = new MenuKey();
        MenuKey menuKey2 = new MenuKey();
        menuKey1.setTitle("首页");
        menuKey1.setHref("page/welcome.html?t=1");
        menuKey2.setTitle("后台管理");
        menuKey2.setImage("images/logo.png");
        data.put("menuInfo",parentMenu);
        data.put("homeInfo",menuKey1);
        data.put("logoInfo",menuKey2);
        return data;
    }

    @Override
    public List<PermissionEntity> treeSelect() {

        // 创建返回结果
        List<PermissionEntity> data = new ArrayList<>();

        // 查出所有权限 非按钮
        List<PermissionEntity> permissions = permissionService.list(new QueryWrapper<PermissionEntity>().ne("is_menu", 1));

        // 找到根节点
        for (PermissionEntity permission : permissions) {
            if (permission.getPId() == 0) {
                permission.setChildren(new ArrayList<PermissionEntity>());
                data.add(permission);
            }
        }

        // 根据根节点找到子节点
        for (PermissionEntity datum : data) {
            datum.getChildren().add(findTreeSelectChildren(datum,permissions));
        }

        return data;
    }


    @Override
    @Transactional
    public void removeMenu(Integer id) {
        // 封装id集合
        List<Integer> ids = new ArrayList<>();
        findPermissionId(id,ids);
        ids.add(id);
        permissionService.removeByIds(ids);
        rolePermissionService.remove(new QueryWrapper<RoleHasPermissionsEntity>().in("p_id",ids));

    }

    private void findPermissionId(Integer id, List<Integer> ids) {

        permissionService.list(new QueryWrapper<PermissionEntity>().eq("p_id",id).select("id"))
                .stream()
                .forEach(permission -> {
                    ids.add(permission.getId());
                    findPermissionId(permission.getId(),ids);
                });
    }


    private PermissionEntity findTreeSelectChildren(PermissionEntity datum, List<PermissionEntity> permissions) {

        datum.setChildren(new ArrayList<PermissionEntity>());
        for (PermissionEntity permission : permissions) {
            if (datum.getId() == permission.getPId()) {
                datum.getChildren().add(findTreeSelectChildren(permission,permissions));
            }
        }
        return datum;
    }


    private Menu findChild(Menu menu, List<Menu> menus,Set<String> set) {

        menu.setChild(new ArrayList<Menu>());
        for (Menu m : menus) {
            if (!ObjectUtils.isEmpty(m.getPath())){
                set.add(m.getPath());
            }
            if (m.getIsMenu()!=1){
                if (menu.getId() == m.getPId() ) {
                    // 递归调用该方法
                    menu.getChild().add(findChild(m,menus,set));
                }
            }
        }


        return menu;
    }

}