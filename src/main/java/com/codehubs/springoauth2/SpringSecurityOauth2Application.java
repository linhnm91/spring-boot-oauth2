package com.codehubs.springoauth2;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = {"com.codehubs.springoauth2.model"})
public class SpringSecurityOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2Application.class, args);
	}
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
	    return hemf.getSessionFactory();
	}
}
