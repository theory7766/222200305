###1. 项目结构
	
	├─src
	│  ├─main
	│  │  ├─java
	│  │  │  └─com
	│  │  │      └─example
	│  │  │          ├─config		//配置类
	│  │  │          ├─controller	//控制器
	│  │  │          ├─dao			//数据访问层
	│  │  │          ├─domain		//实体类
	│  │  │          ├─exception	//异常处理类
	│  │  │          └─service		//服务层
	│  │  │              └─impl		//接口
	│  │  └─resources				// 配置资源
	│  │      ├─static
	│  │      └─templates
	│  └─test						//测试类
	│      └─java
	│          └─com
	│              └─example
	└─pom.xml 						//maven 项目配置文件

###2.技术栈
数据库：mysql、mybatis、druid
基本框架：springboot
工具类：hutool
###3.项目功能介绍
####1.数据库表的基本结构
1. Table users:记录用户的username以及password,以一个自增的id作为主键。
2. Table users_token:记录username以及token,以一个自增的id作为主键。
3. Table tasks:记录username与Task类中的title,content,start_time,end_time,status,以一个自增的id作为主键。
####2.用户注册功能
- 数据校验：保证username不能重复，密码长度不可小于6
- 密码加密：使用hutool工具中的DigestUtil.bcrypt进行加密
- 数据持久化：将username与加密后的密码存进users
####3.用户登录功能
- 数据校验：通过username在users表中查询对应用户，使用hutool中的DigestUtil.bcryptCheck进行密码的校验
- 制作token：若并非首次登录，就直接通过username从users_token中获取token。否则，就使用hutool进行token制作。
####4.创建任务功能
- 数据校验：用户携带的token能否从users_token中查到，若可以返回username；任务信息是否完整；任务status数据是否正确；任务start_time与end_time是否正确，并且start_time在end_time之前。
- 数据持久化：将task按username保存在tasks表中
####5.获取任务信息功能
- 数据校验：用户携带的token能否从users_token中查到，若可以返回username；任务status数据是否正确
- 查询数据：按username从tasks表中查询相应status状态的数据
####6.更新任务功能
- 数据校验：用户携带的token能否从users_token中查到，若可以返回username；任务status数据是否正确；任务id是否正确
- 更新数据：按username与id更新tasks表中相应任务的status数据
####7.删除任务功能
- 数据校验：用户携带的token能否从users_token中查到，若可以返回username；任务id是否正确
- 删除数据：按username删除tasks中相应id的任务。

###4.项目待改进点

1. 尚未解决跨域问题
2. 使用安全框架的话，安全性更高。
3. 对于相应数据的处理方面，可以适当再优化一下。
###5.项目收获
1. 学会使用apifox进行接口测试，与前端进行响应数据的对接。
2. 了解了部分网络安全知识
3. 学习了spring，springmvc，springboot
 
