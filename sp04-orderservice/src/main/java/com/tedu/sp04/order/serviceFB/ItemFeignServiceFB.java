package com.tedu.sp04.order.serviceFB;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp04.order.service.ItemFeignService;
import com.tedu.web.util.JsonResult;

@Component
public class ItemFeignServiceFB implements ItemFeignService {

	@SuppressWarnings("unchecked")
	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		if(Math.random() < 0.5) {
			return JsonResult.ok().data(Arrays.asList(new Item[] {
					new Item(1,"商品1",1),
					new Item(2,"商品2",2),
					new Item(3,"商品3",3),
					new Item(4,"商品4",4),
					new Item(5,"商品5",5)
			}));
		}
		return JsonResult.err("无法读取订单商品列表");
	}

	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		return JsonResult.err("无法修改商品库存");
	}

}
