package com.radmanpooya.artina.model.education.learn;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LearnResponse{

	@SerializedName("LearnResponse")
	private List<LearnResponseItem> learnResponse;

	public void setLearnResponse(List<LearnResponseItem> learnResponse){
		this.learnResponse = learnResponse;
	}

	public List<LearnResponseItem> getLearnResponse(){
		return learnResponse;
	}

	@Override
 	public String toString(){
		return 
			"LearnResponse{" + 
			"learnResponse = '" + learnResponse + '\'' + 
			"}";
		}
}