package cn.imust.dao;

import cn.imust.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 联系人的dao接口
 */
public interface LinkManDao extends JpaRepository<LinkManDao,Long>, JpaSpecificationExecutor<LinkManDao> {
}
