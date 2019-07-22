package cn.imust.test;

import cn.imust.domain.Customer;
import cn.imust.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     * jpa的操作步骤
     *      1.加载配置文件，创建工厂(实体管理类工厂)对象
     *      2.通过实体管理类工厂 创建实体管理器
     *      3.获取事务对象，开启事务 *
     *      4.完成增删改查操作
     *      5.提交事务(回滚事务)
     *      6.释放资源
     */
    @Test
    public void testSave(){
        //1.加载配置文件，创建工厂(实体管理类工厂)对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理类工厂 创建实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事务对象，开启事务 *
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //4.完成增删改查操作
        Customer customer = new Customer();
        customer.setCustName("内科大");
        customer.setCustIndustry("教育");
        //保存
        em.persist(customer);

        //5.提交事务(回滚事务)
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();
    }


    /**
     * 测试Jpa工具类是否好使
     */
    @Test
    public void testJpaUtils(){
        //1. 使用jpaUtils创建实体类管理器
        EntityManager em = JpaUtils.getEntityManager();
        //2. 通过实体类管理器获取事务
        EntityTransaction tx = em.getTransaction();
        //3. 开启事务
        tx.begin();

        //4. 执行CRUD操作
        Customer customer = new Customer();
        customer.setCustName("内科大");
        customer.setCustIndustry("教育");
        em.persist(customer);

        //5. 提交事务
        tx.commit();
        //6. 释放资源，因为实体管理工厂是公共的所以不用释放
        em.close();
    }


    /**
     * 根据id查询用户
     *      find查询:
     *          1. 获取的对象就是本身Customer对象
     *          2. 调用find方法会立即发送sql语句到数据库
     *      立即加载
     */
    @Test
    public  void testFind(){
        //1. 通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. CRUD操作
        Customer customer = em.find(Customer.class, 1l);
        System.out.println(customer);

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();

    }

    /**
     * 根据id查询用户
     *      getReference方法：
     *          1. 获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句到数据库，当调用查询结果对象时，才会发送sql语句
     *      延迟加载（懒加载）
     */
    @Test
    public  void testReference(){
        //1. 通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. CRUD操作
        Customer customer1 = em.getReference(Customer.class, 1l);
        System.out.println(customer1);

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();

    }

    /**
     * 删除客户案例
     */
    @Test
    public  void testRemove(){
        //1. 通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. CRUD操作
        //3.1 根据id查询客户
        Customer customer = em.getReference(Customer.class, 1l);
        //3.2 删除查询出来的客户
        em.remove(customer);

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();

    }

    /**
     * 更新客户操作
     */
    @Test
    public  void testUpdate(){
        //1. 通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2. 开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3. CRUD操作
        //3.1 查询要修改的对象
        Customer customer = em.getReference(Customer.class, 1l);
        //3.2 修改查询出的对象 然后更新
        customer.setCustAddress("内蒙古包头市");
        em.merge(customer);

        //4. 提交事务
        tx.commit();
        //5. 释放资源
        em.close();

    }
}
