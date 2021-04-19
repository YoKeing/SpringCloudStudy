package com.cloud.springcloud.alibaba.controller;

import com.cloud.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String s = paymentHystrixService.paymentInfo_OK(id);
        return s;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String s = paymentHystrixService.paymentInfo_TimeOut(id);
        return s;
    }

    public String paymentInfoTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请稍后再试";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试！";
    }



}
