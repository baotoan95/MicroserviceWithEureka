package com.common.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.common.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
