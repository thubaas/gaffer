package dev.pmanager.gaffer.dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
	
	private String id;
	private String title;
	private String startDate;
	private String finishDate;
	private String status;
	private String managerId;
	private Collection<ComplexTaskDto> tasks = new ArrayList<>();

}
