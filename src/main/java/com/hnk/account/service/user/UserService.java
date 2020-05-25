package com.hnk.account.service.user;

import com.hnk.account.core.model.PageData;
import com.hnk.account.dao.user.model.User;
import java.util.List;

/**
 * 示例Service定义
 * @author 懒猴子CG
 * @date 2020/05/25 16:04
 */
public interface UserService {

    /**
     * 创建
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    Integer create(User user);

    /**
     * 主键删除
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    void deleteById(Integer id);

    /**
     * 批量主键删除
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    void deleteByIdInBatch(List<Integer> ids);

    /**
     * 主键更新
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    void updateById(User user);

    /**
     * 批量主键更新
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    void updateByIdInBatch(List<User> users);

    /**
     * 主键查询
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    User findById(Integer id);

    /**
     * 条件查询单条记录
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    User findOne(User user);

    /**
     * 条件查询
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    List<User> findList(User user);

    /**
     * 分页查询
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    PageData<User> findPage(User user, PageData page, String orderByClause);
    /**
     * 条件统计
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    long count(User user);
}