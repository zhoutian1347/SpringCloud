package com.tedu.sp09.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tedu.sp01.pojo.Order;
import com.tedu.sp09.serviceImpl.OrderFeighServiceFB;
import com.tedu.web.util.JsonResult;

@FeignClient(name = "order-service",fallback = OrderFeighServiceFB.class)
public interface OrderFeignService {

	/**
	 * 要在接口和方法上设置调用的后台服务，以及调用的后台服务路径
	 * 
	 * http://locahost:8001/orderId
	 * http://locahost:8002/orderId
	 * 
	 * @FeignClient(name = "item-service")
	 * 指定要访问哪个后台服务，可以获得指定的服务的主机地址
	 * http://localhost:8001
	 * http://localhost:8002
	 * 
	 * @GetMapping("/{orderId}")
	 * feign利用springMVC注解来确定访问路径
	 * http://localhost:8001/{orderId}
	 * 
	 * @PathVariable 把路径中的占位符替换成具体的参数数据
	 * http://localhost:8001/abcdesss
	 */
	
	@GetMapping("/{orderId}")
	JsonResult<Order> getOrder(@PathVariable String orderId);
	
	@GetMapping("/")
	JsonResult addOrder();
	
	
}
