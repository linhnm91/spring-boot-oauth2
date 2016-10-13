package com.codehubs.springoauth2.oauth2.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.codehubs.springoauth2.SpringSecurityOauth2Application;

public class ServletInitializer extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringSecurityOauth2Application.class);
	}
}
