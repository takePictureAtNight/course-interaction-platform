package courseresourse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import courseresourse.entity.UserEntity;
import courseresourse.service.UserService;
import utils.PageUtils;
import utils.R;


/**
 * 
 *
 * @author yy
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 19:46:31
 */
@RestController
@RequestMapping("courseresourse/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		userService.removeById(id);
        return R.ok();
    }

}
