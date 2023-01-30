package com.radmanpooya.artina.model.education.section.response;

import com.google.gson.annotations.SerializedName;

public class SectionResponseItem{

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("learn")
	private Learn learn;

	@SerializedName("is_free")
	private boolean isFree;

	@SerializedName("num_lesson")
	private int numLesson;

	@SerializedName("title")
	private String title;

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setLearn(Learn learn){
		this.learn = learn;
	}

	public Learn getLearn(){
		return learn;
	}

	public void setIsFree(boolean isFree){
		this.isFree = isFree;
	}

	public boolean isIsFree(){
		return isFree;
	}

	public void setNumLesson(int numLesson){
		this.numLesson = numLesson;
	}

	public int getNumLesson(){
		return numLesson;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"SectionResponseItem{" + 
			"is_active = '" + isActive + '\'' + 
			",learn = '" + learn + '\'' + 
			",is_free = '" + isFree + '\'' + 
			",num_lesson = '" + numLesson + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}