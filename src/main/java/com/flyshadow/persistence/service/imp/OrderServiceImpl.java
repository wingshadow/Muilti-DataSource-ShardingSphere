package com.flyshadow.persistence.service.imp;

import com.flyshadow.common.AbstractBaseServiceImpl;
import com.flyshadow.persistence.entity.Order;
import com.flyshadow.persistence.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: OrderServiceImpl
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 14:42
 */
@Service("orderService")
public class OrderServiceImpl extends AbstractBaseServiceImpl implements OrderService {
    @Override
    public List<Order> list(Order order) {
        return getSlaveSesson().selectList(sqlId("list",
                Order.class),order);
    }

    @Override
    public void inert(Order order) {
        getMasterSession().insert(sqlId("insert", Order.class), order);
    }
}
