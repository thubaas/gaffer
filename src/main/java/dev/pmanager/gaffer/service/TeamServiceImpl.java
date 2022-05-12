package dev.pmanager.gaffer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.dto.TeamDto;
import dev.pmanager.gaffer.mapper.MemberMapper;
import dev.pmanager.gaffer.mapper.TeamMapper;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;
import dev.pmanager.gaffer.repository.MemberRepository;
import dev.pmanager.gaffer.repository.ProjectRepository;
import dev.pmanager.gaffer.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
	
	private final TeamRepository teamRepository;
	private final MemberRepository memberRepository;
	private final TeamMapper teamMapper;
	private final MemberMapper memberMapper;
	private final ProjectRepository projectRepository;
	
	@Override
	public TeamDto addMember(String teamId, MemberDto memberDto) {
		log.info("Adding Member : {}", memberDto);
		Member member = memberMapper.map(memberDto);
		Member savedMember = memberRepository.save(member);
		Team team = teamRepository.findById(teamId).get();
		team.getMembers().add(savedMember);
		Team savedTeam = teamRepository.save(team);
		TeamDto mappedTeam = teamMapper.map(savedTeam);
		return mappedTeam;
	}

	@Override
	public TeamDto addTeam(TeamDto teamDto) {
		log.info("Creating Team : {}", teamDto);
		Team team = teamMapper.map(teamDto);
		Team savedTeam = teamRepository.save(team);
		return teamMapper.map(savedTeam);
	}

	@Override
	public TeamDto getTeam(String teamId) {
		Team team = teamRepository.findById(teamId).get();
		TeamDto mappedTeam = teamMapper.map(team);
		log.info("Retrieved Team : {}", mappedTeam);
		return mappedTeam;
	}

	@Override
	public List<TeamDto> getTeamsByProject(String projectId) {
		Project project = projectRepository
				.findById(projectId)
				.get();
		List<Team> teams = teamRepository.findByProject(project);
		List<TeamDto> mappedTeams = teams
				.stream()
				.map(team -> teamMapper.map(team))
				.collect(Collectors.toList());
		log.info("Retried Teams by Project");
		return mappedTeams;
	}

	@Override
	public boolean deleteTeam(String teamId) {
		teamRepository.deleteById(teamId);
		return true;
	}

	@Override
	public TeamDto removeMember(String teamId, String memberId) {
		log.info("Removing Member ID : {} from Team : {}", memberId, teamId);
		Team team = teamRepository.findById(teamId).get();
		List<Member> newMembers = team
				.getMembers()
				.stream()
				.filter(m -> m.getId().equals(memberId))
				.collect(Collectors.toList());
		team.setMembers(newMembers);
		Team savedTeam = teamRepository.save(team);
		TeamDto mappedTeam = teamMapper.map(savedTeam);
		return mappedTeam;
	}

	@Override
	public TeamDto updateTeam(TeamDto teamDto) {
		Team team = teamMapper.map(teamDto);
		Team savedTeam = teamRepository.save(team);
		TeamDto mappedTeam = teamMapper.map(savedTeam);
		log.info("Updated Team : {}", mappedTeam);
		return mappedTeam;
	}

}
