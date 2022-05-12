package dev.pmanager.gaffer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskDto {
	private String id;
	private String title;
	private String description;
	private String startDate;
	private String finishDate;
	private String assigneeId;
	private String priority;
	private String precedentId;
	private String status;
	private String parentId;

}
