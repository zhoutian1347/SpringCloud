package com.tedu.sp09.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp09.service.ItemFeignService;
import com.tedu.web.util.JsonResult;

//将类放入spring容器
@Component
public class ItemFeignServiceFB implements ItemFeignService {

	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		return JsonResult.err("获取订单中的商品列表失败，请稍后重试");
	}

	@Override
	public JsonResult decreaseNumbner(List<Item> items) {
		return JsonResult.err("库存扣减失败，请稍后重试");
	}

}
