package com.tedu.sp06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
	@HystrixCommand(fallbackMethod = "getItemsFB")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId){
		return rt.getForObject(url+"{1}", JsonResult.class,orderId);
	}
	
	
	@PostMapping("/item-service/decreaseNumber")
	@HystrixCommand(fallbackMethod = "decreaseNumberFB")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		return rt.postForObject(url+"decreaseNumber", items, JsonResult.class);
	}
	
	//调用后台的用户微服务
	@GetMapping("user-service/{userId}")
	@HystrixCommand(fallbackMethod = "getUserFB")
	public JsonResult<User> getUser(@PathVariable Integer userId){
		return rt.getForObject(url1+"{1}", JsonResult.class, userId);
	}
	
	@GetMapping("/user-service/{userId}/score")
	@HystrixCommand(fallbackMethod = "addScoreFB")
	public JsonResult addScore(@PathVariable Integer userId, Integer score) {
		return rt.getForObject(url1+"{1}/score?score={2}", JsonResult.class, userId, score);
	}
	
	
	//调用后台的订单微服务
	@GetMapping("/order-service/{orderId}")
	@HystrixCommand(fallbackMethod = "getOrderFB")
	public JsonResult<Order> getOrder(@PathVariable String orderId){
		return rt.getForObject(url2+"{1}", JsonResult.class, orderId);
	}
	
	@GetMapping("/order-service/")
	@HystrixCommand(fallbackMethod = "addOrderFB")
	public JsonResult addOrder() {
		return rt.getForObject(url2, JsonResult.class);
	}
	
	
	//降级方法的参数和返回值需要和原始方法一致，方法名任意
	public JsonResult<List<Item>> getItemsFB(String orderId){
		return JsonResult.err("获取订单的商品列表"+orderId+"失败");
	}
	
	public JsonResult decreaseNumberFB(List<Item> items) {
		return JsonResult.err("商品"+items+"库存扣减失败");
	}
	
	public JsonResult<User> getUserFB(Integer id){
		return JsonResult.err("获取用户信息失败");
	}
	public JsonResult addScoreFB(Integer id,Integer score) {
		return JsonResult.err("增加用户积分失败");
	}
	
	public JsonResult<Order> getOrderFB(String orderId){
		return JsonResult.err("获取订单失败");
	}
	public JsonResult addOrderFB() {
		return JsonResult.err("添加订单失败！");
	}
	
	
}








