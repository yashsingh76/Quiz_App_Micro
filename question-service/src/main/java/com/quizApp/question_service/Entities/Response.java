package com.quizApp.question_service.Entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Response {
	
	private Integer id;
	private String response;

}
