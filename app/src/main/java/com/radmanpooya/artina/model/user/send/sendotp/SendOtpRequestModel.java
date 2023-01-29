package com.radmanpooya.artina.model.user.send.sendotp;

import com.google.gson.annotations.SerializedName;

public class SendOtpRequestModel{

	@SerializedName("mobile")
	private String mobile;

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	@Override
 	public String toString(){
		return 
			"SendOtpRequestModel{" + 
			"mobile = '" + mobile + '\'' + 
			"}";
		}
}