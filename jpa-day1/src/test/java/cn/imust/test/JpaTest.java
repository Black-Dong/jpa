package cn.imust.test;

import cn.imust.domain.Customer;
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
}
