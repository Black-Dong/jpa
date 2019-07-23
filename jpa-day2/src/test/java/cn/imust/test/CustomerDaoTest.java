package cn.imust.test;

import cn.imust.dao.CustomerDao;
import cn.imust.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 测试统计查询，查询客户总数量
     *      count: 统计总条数
     */
    @Test
    public void testCount(){
        long count = customerDao.count(); //查询全部客户返回值
        System.out.println(count);
    }

    /**
     *  测试：判断id为？的客户是否存在
     *      1. 可以根据id查询用户
     *      2. 查询根据id查询数量
     */
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(4l);
        System.out.println(exists);
    }

    /**
     *  根据id查询用户
     *      使用getOne方法需要加上@Transactional 事务，保证getOne可以正常运行
     *
     *  findOne:
     *      底层调用 em.find()          立即加载
     *  getOne:
     *      底层调用 em.getReference    延迟加载
     *      * 返回一个客户动态代理对象
     *      * 什么时候使用，什么时候进行查询
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(1l);
        System.out.println(customer);
    }
}
