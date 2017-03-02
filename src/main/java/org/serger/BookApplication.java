package org.serger;

import org.serger.servlets.BooksRestServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
public class BookApplication {

	@Bean
	public Servlet dispatcherServlet() {
		return new BooksRestServlet();
	}

	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(
				dispatcherServlet(), "/books/*");

		registration
				.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);

		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
