package cn.imust.dao;


import cn.imust.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合spring data jpa的dao层接口规范，需要继承JpaRepository、JpaSpecificationExecutor并实现相应泛型
 *      JpaRepository<T, ID>            * 封装了基本CRUD操作
 *          T: 操作的实体类类型
 *          ID: 实体类中主键属性的类型
 *      JpaSpecificationExecutor<T>     * 封装了复杂查询操作（分页等）
 *          T: 操作的实体类类型
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /**
     *  案例：根据客户名称查询客户
     *
     *  使用jpql的形式查询
     *  jpql: from Customer where custName like ?
     *  配置jpql语句：使用@Query注解
     */
    @Query(value = "from Customer where custName = ?1")
    public List<Customer> findJpql(String custName);

    /**
     *  案例：根据客户名称和客户id查询客户
     *      jpql: from Customer where custName = ? and custId = ?
     *
     *  对于多参数
     *      默认赋值的时候，占位符位置需要和参数位置保持一致
     *      也可以指定占位符取值来源    eg: ?1 ?2 ?3 ...
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findCustomerByNameAndId(String custName, Long custId);

    /**
     * 使用jpql完成更新操作
     *      案例 : 根据id更新客户名称
     *      sql : update cst_customer set cust_name = ? where cust_id = ?
     *      jpql : update Customer set custName = ? where custId = ?
     * @Query : 代表的是查询
     *      * 声明此方法是进行更新操作的
     */
    @Modifying
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    public void updateCustomer(Long custId, String custName);

    /**
     * 使用sql形式进行查询
     * sql: select * from cst_customer
     * @Query: 配置sql查询
     *      value: Sql语句
     *      nativeQuery:
     *          true: sql查询
     *          false: jpql查询 (default)
     */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Object [] > findSql();

    /**
     * 根据用户名模糊查询
     */
    @Query(value = "select * from cst_customer where cust_name like ?", nativeQuery = true)
    public List<Object [] > findSql(String name);

}
