package hcmute.services;

import java.util.List;

import hcmute.DAO.CategoryDAOimpl;
import hcmute.DAO.ICategotyDAO;
import hcmute.models.CategoryModels;

public class CategoryServiceImpl implements ICategotyService{
	//goi cac phuong thuc trong DAO
	ICategotyDAO cateDao = new CategoryDAOimpl();
	
	@Override
	public List<CategoryModels> findAll() {
		//Xu ly logic
		//goi va truyen cho DAO
		return cateDao.findAll();
	}

	@Override
	public void insert(CategoryModels model) {
		//xu ly logic
		//goi va truyen cho DAO
		cateDao.insert(model);
		
	}

	@Override
	public CategoryModels findOne(int id) {
		
		return cateDao.findOne(id);
	}

	@Override
	public void update(CategoryModels model) {
		CategoryModels oldModel = cateDao.findOne(model.getCateID());
		oldModel.setCateID(model.getCateID());
		oldModel.setCateName(model.getCateName());
		oldModel.setImages(model.getImages());
		
		cateDao.update(oldModel);
		
	}

	@Override
	public void delete(int id) {
		cateDao.delete(id);
		
	}
	
	
}
