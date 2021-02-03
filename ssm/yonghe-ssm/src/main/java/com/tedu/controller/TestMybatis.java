package com.tedu.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/** 测试mybatis开发环境 */
public class TestMybatis {
	public static void main(String[] args) throws Exception {
		//1.读取mybatis-config.xml文件
		InputStream in = 
				Resources.getResourceAsStream(
				"mybatis/mybatis-config.xml");
		//2.获取SqlSessionFactory工厂
		SqlSessionFactory factory = 
				new SqlSessionFactoryBuilder()
				.build(in);
		//3.通过工厂获取SqlSession对象
		SqlSession session = factory
				.openSession();
		//4.获取DoorMapper接口的实例
		DoorMapper mapper = 
				session.getMapper(
						DoorMapper.class);
		//5.调用findAll方法查询所有门店信息
		List<Door> list = mapper.findAll();
		
		//6.遍历所有门店信息
		for(Door d:list){
			System.out.println(d);
		}
	}
}
