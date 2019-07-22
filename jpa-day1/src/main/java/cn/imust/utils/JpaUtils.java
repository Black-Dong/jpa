package cn.imust.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理类工厂的浪费资源和耗时问题
 *      通过静态代码块的形式，当程序第一次访问此工具类时创建一个公共的EntityManagerFactory对象
 *
 * 第一次访问getEntityManager方法，经过静态代码块创建一个factory对象，再调用方法创建一个EntityManager对象并返回、
 * 第二次访问getEntityManager方法，直接通过一个已经创建好的factory对象，直接创建一个EntityManager对象并返回
 */
public class JpaUtils {

    private static EntityManagerFactory factory;

    static {
        //1. 加载配置文件，创建EntityManagerFactory
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 获取EntityManager实体对象
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
