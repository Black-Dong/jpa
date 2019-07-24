package cn.imust.test;

import cn.imust.dao.RoleDao;
import cn.imust.dao.UserDao;
import cn.imust.domain.Role;
import cn.imust.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    /**
     * 保存一个用户，一个角色
     *
     * 多对多放弃维护权：被动的一方放弃维护权 eg：用户选择角色，放弃角色的维护权
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){

        User user = new User();
        user.setUserName("小李");
        Role role = new Role();
        role.setRoleName("Java开发");

        //配置用户到角色的关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);

        //配置角色到用户的关系，可以对中间表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }


    /**
     * 测试级联添加（保存一个用户的同时，保存用户的关联角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCusCadeAdd(){

        User user = new User();
        user.setUserName("小李");
        Role role = new Role();
        role.setRoleName("Java开发");

        //配置用户到角色的关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);

        //配置角色到用户的关系，可以对中间表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);
//        roleDao.save(role);   添加了级联
    }

    /**
     * 测试级联删除（删除一个用户的同时，删除用户的关联角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCusCadeRemove(){

        userDao.delete(1l);
    }
}
