package cn.imust.test;

import cn.imust.dao.CustomerDao;
import cn.imust.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)     //声明Spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")   //指定Spring容器的配置信息
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试：根据客户名称查询数据
     */
    @Test
    public void testFindJpql(){
        List<Customer> customerList = customerDao.findJpql("内科大");
        for (Customer customer : customerList){
            System.out.println(customer);
        }
    }

    /**
     * 根据客户名称和id查询
     */
    @Test
    public void testFindCustomerByNameAndId(){
        Customer customer = customerDao.findCustomerByNameAndId("内科大", 2l);
        System.out.println(customer);
    }

    /**
     * 测试： jpql的更新操作
     *      * SpringDataJpa中使用jpql进行更新/删除
     *          * 需要手动添加事务
     *          * 默认执行结束之后，回滚事务 设置@Rollback不自动回滚
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer(){
        customerDao.updateCustomer(3l,"内工大");
    }
}
