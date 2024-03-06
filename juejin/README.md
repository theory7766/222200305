###1.项目结构
###1. 项目结构
	└─src
	   └─main
	       ├─java
	       │  └─com
	       │      └─example
	       │          ├─config		// 配置类
	       │          ├─controller	// 控制器
	       │          │  └─result	// 响应数据封装类
	       │          ├─dao			// 数据访问层
	       │          ├─domain		// 实体类
	       │          ├─exception	// 异常处理类
	       │          └─service		// 服务层
	       │              └─impl	// 接口
	       └─resources
	           ├─static
	           └─templates
		
	pom.xml		// maven 项目配置文件
	Dockerfile  // docker文件
###2.技术栈
数据库：mysql、mybatis、druid
基本框架：springboot
工具类：hutool
###3.项目功能介绍
####1.数据库表的基本结构
1. users（记录用户的信息）：username以及加密后的password,avatar__url(头像),created_at(注册时间)，token,以一个自增的user_id作为主键。
2. user_like_article(记录用户点赞的文章)：user_id以及article_id,以一个自增的id作为主键。
3. articles(文章表）：user_id,title,content,cover_url,visit_count,like_count,comment_count,created_at(创建时间），以一个自增的article_id作为主键。
4. comments(评论表）：user_id,article_id,parent_id,like_count,child_count,content,created_at(评论时间），以一个自增的comment_id作为主键。

####2.apifox接口文档
链接：（只读成员）
	
s2pK 在 Apifox 邀请你加入项目 寒假合作轮 https://app.apifox.com/invite/project?token=U7hHhdtbSBuTlSy6uJ1Kp

####3.项目功能介绍
- 用户注册功能

要求：用户名不得重复、密码长度大于6。

使用hutool工具中的DigestUtil.bcrypt对密码进行加密后存进数据库。

- 用户登录功能

进行用户名与密码的校验后，
制作token：若并非首次登录，就直接通过username从users_token中获取token。否则，就使用hutool进行token制作。

响应数据包含token字段

- 获取用户信息功能
根据token获取user_id,返回的数据包括username与avatar_val

- 修改用户信息
其中，修改用户名username时需确保新用户名不重复；修改密码时，密码长度需要大于6。

- 首页（获取最热文章数据或者最新文章数据）

getHottest参数为1获取最热文章列表，0获取最新文章列表。

count参数为想要获取的文章数据数量。

最热文章列表按visit_count降序排序，最新文章列表按created_at降序排列。

- 文章页

按article_id获取文章数据，以及其相应评论列表分别放在响应数据中的task与items中。

其中，评论列表可能为空。

- 我的主页

getWrittenArticle参数为1返回我写过的文章列表，0返回我赞过的文章列表。（这二者皆可能为空）置于响应数据的items中。


- 写文章功能

将前端传入的文章title,content,cover_url信息封装进article对象以后，以当前时间为创建时间created_at，结合由token获取的user_id，默认visit_count,like_count,comment_count为0，将文章数据存入articles表。

值得注意的是，除注册与登录，请求头中都需要包含token。将本项目中token较为简单，直接与用户名相绑定存在用户表users中。每次校验token时，直接从users中查询这个token是否存在，存在则返回相应的user_id。

根据user_id从users表中获取相应的username或avatar_url数据，封装到comment或者article对象中来满足前端的需要。尽量减少数据库表的耦合性。

####4.项目解决的问题
- 跨域问题

使用CorsFilter进行配置。

或者在controller类上直接添加@CrossOrigin使得该类所有方法皆可跨域访问。

- java String与mysql Datetime类型的转换：使用SimpleDateFormat 

- 在云服务器上部署后端项目

第一步在云服务器下载docker时，报错repomd.xml在所有镜像找不到：

	# 对原来的yum文件进行备份
	sudo rename '.repo' '.repo.bak' /etc/yum.repos.d/*.repo
	# 下载最新的阿里云的yum文件
	sudo wget https://mirrors.aliyun.com/repo/Centos-vault-8.5.2111.repo -O /etc/yum.repos.d/Centos-vault-8.5.2111.repo
	sudo wget https://mirrors.aliyun.com/repo/epel-archive-8.repo -O /etc/yum.repos.d/epel-archive-8.repo
	# 替换文件内容
	sed -i 's/mirrors.cloud.aliyuncs.com/mirrors.aliyun.com/g' /etc/yum.repos.d/Centos-vault-8.5.2111.repo
	sed -i 's/mirrors.cloud.aliyuncs.com/mirrors.aliyun.com/g' /etc/yum.repos.d/epel-archive-8.repo

	#更新yum
	sudo yum update
	# 下载
	sudo yum install -y yum-utils

开始安装docker环境

	sudo yum install docker-ce docker-ce-cli containerd.io
	# 设置开机自启
	systemctl enable docker.service
	# 创建docker网络
	docker network create bili

开始安装mysql服务（容器名称：mysql）

	# mysql服务
	docker run -d --name mysql -v mysqldata:/var/lib/mysql -v mysqlconf:/etc/mysql/conf.d -v mysqllog:/var/log/mysql -p 3306:3306 --network bili -e MYSQL_ROOT_PASSWORD=Xp@199866 mysql:8

- java应用镜像构建：

Dockerfile文件编写：值得注意的是要将application.yaml与jar包一同打包。

将jar包、Dockerfile文件、applicatio.yaml文件上传到工作文件夹/usr/local/java下

使用Xterminal进行远程连接，但发现还是Xftp上传文件比较靠谱。

	docker build -t bili:1
运行后端：
	docker run -d -it --name bili --network bili -v /usr/local/java:/usr/local/java -p 81:81 bili:1

开启docker: 
	
	systemctl  start docker
- 日志：使用docker logs bili -f将日志导出到本目录下的
nohup.out，可使用cat 文件名进行查看

对于日志数据过大的问题，可使用
	
	# 获取日志文件最后300行
	docker logs -f bili -n 300
- mysql连接失败
application.yaml文件中mysql配置中url要相应改成mysql即数据库中MySQL的名字，而不是localhost。

- 使用SQLyog可视化工具进行数据库远程连接，可直接将本地的数据库或表直接复制到云服务器上

####5.项目待改进的地方
1. springboot程序未设置日志。
2. token处理不够恰当，应设置一个过期时间，并且使用拦截器进行处理。
3. 对于异常处理可以再细分一点。
