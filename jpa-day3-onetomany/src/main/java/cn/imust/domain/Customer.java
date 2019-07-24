package cn.imust.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. 实体类和表的映射关系
 *      @Entity
 *      @Table
 * 2. 类中属性和表中字段的映射关系
 *      @Id
 *      @GeneratedValue
 *      @Column
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //客户主键

    @Column(name = "cust_name")
    private String custName; //客户名称

    @Column(name = "cust_source")
    private String custSource; //客户来源

    @Column(name = "cust_level")
    private String custLevel; //客户级别

    @Column(name = "cust_industry")
    private String custIndustry; //客户所属行业

    @Column(name = "cust_phone")
    private String custPhone; //客户联系方式

    @Column(name = "cust_address")
    private String custAddress; //客户地址

    //配置客户和联系人之间的关系（一对多）
    /**
     * 使用注解的形式配置多表关系
     *      1. 声明关系
     *          @OneToMany : 配置一堆多关系
     *              targetEntity : 对方对象的字节码对象
     *      2. 配置外键（或中间表）
     *          @JoinColumn : 配置外键
     *              name : 外键字段名称（数据库中）
     *              referencedColumnName : 参照的主表的字段名称
     *   * 在配置外键时，在主表的一方添加了外键配置，对于主表而言，他也具备了维护外键的作用
     *   * 但是这样会使保存时多执行一次update，所以最好放弃主表对外键的维护权 （配置mapperBy属性）
     *   * 在@OneToMany中添加cascade属性开启级联
     */
//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)   //该customer是从表的属性，表示主表对放弃对外键的维护，参照从表customer属性
    private Set<LinkMan> linkMans = new HashSet<>();

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
