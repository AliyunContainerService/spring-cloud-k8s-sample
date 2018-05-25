package com.example.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/service1")
public class ZipkinBraveController {
	@Autowired
	private OkHttpClient okHttpClient;
	@Value("${services.service2.url}")
	private String service2Url;

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String service() throws Exception {
		Thread.sleep(100);
		Request request = new Request.Builder().url(service2Url).build();
		Response response = okHttpClient.newCall(request).execute();
		return response.body().string();
	}
}
