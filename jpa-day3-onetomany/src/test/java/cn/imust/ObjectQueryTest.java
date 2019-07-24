package cn.imust;

import cn.imust.dao.CustomerDao;
import cn.imust.dao.LinkManDao;
import cn.imust.domain.Customer;
import cn.imust.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试对象导航查询（查询一个对象的时候，通过此对象查询所有的关联对象）
     *      对象导航查询：默认使用延迟加载的形式加载的 -- 调用get方法并不会立即加载查询，而是在使用关联对象的时候才会查询
     *      修改配置，可以将延迟加载改为立即加载
     *          fetch，需要配置到多表映射关系的注解上
     *              EAGER:立即加载(从多查一时的默认)
     *              LAZY:延迟加载(从以查多时的默认)
     */
    @Test
    @Transactional      //解决Java中的noSession问题
    @Rollback(false)
    public void testQuery1(){
        //查询客户
        Customer customer;
        customer = customerDao.getOne(1l);
//        customer = customerDao.findOne(1l);
        //对象导航查询此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();

        for (LinkMan linkMan : linkMans){
            System.out.println(linkMan);
        }
    }
}
