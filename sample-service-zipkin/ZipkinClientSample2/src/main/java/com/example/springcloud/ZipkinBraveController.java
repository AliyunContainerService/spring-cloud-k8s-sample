package com.example.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/service2")
public class ZipkinBraveController {
	@Autowired
	private OkHttpClient okHttpClient;
	@Value("${services.service3.url}")
	private String service3Url;
	@Value("${services.service4.url}")
	private String service4Url;

	@RequestMapping("/test2")
	public String service() throws Exception {
		Thread.sleep(200);
		Request request3 = new Request.Builder().url(service3Url).build();
		Response response3 = okHttpClient.newCall(request3).execute();
		String response3Str = response3.body().string();

		Request request4 = new Request.Builder().url(service4Url).build();
		Response response4 = okHttpClient.newCall(request4).execute();
		String response4Str = response4.body().string();

		return response3Str + "-" + response4Str;
	}
}
