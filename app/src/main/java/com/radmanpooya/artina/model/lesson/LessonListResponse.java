package com.radmanpooya.artina.model.lesson;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LessonListResponse{

	@SerializedName("LessonListResponse")
	private List<LessonListResponseItem> lessonListResponse;

	public void setLessonListResponse(List<LessonListResponseItem> lessonListResponse){
		this.lessonListResponse = lessonListResponse;
	}

	public List<LessonListResponseItem> getLessonListResponse(){
		return lessonListResponse;
	}

	@Override
 	public String toString(){
		return 
			"LessonListResponse{" + 
			"lessonListResponse = '" + lessonListResponse + '\'' + 
			"}";
		}
}