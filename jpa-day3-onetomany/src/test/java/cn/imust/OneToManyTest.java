package cn.imust;

import cn.imust.dao.CustomerDao;
import cn.imust.dao.LinkManDao;
import cn.imust.domain.Customer;
import cn.imust.domain.LinkMan;
import org.hibernate.annotations.Cascade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户
     * 保存一个联系人
     */
    @Test
    @Transactional
    @Rollback(value = false)    //springdatajpa中默认事务回滚
    public void testAdd(){

        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        //添加外键关系
        // 1.通过从表添加(配置了联系人到客户的关系) 只发送2条insert语句
        linkMan.setCustomer(customer);
        //2.通过主表配置(配置了客户到联系人的关系) 除了2条insert还有一条update， 此时可以让主表放弃对外键的维护权，参考Customer
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }


    /**
     * 测试：级联添加
     *      保存一个客户的同时保存说有联系人
     *    需要在操作主体的实体上配置Cascade属性
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeAdd(){

        Customer customer = new Customer();
        customer.setCustName("京东");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小高");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }


    /**
     * 测试：级联删除
     *      删除一个客户的同时删除所有联系人
     *    需要在操作主体的实体上配置Cascade属性
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeRemove(){

        //1.查询客户
        Customer customer = customerDao.findOne(1l);

        //2.删除客户
        customerDao.delete(customer);
    }
}
