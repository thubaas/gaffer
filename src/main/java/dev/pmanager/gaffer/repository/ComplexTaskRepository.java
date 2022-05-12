package dev.pmanager.gaffer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.enums.Priority;
import dev.pmanager.gaffer.enums.Status;
import dev.pmanager.gaffer.model.ComplexTask;
import dev.pmanager.gaffer.model.Member;

@Repository
public interface ComplexTaskRepository extends CrudRepository<ComplexTask, String> {
	
	ComplexTask findByTitle(String title);
	List<ComplexTask> findAllByAssignee(Member assignee);
	List<ComplexTask> findAllByPriority(Priority priority);
	List<ComplexTask> findAllByStatus(Status status);
	
	

}
