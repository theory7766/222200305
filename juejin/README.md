├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─example
│  │  │          ├─config
│  │  │          ├─controller
│  │  │          ├─dao
│  │  │          ├─domain
│  │  │          ├─exception
│  │  │          └─service
│  │  │              └─impl
│  │  └─resources
│  │      ├─static
│  │      └─templates
│  └─test
│      └─java
│          └─com
│              └─example






1. 首页：
文章标题，内容，作者，浏览量，点赞量，一张图片（按照时间最新顺序）
文章热度排行榜（应该是后端返回排好序的数据吧？）
2. 文章页：
文章标题，作者，发布时间，浏览量，点赞量，评论量，内容
评论头像，用户名，内容，时间，点赞量，评论量
3. 我的主页：
我写的文章，我赞过的文章 （标题，内容，时间，浏览量，点赞量，评论量)
文章表中寻找
4. 设置页面：
前端传给后端用户想要修改的数据和图片，后端判断是否符合条件，返回给前端一个信号（true or false？）
5. 写文章页面：
前端给后端传数据



总体思路:
校验token正确性，获取用户id

1. 首页：http://localhost:81/juejin/homepage
getHottest:1获取最热文章榜，0获取最新文章榜（文章表按visit_count/created_at排序）

count:文章数量

2. 文章页：http://localhost:81/juejin/article
article_id

文章以及评论列表：
文章get文章表里面的数据，评论列表是评论表按article_id返回数据(注意要根据user_id从users表中获得username）

3. 我的主页：http://localhost:81/juejin/myhomepage
getWrittenArticle：1返回我写过的文章列表，0返回我赞过的文章列表

前者直接按user_id从文章表里面返回，后者在user_like_article表中先获得article_id列表,再一个一个从文章表里面查询返回封装成列表。

4. 修改信息:已知user_id):
用户名username：
校验用户名是否重复，需同时修改用户表users


5.写文章：http://localhost:81/juejin/write


title，content，cover_url，加上user_id,created_at插入文章表

6. 获取用户信息：根据user_id从users_information表获得,从users获得username



##2.设计数据库表
1. 用户表users（用户名密码）。user_id（自增）,username,password(base64加密）,avatar_url（默认url头像）,created_at,token(可为空）

2. 文章表articles：
article_id（自增）,user_id,title,content,cover_url,visit_count（0）,like_count（0）,comment_count（0）,created_at（写文章时间）

3. 评论表comments:comment_id（自增）,user_id,article_id,parent_id,like_count（0）,child_count（0）,content,created_at
4. 用户点赞文章表user_like_article:user_id,article_id