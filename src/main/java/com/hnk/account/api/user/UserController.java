package com.hnk.account.api.user;

import com.hnk.account.api.BaseController;
import com.hnk.account.core.model.ApiResponse;
import com.hnk.account.dao.user.model.User;
import com.hnk.account.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 示例Controller
 * @author 懒猴子CG
 * @date 2020/05/25 16:04
 */
@RestController
@RequestMapping("/user")
@Api(tags = "示例接口")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户管理页面
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    @GetMapping("/manage")
    public ModelAndView manage() {
        ModelAndView mav = new ModelAndView("user/manage");
        User req = new User();
        req.setIsDelete(Boolean.FALSE);
        List<User> users = userService.findList(req);
        mav.addObject("userList", users);
        return mav;
    }

    /**
     * 创建
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    @PostMapping("/create")
    @ApiOperation("创建")
    public ApiResponse<User> create(User req) {
        return ApiResponse.success(userService.create(req));
    }

    /**
     * 通过id查询
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询")
    public ApiResponse<User> finById(@PathVariable Integer id) {
        return ApiResponse.success(userService.findById(id));
    }

    /**
     * 根据ID修改
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    @PostMapping("/updateById")
    @ApiOperation("根据ID修改")
    public ApiResponse<User> updateById(User req) {
        userService.updateById(req);
        return ApiResponse.success(null);
    }

    /**
     * 根据ID删除
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    @GetMapping("/delete/{id}")
    @ApiOperation("根据ID删除")
    public ApiResponse delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ApiResponse.success(null);
    }
}