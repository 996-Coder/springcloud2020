package com.practice.springcloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseObject implements Serializable {
    String code;
    String status;
    Object body;
}
