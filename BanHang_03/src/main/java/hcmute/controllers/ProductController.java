package hcmute.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import hcmute.models.CategoryModels;
import hcmute.models.ProductModel;
import hcmute.services.ProductServiceImpl;
import hcmute.utils.Constant;
import hcmute.utils.UploadUtils;
import hcmute.services.CategoryServiceImpl;
import hcmute.services.ICategotyService;
import hcmute.services.IProductService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 50)

@WebServlet(urlPatterns = { "/listpro", "/findpro", "/admin-insertpro" })
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 6783850655897485988L;

	// goi cac phuong thuc trong service
	IProductService proService = new ProductServiceImpl();
	ICategotyService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();

		if (url.contains("listpro")) {
			List<ProductModel> list = proService.findAll();
			req.setAttribute("listpro", list);

			RequestDispatcher rd = req.getRequestDispatcher("/views/product/listProduct.jsp");
			rd.forward(req, resp);
		} else if (url.contains("findpro")) {
			int id = Integer.parseInt(req.getParameter("cateid"));

			List<ProductModel> list1 = proService.findProductByCategory(id);
			req.setAttribute("listpro", list1);

			RequestDispatcher rd = req.getRequestDispatcher("/views/product/listProduct.jsp");
			rd.forward(req, resp);
		} else if (url.contains("admin-insertpro")) {
			List<CategoryModels> listcate = cateService.findAll();
			req.setAttribute("listcate", listcate);
			RequestDispatcher rd = req.getRequestDispatcher("/views/product/addProduct.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("admin-insertpro")) {
			insert(req, resp);

		}

	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		ProductModel model = new ProductModel();
		try {
			// lay du lieu tu jsp bang beanutils
			BeanUtils.populate(model, req.getParameterMap());
			// Xử lý truong image
			if (req.getPart("imageLink").getSize() != 0) {
				String fileName = "" + System.currentTimeMillis();
				model.setImageLink(
						UploadUtils.processUpload("imageLink", req, Constant.DIR + "\\products\\", fileName));
			}
			model.setCategory(cateService.findOne(model.getCategoryID()));
			proService.insert(model);
			// thông báo kết quả
			req.setAttribute("product", model);
			req.setAttribute("message", "Add successful");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Add fails");
		}

		resp.sendRedirect(req.getContextPath() + "/listpro");

	}

}