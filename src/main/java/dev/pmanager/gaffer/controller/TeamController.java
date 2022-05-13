package dev.pmanager.gaffer.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pmanager.gaffer.dto.MemberDto;
import dev.pmanager.gaffer.dto.TeamDto;
import dev.pmanager.gaffer.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/gaffer/teams")
public class TeamController {
	
	private final TeamService teamService;
	
	@GetMapping("/{teamId}")
	public ResponseEntity<TeamDto> getTeam(@PathVariable String teamId) {
		log.info("Retrieving Team ID : {}", teamId);
		TeamDto teamDto = teamService.getTeam(teamId);
		return ResponseEntity.ok(teamDto);
	}
	
	@PostMapping
	public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
		log.info("Adding Team : {}", teamDto);
		TeamDto savedTeam = teamService.addTeam(teamDto);
		return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<TeamDto> updateTeam(TeamDto teamDto) {
		log.info("Update Team : {}", teamDto);
		TeamDto savedTeam = teamService.updateTeam(teamDto);
		return new ResponseEntity<>(savedTeam, HttpStatus.OK);
	}
	
	@PostMapping("/{teamId}/members")
	public ResponseEntity<TeamDto> addMembers(@PathVariable String teamId, @RequestBody List<MemberDto> memberDtos) throws AddressException, MessagingException {
		log.info("Adding Members");
		TeamDto savedTeam = teamService.addMembers(teamId, memberDtos);
		return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
	}
	
	@PatchMapping("/{teamId}/members/{memberId}")
	public ResponseEntity<TeamDto> removeMember(@PathVariable String teamId, @PathVariable String memberId) {
		log.info("Removing Member ID : {} from Team ID : {}", memberId, teamId);
		TeamDto teamDto = teamService.removeMember(teamId, memberId);
		return ResponseEntity.ok(teamDto);
	}
	
	@DeleteMapping("/{teamId}")
	public ResponseEntity<String> deleteTeam(@PathVariable String teamId) {
		log.info("Deleting Team : {}", teamId);
		teamService.deleteTeam(teamId);
		return ResponseEntity.ok("Team Deleted Successfully");
	}
	
	

}
