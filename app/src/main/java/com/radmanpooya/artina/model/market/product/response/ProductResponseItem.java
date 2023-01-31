package com.radmanpooya.artina.model.market.product.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponseItem{

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("create_time")
	private String createTime;

	@SerializedName("name_type")
	private String nameType;

	@SerializedName("days_left")
	private String daysLeft;

	@SerializedName("upc")
	private long upc;

	@SerializedName("weight")
	private int weight;

	@SerializedName("description")
	private String description;

	@SerializedName("brand_name")
	private String brandName;

	@SerializedName("title")
	private String title;

	@SerializedName("attr_value")
	private List<AttrValueItem> attrValue;

	@SerializedName("product_type")
	private int productType;

	@SerializedName("price")
	private int price;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private int category;

	@SerializedName("brand")
	private int brand;

	@SerializedName("user")
	private String user;

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return createTime;
	}

	public void setNameType(String nameType){
		this.nameType = nameType;
	}

	public String getNameType(){
		return nameType;
	}

	public void setDaysLeft(String daysLeft){
		this.daysLeft = daysLeft;
	}

	public String getDaysLeft(){
		return daysLeft;
	}

	public void setUpc(long upc){
		this.upc = upc;
	}

	public long getUpc(){
		return upc;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getWeight(){
		return weight;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setBrandName(String brandName){
		this.brandName = brandName;
	}

	public String getBrandName(){
		return brandName;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setAttrValue(List<AttrValueItem> attrValue){
		this.attrValue = attrValue;
	}

	public List<AttrValueItem> getAttrValue(){
		return attrValue;
	}

	public void setProductType(int productType){
		this.productType = productType;
	}

	public int getProductType(){
		return productType;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(int category){
		this.category = category;
	}

	public int getCategory(){
		return category;
	}

	public void setBrand(int brand){
		this.brand = brand;
	}

	public int getBrand(){
		return brand;
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
			"ProductResponseItem{" + 
			"images = '" + images + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",is_active = '" + isActive + '\'' + 
			",create_time = '" + createTime + '\'' + 
			",name_type = '" + nameType + '\'' + 
			",days_left = '" + daysLeft + '\'' + 
			",upc = '" + upc + '\'' + 
			",weight = '" + weight + '\'' + 
			",description = '" + description + '\'' + 
			",brand_name = '" + brandName + '\'' + 
			",title = '" + title + '\'' + 
			",attr_value = '" + attrValue + '\'' + 
			",product_type = '" + productType + '\'' + 
			",price = '" + price + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",brand = '" + brand + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}