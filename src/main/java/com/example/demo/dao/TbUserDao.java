package com.example.demo.dao;

import com.example.demo.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Zch
 **/
@Mapper
public interface TbUserDao {

    /**
     * 该方法根据用户的id查找用户的权限集合
     * @param userId int 用户id
     * @return Set<String> 权限集合
     * */
    public Set<String> searchUserPermissions(int userId);

    /**
     * 该方法根据用户的id查找用户
     * @param userId int 用户id
     * @return HashMap
     * */
    public HashMap searchById(int userId);

    /**
     * 该方法根据用户输入的用户名密码 登录
     * @param param 一个hashmap集合，{"username": "xxx", "password": "xxx"}
     * @return Integer 登陆成功与否
     * */
    public Integer login(HashMap param);

    /**
     * 该方法根据用户旧密码与新密码更改密码
     * @param param hashmap集合，包含用户原密码以及新密码
     * @return int 更改成功与否
     * */
    public int updatePassword(HashMap param);

    /**
     * 该方法返回用户信息 分页查询
     * @param param hashmap 根据用户输入的各种信息查找用户信息分页，模糊查询以及分页数据查询
     * @return ArrayList<HashMap> 用户信息列表
     * */
    public ArrayList<HashMap> searchUserByPage(HashMap param);

    /**
     * 该方法返回用户数量
     * @param param 输入的用户信息
     * @return long 用户信息数量
     * */
    public long searchUserCount(HashMap param);

    /**
     * 该方法用于插入用户信息
     * @param user 要插入的用户信息
     * @return int 成功插入与否
     * */
    public int insert(TbUser user);

    /**
     * 该方法用户更新用户信息
     * @param param 要更新的用户信息
     * @return int 更新成功与否
     * */
    public int update(HashMap param);

    /**
     * 该方法通过用户id删除用户
     * @param ids 要删除的用户
     * @return 删除成功与否
     * */
    public int deleteUserByIds(Integer[] ids);
}
