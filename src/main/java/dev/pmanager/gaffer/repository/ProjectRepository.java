package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

}
