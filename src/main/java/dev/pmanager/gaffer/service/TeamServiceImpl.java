package dev.pmanager.gaffer.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.dto.TeamDto;
import dev.pmanager.gaffer.mapper.MemberMapper;
import dev.pmanager.gaffer.mapper.TeamMapper;
import dev.pmanager.gaffer.model.Leader;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;
import dev.pmanager.gaffer.notification.EmailSender;
import dev.pmanager.gaffer.notification.MailModel;
import dev.pmanager.gaffer.repository.LeaderRepository;
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
	private final LeaderRepository leaderRepository;
	private final TeamMapper teamMapper;
	private final MemberMapper memberMapper;
	private final ProjectRepository projectRepository;
	private final EmailSender emailSender;
	
	@Override
	public TeamDto addTeam(TeamDto teamDto) {
		log.info("Creating Team : {}", teamDto);
		
		Leader leader = leaderRepository.findById(teamDto.getLeaderId()).get();
		
		Team team = teamMapper.map(teamDto);
		team.setLeader(leader);
		
		Team savedTeam = teamRepository.save(team);
		
		leader.setTeam(savedTeam);
		leaderRepository.save(leader);
		//TODO: Push notification to alert team leader 
		return teamMapper.map(savedTeam);
	}
	
	@Override
	public TeamDto addMembers(String teamId, List<MemberDto> memberDtos) {
		log.info("Adding Members : {}", memberDtos);
		
		Team team = teamRepository.findById(teamId).get();
		List<Member> members = memberDtos.stream().map(mDto -> {
			Member savedMember = memberRepository.findById(mDto.getId()).get();
			savedMember.setTeam(team);
			return memberRepository.save(savedMember);
		}).collect(Collectors.toList());
		
		team.setMembers(members);
		Team updatedTeam = teamRepository.save(team);
		//TODO: Push notification to alert team members
		return teamMapper.map(updatedTeam);
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
