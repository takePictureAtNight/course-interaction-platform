package com.peking.courseresourse.controller;

import annotation.Authority;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peking.courseresourse.entity.RoleEntity;
import com.peking.courseresourse.entity.RoleHasPermissionsEntity;
import com.peking.courseresourse.entity.Tree;
import com.peking.courseresourse.entity.UserHasRolesEntity;
import com.peking.courseresourse.service.RoleHasPermissionsService;
import com.peking.courseresourse.service.RoleService;
import com.peking.courseresourse.service.UserHasRolesService;
import com.peking.courseresourse.vo.AssignRoleVo;
import com.peking.courseresourse.vo.AuthorityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 系统角色
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@RestController
@RequestMapping("courseresourse/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleHasPermissionsService rolePermissionService;

    @Autowired
    private UserHasRolesService userRoleService;

    @GetMapping("/treeList")
    @Authority("permission:treeList")
    public List<Tree> treeList(){
        List<com.peking.courseresourse.entity.Tree> data = roleService.tree();
        return data;
    }

    /**
     * 给用户分配角色
     * 给用户分配角色前把之前的角色都删了
     * @param assignRoleVO
     * @return
     */
    @PostMapping("/assignRole")
    @Authority("user:assignRole")
    public R assignRole(@RequestBody AssignRoleVo assignRoleVO){
        Integer uId = assignRoleVO.getUId();
        userRoleService.remove(new QueryWrapper<UserHasRolesEntity>().eq("u_id",uId));
        List<UserHasRolesEntity> userRoles = new ArrayList<>();
        for (Integer id : assignRoleVO.getRId()) {
            UserHasRolesEntity userRole = new UserHasRolesEntity();
            userRole.setUId(uId);
            userRole.setRId(id);
            userRoles.add(userRole);
        }

        userRoleService.saveBatch(userRoles);
        return R.ok();
    }

    /**
     * 获取用户角色
     * @param id
     * @return
     */
    @GetMapping("/getRole/{id}")
    @Authority("role:getRole")
    public List<Integer> getRole(@PathVariable Integer id){
        List<Integer> list = userRoleService.list(new QueryWrapper<UserHasRolesEntity>().eq("u_id", id).select("r_id"))
                .stream().map(UserHasRolesEntity::getRId).collect(Collectors.toList());
        return list;
    }

    /**
     * 初始化角色
     * @return
     */
    @GetMapping("/initRole")
    @Authority("role:initRole")
    public List<Map<String,Object>> initRole(){

        // 查出所有角色
        List<Map<String, Object>> list = roleService.list().stream()
                .map(role -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("value", role.getId());
                    data.put("title", role.getName());
                    return data;
                }).collect(Collectors.toList());

        return list;
    }


    /**
     * 角色列表
     * @param current
     * @param limit
     * @param name
     * @return
     */
    @GetMapping("/list")
    @Authority("role:list")
    public R list(@RequestParam(value = "page",defaultValue = "1") Long current,
                  @RequestParam(value = "limit",defaultValue = "15") Long limit,
                  @RequestParam(required = false) String name){
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.like(!ObjectUtils.isEmpty(name),"name",name);
        Page<RoleEntity> page = roleService.page(new Page<>(current, limit), wrapper);

        return R.ok().data("data",page.getRecords()).count(page.getTotal());
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/add")
    @Authority("role:add")
    public R add(@RequestBody RoleEntity role){

        roleService.save(role);
        return R.ok();
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PutMapping("/update")
    @Authority("role:update")
    public R update(@RequestBody RoleEntity role){

        roleService.updateById(role);
        return R.ok();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Authority("role:delete")
    public R delete(@PathVariable String id){
        rolePermissionService.remove(new QueryWrapper<RoleHasPermissionsEntity>().eq("r_id",id));
        roleService.removeById(id);
        return R.ok();
    }


    /**
     * 给角色分配权限
     * @param authorityVO
     * @return
     * 给角色分配权限前先把该角色的权限都删了
     */
    @PostMapping("/authority")
    @Authority("role:authority")
    public R authority(@RequestBody AuthorityVo authorityVO){
        rolePermissionService.remove(new QueryWrapper<RoleHasPermissionsEntity>().eq("r_id",authorityVO.getRid()));
        List<RoleHasPermissionsEntity> list = new ArrayList<>();
        Integer rid = authorityVO.getRid();
        for (Integer pId : authorityVO.getPid()) {
            RoleHasPermissionsEntity rolePermission = new RoleHasPermissionsEntity();
            rolePermission.setRId(rid);
            rolePermission.setPId(pId);
            list.add(rolePermission);
        }

        rolePermissionService.saveBatch(list);

        return R.ok();
    }

    /**
     * 获取角色权限
     * @param id
     * @return
     */
    @GetMapping("/getPermission/{id}")
    @Authority("role:getPermission")
    public Integer[] getPermission(@PathVariable Integer id){
        Integer[] list = rolePermissionService.list(new QueryWrapper<RoleHasPermissionsEntity>().eq("r_id", id).select("p_id"))
                .stream().map(RoleHasPermissionsEntity::getPId).toArray(Integer[]::new);

        return list;
    }

}
