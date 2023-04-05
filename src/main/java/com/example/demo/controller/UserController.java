package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.hash.Hash;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.controller.form.*;
import com.example.demo.entity.TbUser;
import com.example.demo.service.TbUserService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Zch
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private TbUserService tbUserService;

    /**
     * 根据用户id查找用户
     * */
    @PostMapping("/searchById")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public ResponseUtils searchById(@Valid @RequestBody SearchUserByIdForm form) {
        HashMap map = tbUserService.searchById(form.getUserId());
        return ResponseUtils.ok(map);
    }

    /**
     * 用户登录接口
     * */
    @PostMapping("/login")
    public ResponseUtils login(@Valid @RequestBody LoginForm form) {
        // 转为json对象再转为javabean，通过javabean获得需要的属性
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        // 调用login方法拿到登录用户的id
        Integer userId = tbUserService.login(param);
        // 返回true or false
        ResponseUtils responseUtils = ResponseUtils.ok().put("result", userId != null);
        if (userId != null) {
            // 调用工具类登录方法
            StpUtil.login(userId);
            // 获得 token信息 传去前端
            SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
            // 得到通过登录用户id登录用户的权限
            Set<String> permissions = tbUserService.searchUserPermissions(userId);
            responseUtils.put("permissions", permissions);
            responseUtils.put("token", saTokenInfo.tokenValue);
        }
        // 这里传的值有result，permissions，token
        return responseUtils;
    }

    /**
     * 退出
     * */
    @GetMapping("/logout")
    public ResponseUtils logout() {
        // 直接调用工具类的logout方法即可
        StpUtil.logout();
        return ResponseUtils.ok();
    }

    /**
     *修改密码
     * */
    public ResponseUtils updatePassword(@Valid @RequestBody UpdatePasswordForm form) {
        // 这里使用工具类获取前面登录传上去的用户id
        int userId = StpUtil.getLoginIdAsInt();
        HashMap param = new HashMap () {{
            put("userId", userId);
            put("password", form.getPassword());
        }};
        // 修改密码
        int row = tbUserService.updatePassword(param);
        return ResponseUtils.ok().put("row", row);
    }

    /**
     * 分页查询用户数据
     * */
    @PostMapping("/searchUserByPage")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public ResponseUtils searchUserByPage(@Valid @RequestBody SearchUserByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start", start);
        PageUtils pageUtils = tbUserService.searchUserByPage(param);
        return ResponseUtils.ok().put("page", pageUtils);
    }

    /**
     * 添加用户
     * */
    @PostMapping("/insert")
    public ResponseUtils insert(@Valid @RequestBody InsertUserForm form) {
        TbUser user = JSONUtil.parse(form).toBean(TbUser.class);
        // 补全数据，status在职 role需要转换一下数据类型（转换为json数组且toString），createTime就现在的时间
        user.setStatus((byte) 1);
        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        user.setCreateTime(new Date());
        int rows = tbUserService.insert(user);
        return ResponseUtils.ok().put("rows", rows);
    }

    /**
     * 更新用户信息
     * */
    @PostMapping("/update")
    public ResponseUtils update(@Valid @RequestBody UpdateUserForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.replace("role", JSONUtil.parseArray(form.getRole()).toString());
        int rows = tbUserService.update(param);
        if (rows == 1) {
            // 修改用户之后需要将指定用户注销登录
            StpUtil.logout(form.getUserId());
        }
        return ResponseUtils.ok().put("rows", rows);
    }

    /**
     * 根据用户id删除用户
     * */
    public ResponseUtils deleteUserByIds(@Valid @RequestBody DeleteUserByIdForm form) {
        Integer userId = StpUtil.getLoginIdAsInt();
        if (ArrayUtil.contains(form.getIds(), userId)) {
            return ResponseUtils.error("您不能删除自己的帐户");
        }
        int rows = tbUserService.deleteUserByIds(form.getIds());
        if (rows > 0) {
            for (Integer id : form.getIds()) {
                StpUtil.logout(id);
            }
        }
        return ResponseUtils.ok().put("rows", rows);

    }
}
