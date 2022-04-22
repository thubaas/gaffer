package dev.pmanager.gaffer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskDto extends TaskDto{
	private TaskDto parentTask;

}
