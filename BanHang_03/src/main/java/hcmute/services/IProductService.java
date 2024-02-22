package hcmute.services;

import java.util.List;

import hcmute.models.ProductModel;

public interface IProductService {
	// dinh nghia cac phuong thuc
	List<ProductModel> findAll();

	List<ProductModel> findProductByCategory(int cateid);

	void insert(ProductModel model);

	void update(ProductModel model);
	ProductModel findOne(int id);
}
