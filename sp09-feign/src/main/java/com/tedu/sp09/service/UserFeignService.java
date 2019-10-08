package com.tedu.sp09.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tedu.sp09.serviceImpl.UserFeignServiceFB;
import com.tedu.web.util.JsonResult;

@FeignClient(name = "user-service",fallback = UserFeignServiceFB.class)
public interface UserFeignService {

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
	
	//如果请求参数和方法参数同名，@RequestParam可省略
	@GetMapping("/{userId}")
	JsonResult getUser(@PathVariable Integer userId);
	
	//拼接路径/{userId/score?score=新增积分
	@GetMapping("/{userId}/score")
	JsonResult addScore(@PathVariable Integer userId,@RequestParam Integer score);
	
}
