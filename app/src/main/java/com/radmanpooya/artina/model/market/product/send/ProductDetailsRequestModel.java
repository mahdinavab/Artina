package com.radmanpooya.artina.model.market.product.send;

import com.google.gson.annotations.SerializedName;

public class ProductDetailsRequestModel{

	@SerializedName("id")
	private int id;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ProductDetailsRequestModel{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}