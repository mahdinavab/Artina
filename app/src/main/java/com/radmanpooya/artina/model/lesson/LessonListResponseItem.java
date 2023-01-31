package com.radmanpooya.artina.model.lesson;

import com.google.gson.annotations.SerializedName;

public class LessonListResponseItem{

	@SerializedName("voice")
	private String voice;

	@SerializedName("image")
	private String image;

	@SerializedName("three1")
	private String three1;

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("three2")
	private String three2;

	@SerializedName("is_free")
	private boolean isFree;

	@SerializedName("description")
	private String description;

	@SerializedName("section")
	private Section section;

	@SerializedName("id")
	private int id;

	@SerializedName("video")
	private String video;

	@SerializedName("title")
	private String title;

	public void setVoice(String voice){
		this.voice = voice;
	}

	public String getVoice(){
		return voice;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setThree1(String three1){
		this.three1 = three1;
	}

	public String getThree1(){
		return three1;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setThree2(String three2){
		this.three2 = three2;
	}

	public String getThree2(){
		return three2;
	}

	public void setIsFree(boolean isFree){
		this.isFree = isFree;
	}

	public boolean isIsFree(){
		return isFree;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSection(Section section){
		this.section = section;
	}

	public Section getSection(){
		return section;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVideo(String video){
		this.video = video;
	}

	public String getVideo(){
		return video;
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
			"LessonListResponseItem{" + 
			"voice = '" + voice + '\'' + 
			",image = '" + image + '\'' + 
			",three1 = '" + three1 + '\'' + 
			",is_active = '" + isActive + '\'' + 
			",three2 = '" + three2 + '\'' + 
			",is_free = '" + isFree + '\'' + 
			",description = '" + description + '\'' + 
			",section = '" + section + '\'' + 
			",id = '" + id + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}