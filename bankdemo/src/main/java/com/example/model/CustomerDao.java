package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by rajat.arora on 2/2/2017.
 */
@RepositoryRestResource
@Transactional
public interface CustomerDao extends JpaRepository<Customer, Long>{

    Collection<Customer> findByName(@Param("cname") String cname);
}
