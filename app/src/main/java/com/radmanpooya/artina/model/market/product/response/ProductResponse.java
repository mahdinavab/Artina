package com.radmanpooya.artina.model.market.product.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponse{

	@SerializedName("ProductResponse")
	private List<ProductResponseItem> productResponse;

	public void setProductResponse(List<ProductResponseItem> productResponse){
		this.productResponse = productResponse;
	}

	public List<ProductResponseItem> getProductResponse(){
		return productResponse;
	}

	@Override
 	public String toString(){
		return 
			"ProductResponse{" + 
			"productResponse = '" + productResponse + '\'' + 
			"}";
		}
}