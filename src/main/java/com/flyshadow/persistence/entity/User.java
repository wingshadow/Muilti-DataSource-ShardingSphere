package com.flyshadow.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Title: User
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/14 16:20
 */
@Setter
@Getter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -7847278484364360588L;
    private Integer id;
    private String name;
}
