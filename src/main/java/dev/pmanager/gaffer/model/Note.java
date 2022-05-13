package dev.pmanager.gaffer.model;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Note {
	
	private String subjectl;
	private String content;
	private Map<String, String> data;
	private String image;

}
