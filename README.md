# SSMCoreDemo
Spring 5 +SpringMvc 5 + mybatis 3.4.6 + pagehelper 5.1.4 + druid 1.1.10

注意 pagehelper 高版本配置与低版本不一样

统一接口返回对象 ApiReturnObj  
{
code: 1,
msg: "成功",
datas: 
}

统一日志记录Spring Aop 

WebLogAspect

支持跨域情况 cors 
MyCorsFilter


进过修改mybatis-generator-core  1.3.6 得到 mybatis-generator-core-gavin 自动生成需要的 model,dao,xml,service
