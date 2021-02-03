package com.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 测试springmvc开发环境 */
@Controller 
public class TestSpringmvc {
	/**
	 * 配置 请求资源路径(/hello)和 方法(hello)
	 * 的映射关系
	 * 当请求访问 /hello 时, 将会调用 hello方法
	 * 来处理请求
	 */
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("test springmvc~~~");
		//转向 /WEB-INF/pages/test.jsp
		return "test";
	}
}







