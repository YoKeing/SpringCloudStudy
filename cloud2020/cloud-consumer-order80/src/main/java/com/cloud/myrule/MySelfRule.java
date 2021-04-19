package com.cloud.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Choisaaaa on 2018/7/9.
 * 自定义的轮询算法的配置类
 */
@Configuration
public class MySelfRule {



    @Bean //修改轮询规则为随机
    public void iRule() {

    }
//        return new RandomRule();
//    }
}
