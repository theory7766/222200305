
#OrderSystem 订货系统
###作者：222200305 theory7766
#####技术栈：
mysql、JDBC

#####项目目录介绍：
1.entity为实体包：包括Goods.java商品类和OrderForm.java订单类

2.DAO包：

1）jdbcUtils.java为jdbc工具类

2）MyException.java为异常语句类

3）ResultSetHandler.java为查询结果处理接口

4）ArrayHandlerList.java为实现ResultSetHandler接口的具体实现类：将 查询数据存储到List集合List<Object[]>

5）OrderSystem.java为订货系统类

3.test.java为测试类

#####项目功能介绍
1.使用商品表goods记录商品信息，以商品编号goods_id作为主键；

使用订单表order_form来记录订单编号order_id，下单时间order_time以及订单价格order_price，以订单编号order_id作为主键；

使用订单商品关联表order_goods记录商品编号good_id以及订单编号order_id，另起一个单独自增编号id作为主键。

2.实现对数据的增删改查操作以及排序后的查询操作，解决了SQL注入的问题，添加了事务管理。

3.对数据库数据进行校验，如插入与查询时价格必须大于零；插入订单时查询商品信息是否存在；插入新商品时先查询商品表中无该商品；在进行删除，修改时先进行查询。

#####项目待改进点

1.部分SQL语句可合并

2.test类需进一步完善

3.可使用数据库连接池


 






