package com.hnk.account.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnk.account.core.model.PageData;
import com.hnk.account.core.utils.MyBatisUtil;
import com.hnk.account.dao.user.UserMapper;
import com.hnk.account.dao.user.model.User;
import com.hnk.account.dao.user.model.UserExample;
import com.hnk.account.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 示例Service实现
 * @author naikuoh
 * @date 2020/05/25 16:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer create(User user) {
        userMapper.insertSelective(user);
        return user.getId();
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIdInBatch(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return;
        for (Integer id: ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void updateById(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateByIdInBatch(List<User> users) {
        if (CollectionUtils.isEmpty(users)) return;
        for (User user: users) {
            this.updateById(user);
        }
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findOne(User user) {
        UserExample example = MyBatisUtil.toExample(user, UserExample.class);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> findList(User user) {
        UserExample example = MyBatisUtil.toExample(user, UserExample.class);
        return userMapper.selectByExample(example);
    }

    @Override
    public PageData<User> findPage(User user, PageData page, String orderByClause) {
        PageHelper.startPage(page.getPage(), page.getCapacity());
        UserExample example = MyBatisUtil.toExample(user, UserExample.class);
        example.setOrderByClause(orderByClause);
        return PageData.from(new PageInfo(userMapper.selectByExample(example)));
    }

    @Override
    public long count(User user) {
        UserExample example = MyBatisUtil.toExample(user, UserExample.class);
        return userMapper.countByExample(example);
    }
}