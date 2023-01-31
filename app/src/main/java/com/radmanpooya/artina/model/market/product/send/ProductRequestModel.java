package com.radmanpooya.artina.model.market.product.send;

import com.google.gson.annotations.SerializedName;

public class ProductRequestModel{

	@SerializedName("sortby")
	private String sortby;

	@SerializedName("type")
	private String type;

	public void setSortby(String sortby){
		this.sortby = sortby;
	}

	public String getSortby(){
		return sortby;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"ProductRequestModel{" + 
			"sortby = '" + sortby + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}