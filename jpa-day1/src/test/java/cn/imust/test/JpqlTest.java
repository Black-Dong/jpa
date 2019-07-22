package cn.imust.test;

import cn.imust.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpqlTest {

    /**
     * 查询全部
     *      jpql: from cn.imust.domain.Customer
     *      sql: select * from cst_customer
     *
     * jpql：
     *      1. 根据jpql创建query查询对象
     *      2. 对参数进行赋值
     *      3. 查询并得到返回结果集
     */
    @Test
    public void testFindAll(){

        //1. 获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. 完成查询全部
        String jpql = "from cn.imust.domain.Customer";
        Query query = em.createQuery(jpql);//创建query查询对象，query才是执行jpql的对象

        //发送查询，并封装结果集
        List list = query.getResultList();

        for (Object obj : list){
            System.err.println(obj);
        }

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();
    }

    /**
     * 排序查询：倒序查询全部客户（根据id倒叙）
     *      sql: select * from cst_customer order by cust_id desc
     *      jpql: from Customer order by custId desc
     */
    @Test
    public void testOrder(){

        //1. 获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. 完成查询全部, 倒序
        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建query查询对象，query才是执行jpql的对象

        //发送查询，并封装结果集
        List list = query.getResultList();

        for (Object obj : list){
            System.err.println(obj);
        }

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();
    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql: select count(cust_id) from cst_customer
     *      jpql: select count(custId) from Customer
     */
    @Test
    public void testCount(){

        //1. 获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. 完成查询全部, 倒序
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);//创建query查询对象，query才是执行jpql的对象

        Object count = query.getSingleResult();
        System.err.println(count);

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();
    }

    /**
     * 分页查询
     *      sql: select * from cst_customer limit ?,?
     *      jpql: from Customer
     */
    @Test
    public void testPaged(){

        //1. 获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. 完成查询全部, 倒序
        //3.1 根据jpql语句创建查询语句
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);//创建query查询对象，query才是执行jpql的对象
        //3.2 对参数进行赋值
        query.setFirstResult(1);    //起始索引赋值
        query.setMaxResults(2);     //每页查询的条数
        //3.3 查询并得到返回结果集
        List list = query.getResultList();
        for (Object obj : list){
            System.err.println(obj);
        }

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();
    }

    /**
     * 条件查询
     *      eg：查询客户名称以‘科大’开头的客户
     *       sql: select * from cst_customer where cust_name like '科大%'
     *       jpql: from Customer where custName like ?
     */
    @Test
    public void testCondition(){

        //1. 获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. 完成查询全部, 倒序
        //3.1 根据jpql语句创建查询语句
        String jpql = "from Customer where custName like ?";
        Query query = em.createQuery(jpql);//创建query查询对象，query才是执行jpql的对象
        //3.2 对参数进行赋值
        query.setParameter(1, "内科%");
        //3.3 查询并得到返回结果集
        List list = query.getResultList();
        for (Object obj : list){
            System.err.println(obj);
        }

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();
    }
}
