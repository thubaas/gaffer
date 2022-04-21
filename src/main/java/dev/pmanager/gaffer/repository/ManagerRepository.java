package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import dev.pmanager.gaffer.model.Manager;

@RestController
public interface ManagerRepository extends CrudRepository<Manager, String> {

}
