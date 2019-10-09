package com.tedu.sp11.fb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ItemServiceFallback implements FallbackProvider {

	@Override
	public String getRoute() {
		//当执行item-service失败，应用当前这个降级类
		//星号和null都表示所有微服务失败都应用当前降级类
		//“*”，null
		return "item-service";
	}

	//该方法返回封装降级响应的对象
	//ClientHttpResponse中封装降级响应
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return response();
	}

	private ClientHttpResponse response() {
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				log.info("Fallback body");
				byte[] c = JsonResult.err().msg("后台服务出现错误，请稍后重试").toString().getBytes("UTF-8");
				return new ByteArrayInputStream(c);
			}
			
			//以下三个都是协议号
			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}
			
			@Override
			public void close() {
			}
		};
	}

}
