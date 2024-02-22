package hcmute.models;

import java.io.Serializable;

public class CategoryModels implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//khai bao cac bien
	
	private int cateID;
	private String cateName;
	private String images;
	
	//tao ca constructor
	
	public CategoryModels() {
		super();
	}
	public CategoryModels(int cateID, String cateName, String images) {
		super();
		this.cateID = cateID;
		this.cateName = cateName;
		this.images = images;
	}
	
	//tao cac setter va getter
	
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	@Override
	public String toString() {
		return "CategoryModels [cateID=" + cateID + ", cateName=" + cateName + ", images=" + images + "]";
	}
	
	
	
	
	
	
	
	
}
