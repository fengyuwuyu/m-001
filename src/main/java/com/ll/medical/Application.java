package com.ll.medical;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ll.medical.util.MapUtils;

@SpringBootApplication
@RestController
public class Application {
	
	@RequestMapping("test.do")
	public Map<String, Object> test() {
		return MapUtils.createSuccessMap("msg", "test");
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
