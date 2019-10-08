package com.tedu.sp06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;


@RestController
public class RibbonController {

	@Autowired
	private RestTemplate rt;
	
	private String url = "http://item-service/";
	private String url1 = "http://user-service/";
	private String url2 = "http://order-service/";
	
	//调用后台的商品微服务
	@SuppressWarnings("unchecked")
	@GetMapping("/item-service/{orderId}")	//pathVariable 路径参数
	public JsonResult<List<Item>> getItems(@PathVariable String orderId){
		System.out.println("ribbon.getItems");
		return rt.getForObject(url+"{1}", JsonResult.class,orderId);
	}
	
	
	@PostMapping("/item-service/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		System.out.println("ribbon.decreaseNumber");
		return rt.postForObject(url+"decreaseNumber", items, JsonResult.class);
	}
	
	//调用后台的用户微服务
	@GetMapping("user-service/{userId}")
	public JsonResult<User> getUser(@PathVariable Integer userId){
		return rt.getForObject(url1+"{1}", JsonResult.class, userId);
	}
	
	@GetMapping("/user-service/{userId}/score")
	public JsonResult addScore(@PathVariable Integer userId, Integer score) {
		return rt.getForObject(url1+"{1}/score?score={2}", JsonResult.class, userId, score);
	}
	
	
	//调用后台的订单微服务
	@GetMapping("/order-service/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId){
		return rt.getForObject(url2+"{1}", JsonResult.class, orderId);
	}
	
	@GetMapping("/order-service/")
	public JsonResult addOrder() {
		return rt.getForObject(url2, JsonResult.class);
	}
	
}
