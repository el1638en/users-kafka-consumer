package com.syscom.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.syscom.handler.category.CategoryCreatedEventHandler;
import com.syscom.handler.user.UserCreatedEventHandler;

@Configuration
@EntityScan(basePackages = "com.syscom.beans")
@ComponentScan(basePackages = { "com.syscom" })
@EnableJpaRepositories(basePackages = { "com.syscom.repository" })
@EnableTransactionManagement
public class TestConfiguration {

	@MockBean
	private CategoryCreatedEventHandler categoryCreatedEventHandler;

	@MockBean
	private UserCreatedEventHandler userCreatedEventHandler;

}
