package dev.pmanager.gaffer.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.dto.TeamDto;
import dev.pmanager.gaffer.mapper.TeamMapper;
import dev.pmanager.gaffer.model.Member;
import dev.pmanager.gaffer.model.Project;
import dev.pmanager.gaffer.model.Team;
import dev.pmanager.gaffer.notification.EmailSender;
import dev.pmanager.gaffer.notification.MailModel;
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
	private final ProjectRepository projectRepository;
	private final EmailSender emailSender;
	
	@Override
	public TeamDto addMember(String teamId, MemberDto memberDto) throws AddressException, MessagingException {
		log.info("Adding Member : {}", memberDto);
		
		Team team = teamRepository.findById(teamId).get();
		Member member = memberRepository.findByEmail(memberDto.getEmail());
		
		if(member == null) {
			log.info("Member not Registered, send Invitation to Email : {}", memberDto.getEmail());
			MailModel mailModel = new MailModel();
			mailModel.setTo(memberDto.getEmail());
			mailModel.setSubject("INVITATION TO JOIN NEW TEAM");
			mailModel.setBody(
					"You are being invited to download the "
					+ "PM Application and join the team using ID " + team.getId()
					);
			emailSender.sendMail(mailModel);
			return teamMapper.map(team);
		}
		
		if(member.getTeam() == null) {
			member.setTeam(team);
			Member savedMember = memberRepository.save(member);
			team.getMembers().add(savedMember);
			Team savedTeam = teamRepository.save(team);
			TeamDto mappedTeam = teamMapper.map(savedTeam);
			return mappedTeam;
		}	
		
		return teamMapper.map(team);
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
