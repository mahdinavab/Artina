package com.radmanpooya.artina.model.education.category;

import com.google.gson.annotations.SerializedName;

public class CategoryResponseItem{

	@SerializedName("image")
	private String image;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("num_learn")
	private int numLearn;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setNumLearn(int numLearn){
		this.numLearn = numLearn;
	}

	public int getNumLearn(){
		return numLearn;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponseItem{" + 
			"image = '" + image + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",num_learn = '" + numLearn + '\'' + 
			"}";
		}
}