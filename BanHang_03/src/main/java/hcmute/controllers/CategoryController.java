package hcmute.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hcmute.models.CategoryModels;
import hcmute.services.CategoryServiceImpl;
import hcmute.services.ICategotyService;

@WebServlet(urlPatterns = { "/listcate", "/addcate", "/update", "/delete" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 8204386410988297301L;
	// goi cac phuong thuc trong service
	ICategotyService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();

		if (url.contains("listcate")) {
			findAll(req, resp);
		} else if (url.contains("addcate")) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/category/addCategory.jsp");
			rd.forward(req, resp);
		} else if (url.contains("update")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			// nhan du lieu tu view
			int id = Integer.parseInt(req.getParameter("id"));

			// goi phuong thuc findOne trong service
			CategoryModels model = cateService.findOne(id);

			// day du lieu view
			req.setAttribute("cate", model);

			RequestDispatcher rd = req.getRequestDispatcher("/views/category/updateCategory.jsp");
			rd.forward(req, resp);
		} else if (url.contains("delete")) {
			// nhan du lieu tu view
			int id = Integer.parseInt(req.getParameter("id"));

			// goi phuong thuc findOne trong service
			cateService.delete(id);

			// day du lieu view
			req.setAttribute("message", "Da xoa thanh cong");

			RequestDispatcher rd = req.getRequestDispatcher("listcate");
			rd.forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("addcate")) {
			insert(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// thiet lap ngon ngu
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// nhan du lieu tu form
		int id = Integer.parseInt(req.getParameter("cateID"));
		String cateName = req.getParameter("cateName");
		String images = req.getParameter("images");
		// nhan du lieu dua vao model
		CategoryModels cate = new CategoryModels();
		cate.setCateID(id);
		cate.setCateName(cateName);
		cate.setImages(images);
		// goi phuong thuc vao model
		cateService.update(cate);
		// tra ve view
		resp.sendRedirect(req.getContextPath() + "/listcate");

	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// thiet lap ngon ngu
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// nhan du lieu tu form
		String cateName = req.getParameter("cateName");
		String images = req.getParameter("images");
		// nhan du lieu dua vao model
		CategoryModels cate = new CategoryModels();
		cate.setCateName(cateName);
		cate.setImages(images);
		// goi phuong thuc vao model
		cateService.insert(cate);
		// tra ve view
		resp.sendRedirect(req.getContextPath() + "/listcate");

	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<CategoryModels> listcate = cateService.findAll();

		// day du lieu ra view
		req.setAttribute("list", listcate);

		// view nhan du lieu
		RequestDispatcher rd = req.getRequestDispatcher("/views/category/listCategory.jsp");
		rd.forward(req, resp);

	}

}