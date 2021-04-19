package com.cloud.springcloud.alibaba.controller;

import com.cloud.springcloud.entities.CommonResult;
import com.cloud.springcloud.entities.Payment;
import com.cloud.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入结果" + result);
        if (result > 0 ){
            return new CommonResult(200, "插入数据库成功！"+ serverPort,  result);
        }else{
            return new CommonResult(444, "插入数据库失败！", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);

        log.info("******查询结果" + paymentById);
        if (paymentById != null ){
            return new CommonResult(200, "查询数据库成功！" + serverPort, paymentById);
        }else{
            return new CommonResult(444, "查询数据库失败！" + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("*********" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;

    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
