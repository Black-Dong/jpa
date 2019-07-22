package cn.imust.test;

import cn.imust.dao.CustomerDao;
import cn.imust.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)     //声明Spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")   //指定Spring容器的配置信息
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(1l);
        System.out.println(customer);
    }

    /**
     * save: 保存或更新
     *      根据传递的对象是否有id属性
     *          有id：更新
     *          无id：保存
     */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("呼和佳地");
        customer.setCustLevel("svip");
        customer.setCustIndustry("住房");
        customerDao.save(customer);
    }
    @Test
    public void testSave2(){
        //直接给id然后调用save方法修改只会保存id和修改过的内容，原有内容会丢失，推荐使用先查询，再save
//        Customer customer = new Customer();
//        customer.setCustId(6l);

        Customer customer = customerDao.findOne(1l);

        customer.setCustAddress("内蒙古呼和浩特市");
        customer.setCustLevel("vip");
        customer.setCustName("山水小区");
        customer.setCustIndustry("居民楼");
        customerDao.save(customer);
    }

    /**
     * delete: 删除操作
     */
    @Test
    public void testDelete(){
        customerDao.delete(1l);
    }

    /**
     * findAll: 查询所有
     */
    @Test
    public void testFindAll(){
        List<Customer> customerList = customerDao.findAll();
        for (Customer customer : customerList){
            System.out.println(customer);
        }
    }
}
