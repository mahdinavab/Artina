package com.radmanpooya.artina.model.education.section.response;

import com.google.gson.annotations.SerializedName;

public class Learn{

	@SerializedName("image")
	private String image;

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("price")
	private int price;

	@SerializedName("is_free")
	private boolean isFree;

	@SerializedName("auther")
	private String auther;

	@SerializedName("id")
	private int id;

	@SerializedName("num_section")
	private int numSection;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private int type;

	@SerializedName("user")
	private String user;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setIsFree(boolean isFree){
		this.isFree = isFree;
	}

	public boolean isIsFree(){
		return isFree;
	}

	public void setAuther(String auther){
		this.auther = auther;
	}

	public String getAuther(){
		return auther;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNumSection(int numSection){
		this.numSection = numSection;
	}

	public int getNumSection(){
		return numSection;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Learn{" + 
			"image = '" + image + '\'' + 
			",is_active = '" + isActive + '\'' + 
			",price = '" + price + '\'' + 
			",is_free = '" + isFree + '\'' + 
			",auther = '" + auther + '\'' + 
			",id = '" + id + '\'' + 
			",num_section = '" + numSection + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}