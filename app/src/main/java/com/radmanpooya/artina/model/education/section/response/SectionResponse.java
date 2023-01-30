package com.radmanpooya.artina.model.education.section.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SectionResponse{

	@SerializedName("SectionResponse")
	private List<SectionResponseItem> sectionResponse;

	public void setSectionResponse(List<SectionResponseItem> sectionResponse){
		this.sectionResponse = sectionResponse;
	}

	public List<SectionResponseItem> getSectionResponse(){
		return sectionResponse;
	}

	@Override
 	public String toString(){
		return 
			"SectionResponse{" + 
			"sectionResponse = '" + sectionResponse + '\'' + 
			"}";
		}
}