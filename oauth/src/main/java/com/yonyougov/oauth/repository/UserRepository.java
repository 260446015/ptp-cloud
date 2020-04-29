package com.yonyougov.oauth.repository;

import com.yonyougov.oauth.entity.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/26
 */
public interface UserRepository extends CrudRepository<Users,Long> {

    Users findByUsernameAndPassword(String username, String password);
}
