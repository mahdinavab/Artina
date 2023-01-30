package com.radmanpooya.artina.model.user.response.verify;

import com.google.gson.annotations.SerializedName;

public class VerifyCodeResponse{

	@SerializedName("messege")
	private String messege;

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("refresh_token")
	private String refreshToken;

	@SerializedName("status")
	private String status;

	public void setMessege(String messege){
		this.messege = messege;
	}

	public String getMessege(){
		return messege;
	}

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"VerifyCodeResponse{" + 
			"messege = '" + messege + '\'' + 
			",access_token = '" + accessToken + '\'' + 
			",refresh_token = '" + refreshToken + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}