package hcmute.DAO;

import java.util.List;

import hcmute.models.ProductModel;

public interface IProductDAO {
	// dinh nghia cac phuong thuc
	List<ProductModel> findAll();
	List<ProductModel> findProductByCategory(int cateid);
	
	void insert(ProductModel model);
	
	void update(ProductModel model);
	ProductModel findOne(int id);
}
