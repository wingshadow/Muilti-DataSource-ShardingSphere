package com.flyshadow.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Title: Order
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/14 14:54
 */
@Getter
@Setter
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = 1197522265106543785L;
    private Integer orderId;
    private Integer userId;
}
