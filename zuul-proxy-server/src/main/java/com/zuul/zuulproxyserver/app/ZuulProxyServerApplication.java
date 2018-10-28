package com.zuul.zuulproxyserver.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;

import com.zuul.zuulproxyserver.config.ZuulConfig;

@SpringBootApplication
@EnableZuulProxy
@Import(ZuulConfig.class)
public class ZuulProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyServerApplication.class, args);
	}
}
