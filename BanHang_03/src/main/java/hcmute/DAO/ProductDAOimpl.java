package hcmute.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hcmute.models.CategoryModels;
import hcmute.models.ProductModel;

public class ProductDAOimpl implements IProductDAO{

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ICategotyDAO cateDAO = new CategoryDAOimpl();	
	@Override
	public List<ProductModel> findAll() {
		//xu ly du lieu hien thi tat ca category
		List<ProductModel> listcate = new ArrayList<ProductModel>();
		String sql = "Select * from Product";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt("ProductID"));
				product.setProductName(rs.getString("ProductName"));
				product.setDescription(rs.getString("Description"));
				product.setPrice(rs.getInt("Price"));
				product.setImageLink(rs.getString("imageLink"));
				product.setCategoryID(rs.getInt("CategoryID"));
				product.setSellerID(rs.getInt("SellerID"));
				product.setAmount(rs.getInt("Amount"));
				product.setStoke(rs.getInt("stoke"));
				
				listcate.add(product);
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
	public List<ProductModel> findProductByCategory(int cateid) {
		//xu ly du lieu hien thi tat ca category
		List<ProductModel> listcate = new ArrayList<ProductModel>();
		String sql = "Select * from Product where CategoryID = ?";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cateid);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModels model1 = cateDAO.findOne(rs.getInt("CategoryID"));
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt("ProductID"));
				product.setProductName(rs.getString("ProductName"));
				product.setDescription(rs.getString("Description"));
				product.setPrice(rs.getInt("Price"));
				product.setImageLink(rs.getString("imageLink"));
				product.setCategoryID(model1.getCateID());
				product.setSellerID(rs.getInt("SellerID"));
				product.setAmount(rs.getInt("Amount"));
				product.setStoke(rs.getInt("stoke"));
				
				listcate.add(product);
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
	public void insert(ProductModel model) {
		String sql = "INSERT INTO Product(ProductName, Description, Price, imageLink, CategoryID, stoke) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = new DBConnectionSQL().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProductName());
			ps.setString(2, model.getDescription());
			ps.setInt(3, model.getPrice());
			ps.setString(4, model.getImageLink());
			ps.setInt(5, model.getCategory().getCateID());
			ps.setInt(6, model.getStoke());
			
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(ProductModel model) {
		String sql = "UPDATE Product set ProductName = ?, Description = ?, Price = ?, imageLink = ?, CategoryID = ?, stoke = ? where ProductID = ?";
		try {
			Connection conn = new DBConnectionSQL().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProductName());
			ps.setString(2, model.getDescription());
			ps.setInt(3, model.getPrice());
			ps.setString(4, model.getImageLink());
			ps.setInt(5, model.getCategory().getCateID());
			ps.setInt(6, model.getStoke());
			ps.setInt(7, model.getProductID());
			
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public ProductModel findOne(int id) {
		String sql = "SELECT * FROM Product WHERE ProductID = ?";
		ProductModel product = new ProductModel();
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModels model1 = cateDAO.findOne(rs.getInt("CategoryID"));
				product.setProductID(rs.getInt("ProductID"));
				product.setProductName(rs.getString("ProductName"));
				product.setDescription(rs.getString("Description"));
				product.setPrice(rs.getInt("Price"));
				product.setImageLink(rs.getString("imageLink"));
				product.setCategoryID(model1.getCateID());
				product.setSellerID(rs.getInt("SellerID"));
				product.setAmount(rs.getInt("Amount"));
				product.setStoke(rs.getInt("stoke"));
			}
			//ket noi du lieu
			// truy van du lieu
			// chuyen du liieu ra view
			// view nao se nhan du lieu
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	public static void main(String[] args) {
		IProductDAO proDAO = new ProductDAOimpl();
		List<ProductModel> list = proDAO.findProductByCategory(3);
		System.out.println(list);
	}
}
