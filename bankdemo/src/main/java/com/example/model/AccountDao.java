package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by rajat.arora on 2/3/2017.
 */
@RepositoryRestResource
@Transactional
public interface AccountDao extends JpaRepository<Account, Long> {

}
