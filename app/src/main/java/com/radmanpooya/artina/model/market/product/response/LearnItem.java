package com.radmanpooya.artina.model.market.product.response;

import com.google.gson.annotations.SerializedName;

public class LearnItem{

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private int price;

	@SerializedName("auther")
	private String auther;

	@SerializedName("title")
	private String title;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setAuther(String auther){
		this.auther = auther;
	}

	public String getAuther(){
		return auther;
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
			"LearnItem{" + 
			"image = '" + image + '\'' + 
			",price = '" + price + '\'' + 
			",auther = '" + auther + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}