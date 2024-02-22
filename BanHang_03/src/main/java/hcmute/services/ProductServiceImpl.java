package hcmute.services;

import java.util.List;

import hcmute.DAO.ProductDAOimpl;
import hcmute.DAO.IProductDAO;
import hcmute.models.ProductModel;

public class ProductServiceImpl implements IProductService{
	//goi cac phuong thuc trong DAO
	IProductDAO proDao = new ProductDAOimpl();
	
	@Override
	public List<ProductModel> findAll() {
		//Xu ly logic
		//goi va truyen cho DAO
		return proDao.findAll();
	}
	public List<ProductModel> findProductByCategory(int id) {
		//Xu ly logic
		//goi va truyen cho DAO
		return proDao.findProductByCategory(id);
	}
	@Override
	public void insert(ProductModel model) {
		proDao.insert(model);
		
	}
	@Override
	public void update(ProductModel model) {
		
		ProductModel newPro = proDao.findOne(model.getProductID());
		newPro.setProductName(model.getProductName());
		newPro.setDescription(model.getDescription());
		newPro.setPrice(model.getPrice());
		newPro.setImageLink(model.getImageLink());
		newPro.setCategory(model.getCategory());
		newPro.setStoke(model.getStoke());
		
		
		proDao.update(newPro);
		
	}
	@Override
	public ProductModel findOne(int id) {
		return proDao.findOne(id);
	}
}
