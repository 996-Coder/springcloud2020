package com.practice.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalance implements ILoadBalance {
    // 定义访问次数，从0开始
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getAndIncrement() {
        int current, next;
        do {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, next));
        System.out.println("第" + next + "次访问。。。");
        return next;
    }

    /**
     * 根据传入的多个service实例，返回这次去调用的实例
     *
     * @param serviceInstances 根据服务名拿到的服务实例列表
     * @return 返回根据自定义轮询算法得出这次将会去调用的服务实例
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
