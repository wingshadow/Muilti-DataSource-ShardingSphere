package com.flyshadow.persistence.service;

import com.flyshadow.persistence.entity.Order;

import java.util.List;

/**
 * @Title: OrderService
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/14 15:13
 */
public interface OrderService {

    List<Order> list(Order order);

    void inert(Order order);
}
