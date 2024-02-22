package hcmute.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import hcmute.models.CategoryModels;

public class CategoryDAOimpl implements ICategotyDAO{

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<CategoryModels> findAll() {
		//xu ly du lieu hien thi tat ca category
		List<CategoryModels> listcate = new ArrayList<CategoryModels>();
		String sql = "Select * from category";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModels category = new CategoryModels();
				category.setCateID(rs.getInt("CategoryID"));
				category.setCateName(rs.getString("CategoryName"));
				category.setImages(rs.getString("icon"));
				listcate.add(category);
			}
			//ket noi du lieu
			// truy van du lieu
			// chuyen du liieu ra view
			// view nao se nhan du lieu
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listcate;
	}

	@Override
	public void insert(CategoryModels model) {
		//xu ly them category
		String sql = "INSERT INTO category(CategoryName, icon) VALUES (?,?)";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			//gan gia tri cho tham so 
			ps.setString(1, model.getCateName());
			ps.setString(2, model.getImages());
			ps.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CategoryModels findOne(int id) {
		String sql = "select *from category where CategoryID = ?";
		CategoryModels model = new CategoryModels();
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				model.setCateID(rs.getInt("CategoryID"));
				model.setCateName(rs.getString("CategoryName"));
				model.setImages(rs.getString("Icon"));
			}
			conn.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public void update(CategoryModels model) {
		String sql = "update category set CategoryName = ?, icon = ? where CategoryID = ?";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			//gan gia tri cho tham so 
			ps.setString(1, model.getCateName());
			ps.setString(2, model.getImages());
			ps.setInt(3, model.getCateID());
			
			ps.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		
		String sql = "delete from category where CategoryID = ?";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			//gan gia tri cho tham so 
			ps.setInt(1, id);
			
			ps.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
