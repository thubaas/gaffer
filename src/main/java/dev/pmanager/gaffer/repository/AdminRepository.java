package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

}
