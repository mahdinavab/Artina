package com.radmanpooya.artina.model.education.section.response;

import com.google.gson.annotations.SerializedName;

public class SectionResponseItem{

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("learn")
	private Learn learn;

	@SerializedName("id")
	private int id;

	@SerializedName("is_free")
	private boolean isFree;

	@SerializedName("num_lesson")
	private int numLesson;

	@SerializedName("title")
	private String title;

	public SectionResponseItem(boolean isActive, Learn learn, int id, boolean isFree, int numLesson, String title) {
		this.isActive = isActive;
		this.learn = learn;
		this.id = id;
		this.isFree = isFree;
		this.numLesson = numLesson;
		this.title = title;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public void setLearn(Learn learn) {
		this.learn = learn;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFree(boolean free) {
		isFree = free;
	}

	public void setNumLesson(int numLesson) {
		this.numLesson = numLesson;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return isActive;
	}

	public Learn getLearn() {
		return learn;
	}

	public int getId() {
		return id;
	}

	public boolean isFree() {
		return isFree;
	}

	public int getNumLesson() {
		return numLesson;
	}

	public String getTitle() {
		return title;
	}
}