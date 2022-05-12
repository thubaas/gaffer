package dev.pmanager.gaffer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Subtask;

@Repository
public interface SubtaskRepository extends CrudRepository<Subtask, String> {
	
	Subtask findByTitle(String title);
	List<Subtask> findAllByAssignee(Member assignee);
	List<Subtask> findAllByPriority(Priority priority);
	List<Subtask> findAllByStatus(Status status);

}
