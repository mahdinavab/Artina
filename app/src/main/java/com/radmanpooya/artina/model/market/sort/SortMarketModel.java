package com.radmanpooya.artina.model.market.sort;

import com.google.gson.annotations.SerializedName;

public class SortMarketModel {

	@SerializedName("id")
	private int id;

	@SerializedName("sort_value")
	private String sortValue;

	@SerializedName("icon_resource")
	private int iconResource;

	@SerializedName("title")
	private String title;

	public SortMarketModel(int id, String sortValue, int iconResource, String title) {
		this.id = id;
		this.sortValue = sortValue;
		this.iconResource = iconResource;
		this.title = title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

	public void setIconResource(int iconResource) {
		this.iconResource = iconResource;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getSortValue() {
		return sortValue;
	}

	public int getIconResource() {
		return iconResource;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "SortMarketModel{" +
				"id=" + id +
				", sortValue='" + sortValue + '\'' +
				", iconResource=" + iconResource +
				", title='" + title + '\'' +
				'}';
	}
}