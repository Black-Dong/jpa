package cn.imust;

import cn.imust.dao.CustomerDao;
import cn.imust.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 测试：动态查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     *  根据单个条件查询对象
     */
    @Test
    public void testSpec(){
        //匿名内部类 需要提供泛型，就是要查询的对象类型
        Specification<Customer> specification = new Specification<Customer>() {
            /**
             * 自定义查询条件
             *      eg：根据客户名称查询，查询客户客户名为？的客户
             *          查询条件
             * @param root  查询的根对象（查询的任何属性都可以从根对象中获取）
             * @param query 顶层查询对象，自定义查询方式（了解：一般不用）
             * @param cb    查询的构造器，封装了很多的查询条件（模糊，精准...)
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // 1. 获取比较的属性
                Path<Object> custName = root.get("custName");
                // 2. 构造查询条件
                // 参数1：要比较的属性（path对象） 参数2：传递的参数
                Predicate predicate = cb.equal(custName, "内科大");//进行精准匹配
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }


    /**
     * 根据多个条件查询对象
     *      eg: 根据客户名称和客户所属行业查询
     */
    @Test
    public void testSpeci(){

        Specification<Customer> specification = new Specification<Customer>() {
            // sql : select * from cst_customer where cust_name = ? and cust_industry = ?
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                // 1. 获取比较的属性
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");

                // 2. 构造查询条件
                // 参数1：要比较的属性（path对象） 参数2：传递的参数
                Predicate predicate1 = cb.equal(custName, "内科大");
                Predicate predicate2 = cb.equal(custIndustry, "教育");

                // 3.将多个查询条件组合到一起
                Predicate predicate = cb.and(predicate1, predicate2);   //满足 1 和 2 ，以与的形式拼接多个条件
//                Predicate predicate = cb.or(predicate1, predicate2);  //满足 1 或 2 ，以或的形式拼接多个条件
                return predicate;
            }
        };

        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 根据客户名称的模糊匹配，返回客户列表
     *
     * equal
     */
    @Test
    public void testSpecif(){

        Specification<Customer> specification = new Specification<Customer>() {
            // sql : select * from cst_customer where cust_name like ?
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");

                Predicate predicate = cb.like(custName.as(String.class), "内%");
                return predicate;
            }
        };

        List<Customer> customerList;
        // 模糊查询
//        customerList = customerDao.findAll(specification);

        // 添加排序
        // 创建排序对象，需要调用构造方法实例化对象
        // 第一个参数：排序的顺序  第二个参数：排序的属性名（根据什么排序）
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        customerList = customerDao.findAll(specification, sort);

        // 输出
        for (Customer customer : customerList){
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testSpecifi(){

        /**
         * 查询所有并分页
         *      findAll(Specification, Pageable) : 按Specification查询，再分页
         *      findAll(Pageable) : 查询全部再分页
         *    都返回一个Page对象（SpringDataJpa为我们封装好的PageBean对象，内含各种属性）
         */
        Specification<Customer> specification = null;
        //PageRequest是Pageable的接口实现类
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page = customerDao.findAll(pageable);

        System.out.println(page.getContent());  //得到数据集合列表
        System.out.println(page.getTotalElements());    //得到总条数
        System.out.println(page.getTotalPages());   //得到总页数
    }

}
