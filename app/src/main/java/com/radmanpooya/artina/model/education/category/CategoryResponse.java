package com.radmanpooya.artina.model.education.category;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse{

	@SerializedName("CategoryResponse")
	private List<CategoryResponseItem> categoryResponse;

	public void setCategoryResponse(List<CategoryResponseItem> categoryResponse){
		this.categoryResponse = categoryResponse;
	}

	public List<CategoryResponseItem> getCategoryResponse(){
		return categoryResponse;
	}

	@Override
 	public String toString(){
		return 
			"CategoryResponse{" + 
			"categoryResponse = '" + categoryResponse + '\'' + 
			"}";
		}
}