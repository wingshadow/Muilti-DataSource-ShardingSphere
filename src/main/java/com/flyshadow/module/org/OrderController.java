package com.flyshadow.module.org;


import com.flyshadow.persistence.entity.Order;
import com.flyshadow.persistence.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Title: OrderController
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 14:27
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "/order/add",method = RequestMethod.GET)
    public String add(){
        Order order = new Order();
        order.setOrderId(1);
        order.setUserId(1);
        orderService.inert(order);
        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setUserId(2);
        orderService.inert(order2);
        return "success";
    }

    @RequestMapping(path = "/order/list", method = {RequestMethod.GET})
    public List<Order> getOrderList() {
        Order order = new Order();
        order.setOrderId(1);
        return orderService.list(order);
    }
}

