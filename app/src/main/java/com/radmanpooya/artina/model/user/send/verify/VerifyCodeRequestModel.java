package com.radmanpooya.artina.model.user.send.verify;

import com.google.gson.annotations.SerializedName;

public class VerifyCodeRequestModel{

	@SerializedName("code")
	private String code;

	@SerializedName("mobile")
	private String mobile;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	@Override
 	public String toString(){
		return 
			"VerifyCodeRequestModel{" + 
			"code = '" + code + '\'' + 
			",mobile = '" + mobile + '\'' + 
			"}";
		}
}