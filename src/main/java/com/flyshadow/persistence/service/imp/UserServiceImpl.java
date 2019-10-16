package com.flyshadow.persistence.service.imp;

import com.flyshadow.common.AbstractBaseServiceImpl;
import com.flyshadow.persistence.entity.User;
import com.flyshadow.persistence.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: UserServiceImp
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 14:42
 */
@Service("userService")
public class UserServiceImpl extends AbstractBaseServiceImpl implements UserService {
    @Override
    public List<User> list() {
        return getMasterSession().selectList(sqlId("list", User.class));
    }

    @Override
    public void inert(User user) {
        getMasterSession().insert(sqlId("insert", User.class), user);
    }
}
