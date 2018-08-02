package com.test.web.dao.mybatis;

import com.test.web.model.User;
import com.test.web.model.UserCriteria;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, User, UserCriteria, String> {
}