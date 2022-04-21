package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.ComplexTask;

@Repository
public interface ComplexTaskRepository extends CrudRepository<ComplexTask, String> {

}
