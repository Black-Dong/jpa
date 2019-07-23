# JPA-day2

## 学习JPA的第一天:springdatajpa的运行原理以及基本操作
 day3：多表操作，复杂查询
> springDataJpa的概述:
>>
>> 概述：
>>> Spring Data JPA 是 Spring 基于 ORM 框架、JPA 规范的基础上封装的一套JPA应用框架，可使开发者用极简的代码即可实现对数据库的访问和操作。它提供了包括增删改查等在内的常用功能，且易于扩展！
>>
>> 特性：
>>> SpringData Jpa 极大简化了数据库访问层代码。 使用了SpringDataJpa，我们的dao层中只需要写接口，就自动具有了增删改查、分页查询等方法。
>>
>> Spring Data JPA与 JPA和hibernate之间的关系
>>> JPA是一套规范，内部是有接口和抽象类组成的。hibernate是一套成熟的ORM框架，而且Hibernate实现了JPA规范，所以也可以称hibernate为JPA的一种实现方式，我们使用JPA的API编程，意味着站在更高的角度上看待问题（面向接口编程）
>>
>>> Spring Data JPA是Spring提供的一套对JPA操作更加高级的封装，是在JPA规范下的专门用来进行数据持久化的解决方案。
>>


> springDataJpa的入门操作:
>>
>> 案例：客户的基本CRUD
>>> 1、搭建环境
>>>> * 创建工程导入坐标
>>>> * 配置spring的配置文件（配置spring Data jpa的整合）
>>>> * 编写实体类（Customer），使用jpa注解配置映射关系  
>>>
>>> 2、编写一个符合springDataJpa的dao层接口
>>>> * 只需要编写dao层接口，不需要编写dao层接口的实现类
>>>> * dao层接口规范
>>>>    * 需要继承两个接口（JpaRepository --提供简单CRUD，JpaSpecificationExecutor --提供复杂查询）
>>>>    * 需要提供相应的泛型（详见测试类）
>>>> * 方法：
>>>>    * findOne（id） ：根据id查询
>>>>    * save(customer):保存或者更新（依据：传递的实体类对象中，是否包含id属性）
>>>>    * delete（id） ：根据id删除
>>>>    * findAll() : 查询全部
>>>
>>> 3、SpringDataJpa的运行过程和原理分析
>>>> * 当使用@Autowired按类型自动注入dao时，通过JdkDynamicAopProxy的invoke方法创建了一个动态代理对象(SimpleJpaRepository)
>>>> * SimpleJpaRepository当中封装了JPA的操作(借助JPA的api完成数据库的CRUD操作)
>>>> * 通过hibernate完成数据库操作(封装了jdbc操作)
>>>
>>> 4、复杂查询
>>>> * 借助接口中定义好的方法完成查询 eg: findOne(id)...
>>>> * jpql的查询方法
>>>>    * jpql : jpa query language (jpa查询语言)
>>>>    * 特点 : 
>>>>        * 语法或关键字和sql语句类似
>>>>        * 查询的是类和类中属性
>>>>    * 需要将jpql语句配置到接口方法上
>>>>        * 特有的查询：需要在dao接口中配置方法
>>>>        * 在新添加的方法上，使用注解的形式配置jpql查询语句
>>>>        * 注解：@Query
>>>>    
>>>>    


