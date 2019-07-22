# JPA-day1

## 学习JPA的第一天:orm思想和hibernate以及jpa的概述和jpa的基本操作

> ORM
>> 概念：Object-Relational Mapping，把关系数据库的表结构映射到对象上
>>
>> 主要目的：操作实体类就相当于操作数据库表，不再重点关注sql语句
>>
>> 建立两个映射关系
>>> 1. 实体类和表的映射关系
>>> 2. 实体类中属性和表中字段的映射关系  
>>> 注意：详细实现请看`cn.imust.domain`中的`Customer实体类`
>>
>> 实现了ORM思想的框架：mybatis，hibernate

> hibernate框架介绍
>> Hibernate是一个开放源代码的对象关系映射框架，
>>
>>> 1. 它对JDBC进行了非常轻量级的对象封装
>>> 2. 它将POJO与数据库表建立映射关系，是一个全自动的orm框架


> JPA
>> 1. 概念：JPA是Java Persistence API的简称，中文名Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。  
>>
>> 2. 实质（宗旨）：JPA的宗旨是为POJO提供持久化`标准规范`，内部是由接口和抽象类组成
>>
>> 3. [优势](https://baike.baidu.com/item/JPA/5660672)
>
> jpa操作的操作步骤
>> 1、 加载配置文件创建实体管理器工厂
>>> * Persisitence：静态方法（根据持久化单元名称创建实体管理器工厂）
>>>     * createEntityMnagerFactory（持久化单元名称）
>>> * 作用：创建实体管理器工厂（EntityManagerFactory）
>>> * EntityManagerFactory内部维护的很多的内容
>>>     * 内部维护了数据库信息
>>>     * 维护了缓存信息
>>>     * 维护了所有的实体管理器对象
>>>     * 在创建EntityManagerFactory的过程中会根据配置创建数据库表
>>> * EntityManagerFactory的创建过程比较浪费资源
>>> * 特点：线程安全的对象
>>>     * 多个线程访问同一个EntityManagerFactory不会有线程安全问题
>>> * 如何解决EntityManagerFactory的创建过程浪费资源（耗时）的问题？
>>>     * 思路：创建一个公共的EntityManagerFactory的对象 -- 静态代码块的形式创建EntityManagerFactory
>>
>> 2、 根据实体管理器工厂，创建实体管理器
>>> * 通过EntityManagerFactory获取EntityManager对象
>>> * 方法：createEntityManager
>>> * EntityManager对象：实体类管理器
>>>     * 方法：
>>>         * getTransaction : 获取事务对象
>>>         * presist : 保存
>>>         * merge : 更新
>>>         * remove : 删除
>>>         * find/getRefrence : 根据id查询
>>
>> 3、创建事务对象，开启事务
>>> * 通过EntityManager获取事务EntityTransaction对象
>>> * 方法：getTransaction
>>> * EntityTransaction对象：事务
>>>     * 方法：
>>>         * begin : 开启事务
>>>         * commit : 提交事务
>>>         * rollback : 回滚
>>
>> 4、增删改查操作（通过EntityManager对象的方法）
>>
>> 5、提交事务
>>
>> 6、释放资源


> JPA Demo（对客户的CRUD操作，详细请看自行翻阅源码）
>> 注意1：使用jpa需要一个核心配置文件`persistence.xml`放在类目录下的`META-INF`
>>  
>> 注意2：注意定义实体类时与数据库表映射，属性与表中字段映射
>> 
> jpa 使用流程请查看测试类JpaTest


> jpql查询
>>  sql：查询的是表和表中的字段  
>>  jpql：查询的是实体类和类中的属性  
>>  jpql和sql语句的语法相似
>> 1. 查询全部
>> 2. 分页查询
>> 3. 统计查询
>> 4. 条件查询
>> 5. 排序
		
		