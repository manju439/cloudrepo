package com.zuul.zuulproxyserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zuul.zuulproxyserver.filters.ErrorFilter;
import com.zuul.zuulproxyserver.filters.PostFilter;
import com.zuul.zuulproxyserver.filters.PreFilter;
import com.zuul.zuulproxyserver.filters.RouteFilter;

@Configuration
public class ZuulConfig {
	
	@Bean
	public PreFilter preFilter() {
	    return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
	    return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
	    return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
	    return new RouteFilter();
	}

}
