package com.example.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;

import okhttp3.OkHttpClient;

@Configuration
public class ZipkinConfig {

	@Value("${zipkin.server.url}")
	private String zipkinServerUrl;
	@Value("${spring.application.name}")
	private String applicationName;

	@Bean
	public SpanCollector spanCollector() {
		HttpSpanCollector.Config spanConfig = HttpSpanCollector.Config.builder().compressionEnabled(false)// 默认false，span在transport之前是否会被gzipped。
				.connectTimeout(5000)// 5s，默认10s
				.flushInterval(1)// 1s
				.readTimeout(6000)// 5s，默认60s
				.build();
		return HttpSpanCollector.create(zipkinServerUrl, spanConfig, new EmptySpanCollectorMetricsHandler());
	}

	@Bean
	public Brave brave(SpanCollector spanCollector) {
		Brave.Builder builder = new Brave.Builder(applicationName);// 指定serviceName
		builder.spanCollector(spanCollector);
		builder.traceSampler(Sampler.create(1));// 采集率
		return builder.build();
	}

	@Bean
	public BraveServletFilter braveServletFilter(Brave brave) {
		/**
		 * 设置sr、ss拦截器
		 */
		return new BraveServletFilter(brave.serverRequestInterceptor(), brave.serverResponseInterceptor(),
				new DefaultSpanNameProvider());
	}

	@Bean
	public OkHttpClient okHttpClient(Brave brave) {
		/**
		 * 设置cs、cr拦截器
		 */
		return new OkHttpClient.Builder()
				.addInterceptor(new BraveOkHttpRequestResponseInterceptor(brave.clientRequestInterceptor(),
						brave.clientResponseInterceptor(), new DefaultSpanNameProvider()))
				.build();
	}
}
