package com.test.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.web.dao.mybatis.UserDao;
import com.test.web.model.User;
import com.test.web.model.UserCriteria;
import com.test.web.service.UserService;
import java.util.List;
import org.mybatis.generator.plugins.my.PageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public long countByExample(UserCriteria userCriteria) {
        long count = this.userDao.countByExample(userCriteria);
        logger.debug("count: {}", count);
        return count;
    }

    public User selectByPrimaryKey(String userName) {
        return this.userDao.selectByPrimaryKey(userName);
    }

    public List<User> selectByExample(UserCriteria userCriteria) {
        return this.userDao.selectByExample(userCriteria);
    }

    public int deleteByPrimaryKey(String userName) {
        return this.userDao.deleteByPrimaryKey(userName);
    }

    public int updateByPrimaryKeySelective(User user) {
        return this.userDao.updateByPrimaryKeySelective(user);
    }

    public int updateByPrimaryKey(User user) {
        return this.userDao.updateByPrimaryKey(user);
    }

    public int deleteByExample(UserCriteria userCriteria) {
        return this.userDao.deleteByExample(userCriteria);
    }

    public int updateByExampleSelective(User user, UserCriteria userCriteria) {
        return this.userDao.updateByExampleSelective(user,userCriteria);
    }

    public int insert(User user) {
        return this.userDao.insert(user);
    }

    public int insertSelective(User user) {
        return this.userDao.insertSelective(user);
    }

    public int insertBatch(List<User> userList) {
        		 //控制一次性数量，避免超出数据库SQL内存，批量插入，每200条数据进行插入一次,
		 int rm=0;
		 int perCount=200,index=0;  
		 int times=userList.size()/perCount; 
		 do  { 
		  	List<User> listTemp=null;
 		  	if(userList.size()>=perCount){  
		  	         listTemp=userList.subList(0, perCount);// listTemp是分段处理逻辑的参数  
		  	     }else{ 
		  	        listTemp=userList.subList(0, userList.size());// listTemp是分段处理逻辑的参数
		  	}  
		 		System.out.println("第"+(index+1)+"轮批量保存>>");
		 		rm+= this.userDao.insertBatch(listTemp);
		 		userList.removeAll(listTemp);
		 		    System.out.println("当前剩余集合长度:>>"+userList.size()); 
		 		    index++;  
		 		}
		 while (userList.size() >0 && index<=times); 

		 return rm;

    }

    public int insertBatchSelective(List<User> userList) {
        		 //控制一次性数量，避免超出数据库SQL内存，批量插入，每200条数据进行插入一次,
		 int rm=0;
		 int perCount=200,index=0;  
		 int times=userList.size()/perCount; 
		 do  { 
		  	List<User> listTemp=null;
 		  	if(userList.size()>=perCount){  
		  	         listTemp=userList.subList(0, perCount);// listTemp是分段处理逻辑的参数  
		  	     }else{ 
		  	        listTemp=userList.subList(0, userList.size());// listTemp是分段处理逻辑的参数
		  	}  
		 		System.out.println("第"+(index+1)+"轮批量保存>>");
		 		rm+= this.userDao.insertBatchSelective(listTemp);
		 		userList.removeAll(listTemp);
		 		    System.out.println("当前剩余集合长度:>>"+userList.size()); 
		 		    index++;  
		 		}
		 while (userList.size() >0 && index<=times); 

		 return rm;

    }

    public PageView<User> selectByPageExample(int pageNum, int pageSize, UserCriteria userCriteria, boolean distinct, String orderByClause) {
        PageView<User>  pageView = null;
		 long count = this.countByExample(userCriteria);
		 if (count>0){
		 pageView = new PageView<User>();
		 if (pageSize<=0){
		 	pageSize=10;
		 }
		 //计算页数
		 int pages=0;
		 if(count<pageSize){
		 	 pages=1;
		 }else{
		 	 pages= (int)Math.ceil(count/pageSize);
		 }
		 /**
		 if(count/pageSize==0){
		 	pages=count/pageSize;
		 }else{
		 	pages=count/pageSize +1 ;
		 }
		 **/	

		 int size=pageNum;
		 if (pageNum<=0){	
		 	size=1;
		 }else if(pageNum>pages){
		 		size=pages;
		 }
		 int prePage=0;
		 int nextPage=0;
		 boolean isFirstPage=false;
		 boolean isLastPage=false;
		 	boolean isHasPreviousPage=false;
		 	boolean isHasNextPage=false;
		 int navigatePages=7; //导航页码数  
		 int[] navigatePageNumbers=new int[navigatePages];   //所有导航页号  
		 if(size<=1){
		 	isFirstPage=true;
		 	isHasNextPage=true;
		 	nextPage=2;
		 	for(int i=1;i<pages;i++){
		 		navigatePageNumbers[i-1]=i;
		 		if(i==navigatePages){
		 			break;
		 		}
		 	}
		 	if(pages==1){
		 			isLastPage=true;
		 	}else{
		 		isHasNextPage=true;
		 	}
		 	if(navigatePages>=pages){
		 		for (int i=1;i<=pages;i++){
		 				navigatePageNumbers[i-1]=i;
		 		 }
		 	}
		 }else if(size==pages){
		 	isLastPage=true;
		 	isHasPreviousPage=true;
		 	prePage=size-1;
		 	for(int i=1;i<pages;i++){
		 		if(pages-i>0){
		 				navigatePageNumbers[i-1]=pages-i;
		 			if(i==navigatePages){
		   				break;
		   			}
		 			}else{
		 			break;
		 		}
		 	}
		 }else{
		 	isHasNextPage=true;
		 	isHasPreviousPage=true;
		 	prePage=size-1;
		 	nextPage=size+1;
		 	if(size<=navigatePages/2){
		 			for (int i=1;i<=navigatePages;i++){
		 			navigatePageNumbers[i-1]=i;
		 		}
		 	}else if(size>=pages-navigatePages/2){
		 		for (int i=0;i<navigatePages;i++){
		 			navigatePageNumbers[i]=pages-i;
		 		}
		 	}else{
		 		if(navigatePages%2==0){
		 			for (int i=0;i<navigatePages/2;i++){
		  				navigatePageNumbers[i]=size-navigatePages/2+i;
		  				navigatePageNumbers[i+navigatePages/2]=size+i;
		   			}
		 		}else{
		 			navigatePageNumbers[navigatePages/2]=size;
		 			for (int i=0;i<navigatePages/2;i++){
		   				navigatePageNumbers[i]=size-navigatePages/2+i;
		  				navigatePageNumbers[i+navigatePages/2+1]=size+i+1;
		    			}
		 			}
		 		}
		 		 if(navigatePages>=pages){
		 			for (int i=1;i<=pages;i++){
		 				navigatePageNumbers[i-1]=i;
		 		 }
		 	}
		 }
		  int startRow=(size-1)*pageSize;
		  int endRow=size*pageSize;
		 List<User> list = this.userDao.selectByPageExample(startRow,endRow,userCriteria,distinct,orderByClause);
		 	if (list !=null && list.size()>0){
		   		pageView.setPageNum(pageNum);
		 		pageView.setPageSize(pageSize);
		 		pageView.setSize(size);
		 		pageView.setStartRow(startRow);
		  		pageView.setEndRow(endRow);
		  		pageView.setTotal(count);
		  		pageView.setPages(pages);
		  		pageView.setList(list);
		   		pageView.setFirstPage(1);
		   		pageView.setLastPage(pages);
		    	pageView.setPrePage(prePage);
		   		pageView.setNextPage(nextPage);
		    	pageView.setIsFirstPage(isFirstPage);
		    	pageView.setIsLastPage(isLastPage);
		    	pageView.setIsHasPreviousPage(isHasPreviousPage);
		    	pageView.setIsHasNextPage(isHasNextPage);
		    	pageView.setNavigatePages(navigatePages);
		    	pageView.setNavigatepageNums(navigatePageNumbers);
		 	}
		 }
		   return pageView;

    }

    public PageInfo<User> selectByPageHelperExample(int pageNum, int pageSize, UserCriteria userCriteria) {
        		 PageInfo<User> pageInfo =null;
		 PageHelper.startPage(pageNum, pageSize);
		 List<User> list = this.userDao.selectByExample(userCriteria);
		   if (list !=null && list.size()>0){
		   		pageInfo= new PageInfo<User>(list);
		   }
		 return pageInfo;

    }
}