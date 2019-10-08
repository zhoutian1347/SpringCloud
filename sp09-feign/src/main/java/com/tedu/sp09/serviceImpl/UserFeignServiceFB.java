package com.tedu.sp09.serviceImpl;

import org.springframework.stereotype.Component;

import com.tedu.sp09.service.UserFeignService;
import com.tedu.web.util.JsonResult;

@Component
public class UserFeignServiceFB implements UserFeignService {

	@Override
	public JsonResult getUser(Integer userId) {
		return JsonResult.err("获取用户列表失败");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		return JsonResult.err("增加积分失败");
	}

}
