package com.test.web.service;

import com.github.pagehelper.PageInfo;
import com.test.web.model.User;
import com.test.web.model.UserCriteria;
import java.util.List;
import org.mybatis.generator.plugins.my.PageView;

public interface UserService {
    long countByExample(UserCriteria userCriteria);

    User selectByPrimaryKey(String userName);

    List<User> selectByExample(UserCriteria userCriteria);

    int deleteByPrimaryKey(String userName);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    int deleteByExample(UserCriteria userCriteria);

    int updateByExampleSelective(User user, UserCriteria userCriteria);

    int insert(User user);

    int insertSelective(User user);

    int insertBatch(List<User> userList);

    int insertBatchSelective(List<User> userList);

    PageView<User> selectByPageExample(int pageNum, int pageSize, UserCriteria userCriteria, boolean distinct, String orderByClause);

    PageInfo<User> selectByPageHelperExample(int pageNum, int pageSize, UserCriteria userCriteria);
}