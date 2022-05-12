package dev.pmanager.gaffer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.model.Manager;
import dev.pmanager.gaffer.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {
	
	List<Project> findByManager(Manager manager);
	List<Project> findByStatus(Status status);

}
