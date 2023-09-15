package com.tyza66.orderquerytool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tyza66.orderquerytool.mapper")
public class OrderquerytoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderquerytoolApplication.class, args);
	}

}
