# 替换Ribbon默认的负载策略
注意: 自定义的Rlue配置类不放在@ComponentScan注解所扫描的当前包及其子包下，否则自定义的配置类就会被所有的Ribbon客户端共享，也就达不到特殊制定的目的了


需要在启动类所在的包外面新建一个包  
添加配置类
```java
@Configuration
public class RandomRule {
    @Bean
    public IRule randomRule(){
        // 返回想要用的负载算法
        return new com.netflix.loadbalancer.RandomRule();
    }
}
```
在启动类上添加注解@RibbonClient
```java
/**
* name: 要调用的服务名
* configuration: 要使用的负载算法
*/
import com.practice.ribbon.rule.MyRule;@RibbonClient(name = "payment-service", configuration = MyRule.class)
```