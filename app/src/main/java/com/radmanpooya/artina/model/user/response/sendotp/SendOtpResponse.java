package com.radmanpooya.artina.model.user.response.sendotp;

import com.google.gson.annotations.SerializedName;

public class SendOtpResponse{

	@SerializedName("messege")
	private String messege;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setMessege(String messege){
		this.messege = messege;
	}

	public String getMessege(){
		return messege;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"SendOtpResponse{" + 
			"messege = '" + messege + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}