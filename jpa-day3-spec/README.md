# JPA-day3-spec

## 学习JPA的第三天 : 多表操作，复杂查询

> Specifications动态查询
>>	1、JpaSpecificationExecutor 方法列表
>> * 查询单个对象  
>>  `T findOne(Specification<T> spec);`
>> * 查询列表  
>>  `List<T> findAll(Specification<T> spec);`
>> * 查询全部，分页
>>  ```
>>  //pageable：分页参数
>>  //返回值：分页pageBean（page：是springdatajpa提供的）
>>  Page<T> findAll(Specification<T> spec, Pageable pageable);
>>   ```
>> * 查询列表
>>  ```
>>  //Sort：排序参数
>>  List<T> findAll(Specification<T> spec, Sort sort);
>>  ```
>> * 统计查询  
>>  `long count(Specification<T> spec);`
>> 
>> 2、Specification ：查询条件
>>  * 自定义我们自己的Specification实现类实现
>>      * 实现  
>>      `Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb); //封装查询条件`
>>          * root：查询的根对象（查询的任何属性都可以从根对象中获取）
>>          * CriteriaQuery：顶层查询对象，自定义查询方式（了解：一般不用）
>>          * CriteriaBuilder：查询的构造器，封装了很多的查询条件