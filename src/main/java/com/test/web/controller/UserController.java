package com.test.web.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.test.core.result.ApiReturnObj;
import com.test.web.model.User;
import com.test.web.model.UserCriteria;
import com.test.web.service.UserService;

/**
 * 用户请求控制器
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;// 由Spring容器注入一个UserService实例

	
    
	/**
	 * 登录
	 * 
	 * @param user
	 *            用户
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public ApiReturnObj<List<User>> login(String userName,String password) throws IOException {
		ApiReturnObj<List<User>> apiReturnObj=new ApiReturnObj<List<User>>(2, "失败");
		UserCriteria userCriteria=new UserCriteria();
		userCriteria.createCriteria().andUserNameEqualTo(userName);
		userCriteria.createCriteria().andPasswordEqualTo(password);
		List<User> result= userService.selectByExample(userCriteria);// 调用UserService的登录方法
		if(result!=null){
			apiReturnObj=new ApiReturnObj<List<User>>(1, "成功",result);
		}
		return apiReturnObj;
	}

	/**
	 * 注册（只允许注册普通用户）
	 * 
	 * @param user
	 *            用户
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/register", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public ApiReturnObj<Object>  register() throws IOException {
		ApiReturnObj<Object> apiReturnObj=new ApiReturnObj<Object>(2, "失败");
		User user= new User();
		user.setUserName("test");
		user.setPassword("test");
		user.setRole(0);//0 普通 1 管理员
		user.setAlive(1);//1激活 0 冻结
		int lines = userService.insert(user);
		if(lines>0){
			apiReturnObj=new ApiReturnObj<Object>(1, "注册成功");
		}
		return apiReturnObj;
	}
	
	
	
	/**
	 * 批量注册（只允许注册普通用户）
	 * 
	 * @param user
	 *            用户
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/registerBatch", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public ApiReturnObj<Object>  registerBatch() throws IOException {
		ApiReturnObj<Object> apiReturnObj=new ApiReturnObj<Object>(2, "失败");
		
		List<User> userList= new ArrayList<User>();
		for(int i=1;i<100;i++){
			User user= new User();
			user.setUserName("test"+i);
			user.setPassword("test"+i);
			user.setRole(0);//0 普通 1 管理员
			user.setAlive(1);//1激活 0 冻结
			userList.add(user);
		}
		
		
		int lines = userService.insertBatch(userList);
		if(lines>0){
			apiReturnObj=new ApiReturnObj<Object>(1, "批量注册成功");
		}
		return apiReturnObj;
	}
	
	
	@RequestMapping(value = "/getByUserName", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public ApiReturnObj<User> getByUserName(String userName) throws IOException {
		ApiReturnObj<User> apiReturnObj=new ApiReturnObj<User>(2, "失败");
		User user = userService.selectByPrimaryKey(userName);
		if(user!=null){
			apiReturnObj=new ApiReturnObj<User>(1, "成功",user);
		}
		return apiReturnObj;
	}
	
	
	
	@RequestMapping(value = "/getByPage", method = {  RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public PageInfo<User> getByPage(String userName) throws IOException {
		PageInfo<User> pageInfo = userService.selectByPageHelperExample(1, 10, null);
		return pageInfo;
	}

	
}