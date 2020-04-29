package com.practice.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface ILoadBalance {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
