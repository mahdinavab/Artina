package com.radmanpooya.artina.model.market.product.response;

import com.google.gson.annotations.SerializedName;

public class LearnItem{

	@SerializedName("id")
	private int id;

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private int price;

	@SerializedName("auther")
	private String auther;

	@SerializedName("title")
	private String title;

	public LearnItem(int id, String image, int price, String auther, String title) {
		this.id = id;
		this.image = image;
		this.price = price;
		this.auther = auther;
		this.title = title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public int getPrice() {
		return price;
	}

	public String getAuther() {
		return auther;
	}

	public String getTitle() {
		return title;
	}
}