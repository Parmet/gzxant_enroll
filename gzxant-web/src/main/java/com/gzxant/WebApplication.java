package com.gzxant;

import com.gzxant.constant.Setting;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan({Setting.SCAN_MAPPER_PATH, Setting.COMMON_SCAN_MAPPER_PATH})//@Mapper  在mapper 接口上加入也行
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
