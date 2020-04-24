package com.practice.springcloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Payment implements Serializable {
    private String id;
    private String serial;
}
