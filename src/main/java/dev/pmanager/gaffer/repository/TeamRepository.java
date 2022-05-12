package dev.pmanager.gaffer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
	
	List<Team> findByProject(Project project);

}
