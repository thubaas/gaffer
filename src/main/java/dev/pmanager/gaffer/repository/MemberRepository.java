package dev.pmanager.gaffer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.pmanager.gaffer.model.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {

	Member findByEmail(String email);
}
