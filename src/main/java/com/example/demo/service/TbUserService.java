package com.example.demo.service;

import com.example.demo.entity.TbUser;
import com.example.demo.utils.PageUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Zch
 **/
public interface TbUserService {

    /**
     * 根据用户id返回用户权限集合
     * @param userId 用户id
     * @return Set<String> 权限集合
     * */
    public Set<String> searchUserPermissions(int userId);

    /**
     * 根据用户id返回用户权限集合
     * @param userId 用户id
     * @return Set<String> 权限集合
     * */
    public HashMap searchById(int userId);

    /**
     * 登录
     * @param param 用户名和密码
     * @return 登陆与否
     * */
    public Integer login(HashMap param);

    /**
     * 更新用户密码
     * @param param 旧密码和新密码
     * @return 修改密码成功与否
     * */
    public int updatePassword(HashMap param);

    /**
     * 根据查询信息返回用户分页信息
     * @param param 要查询的信息
     * @return PageUtils 返回用户分页数据
     * */
    public PageUtils searchUserByPage(HashMap param);

    /**
     * 插入用户信息
     * @param user 要插入的用户信息
     * @return 插入用户成功与否
     * */
    public int insert(TbUser user);

    /**
     * 更新用户信息
     * @param param 更新的用户信息
     * @return 更新成功与否
     */
    public int update(HashMap param);

    /**
     * 根据用户id删除用户
     * @param ids 用户id
     * @return 删除成功与否
     * */
    public int deleteUserByIds(Integer[] ids);
}
