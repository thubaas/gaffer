package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {

}
