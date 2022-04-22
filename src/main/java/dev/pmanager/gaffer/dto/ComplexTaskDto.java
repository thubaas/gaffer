package dev.pmanager.gaffer.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplexTaskDto extends TaskDto {
	private Collection<TaskDto> subTasks; 

}
