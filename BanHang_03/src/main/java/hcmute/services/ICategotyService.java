package hcmute.services;

import java.util.List;

import hcmute.models.CategoryModels;

public interface ICategotyService {
	//dinh nghia cac phuong thuc
	List<CategoryModels> findAll();
	
	void insert(CategoryModels model);
	
	CategoryModels findOne(int id);
	
	void update(CategoryModels model);
	void delete(int id);
}
