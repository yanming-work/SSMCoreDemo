package com.test.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.core.result.ApiReturnObj;

@RestController
@RequestMapping("/test")
public class TestController {

	
	@RequestMapping(value = "/test1", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public ApiReturnObj<Object> test1()  {
		ApiReturnObj<Object> apiReturnObj=new ApiReturnObj<Object>(1, "测试成功");
		
		return apiReturnObj;
	}
	
	
	
	@RequestMapping(value = "/test2", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public String test2() {
		return "测试成功2";
	}
	
	
	
	@RequestMapping(value = "/int", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public int testint() {
		return 520;
	}
	
	
	
	@RequestMapping(value = "/double", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public double testdouble() {
		return 13.14;
	}
	
	
	
	@RequestMapping(value = "/map", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map<String,String> testmap() {
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", "张三");
		map.put("msg", "你好");
		return map;
	}
}
