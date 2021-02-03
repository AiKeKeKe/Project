package com.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/** 测试SSM整合后的开发环境 */
@Controller 
public class TestSSM {
	/* 自动装配
	 * 该注解的作用是, 由spring容器创建该对象
	 * 并将创建的对象, 赋值给该属性!!
	 */
	@Autowired 
	DoorMapper mapper;
	
	/**
	 * 配置 请求资源路径(/hello)和 方法(hello)
	 * 的映射关系
	 * 当请求访问 /hello 时, 将会调用 hello方法
	 * 来处理请求
	 */
	@RequestMapping("/testssm")
	public String hello(){
		System.out.println("test springmvc~~~");
		
		//查询所有的门店信息, 返回List<Door>集合
		List<Door> list = mapper.findAll();
		
		//遍历集合
		for(Door door:list){
			System.out.println("ssm:"+door);
		}
		
		//转向 /WEB-INF/pages/test.jsp
		return "test";
	}
}







