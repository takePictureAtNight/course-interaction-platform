package com.peking.courseresourse.service.impl;


import com.peking.courseresourse.entity.Tree;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.entity.RoleEntity;
import com.peking.courseresourse.mapper.RoleMapper;
import com.peking.courseresourse.service.PermissionService;
import com.peking.courseresourse.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private PermissionService permissionService;


    @Override
    public List<Tree> tree() {
        List<Tree> data = new ArrayList<>();

        List<Tree> trees = new ArrayList<>();

        permissionService.list().stream()
                .forEach(permission -> {
                    Tree tree = new Tree();
                    BeanUtils.copyProperties(permission,tree);
                    tree.setTitle(permission.getName());
                    tree.setSpread(true);
                    trees.add(tree);
                });

        // 找到根节点
        for (Tree tree : trees) {
            if (tree.getPId() == 0) {
                tree.setChildren(new ArrayList<Tree>());
                data.add(tree);
            }
        }

        for (Tree datum : data) {
            datum.getChildren().add(findChildren(datum,trees));
        }

        return data;
    }

    private Tree findChildren(Tree datum, List<Tree> trees) {
        datum.setChildren(new ArrayList<Tree>());
        for (Tree tree : trees) {
            if (tree.getPId() == datum.getId()) {
                datum.getChildren().add(findChildren(tree,trees));
            }
        }
        return datum;
    }
}