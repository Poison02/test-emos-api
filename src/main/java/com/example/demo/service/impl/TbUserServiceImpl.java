package com.example.demo.service.impl;

import com.example.demo.dao.TbUserDao;
import com.example.demo.entity.TbUser;
import com.example.demo.service.TbUserService;
import com.example.demo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Zch
 **/
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    TbUserDao tbUserDao;

    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permissions = tbUserDao.searchUserPermissions(userId);
        return permissions;
    }

    @Override
    public HashMap searchById(int userId) {
        HashMap map = tbUserDao.searchById(userId);
        return map;
    }

    @Override
    public Integer login(HashMap param) {
        Integer userId = tbUserDao.login(param);
        return userId;
    }

    @Override
    public int updatePassword(HashMap param) {
        int row = tbUserDao.updatePassword(param);
        return row;
    }

    @Override
    public PageUtils searchUserByPage(HashMap param) {
        ArrayList<HashMap> list = tbUserDao.searchUserByPage(param);
        long count = tbUserDao.searchUserCount(param);
        int start = (Integer) param.get("start");
        int length = (Integer) param.get("length");
        PageUtils pageUtils = new PageUtils(count, length, start, list);
        return pageUtils;
    }

    @Override
    public int insert(TbUser user) {
        int row = tbUserDao.insert(user);
        return row;
    }

    @Override
    public int update(HashMap param) {
        int row = tbUserDao.update(param);
        return row;
    }

    @Override
    public int deleteUserByIds(Integer[] ids) {
        int row = tbUserDao.deleteUserByIds(ids);
        return row;
    }
}
