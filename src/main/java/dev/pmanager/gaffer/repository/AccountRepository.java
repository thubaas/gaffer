package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String>{

}
