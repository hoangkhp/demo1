package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Category Control", value ="/category")

public class CategoryControl extends HttpServlet {
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException{}
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String cateID = request.getParameter("cid"); //Lay cid ve
    DAO dao = new DAO();
    List<Product> list = dao.getProductByCategory(cateID);
    List<Category> listC = dao.getAllCategory();
    Product last = dao.getLast();

    request.setAttribute("listP", list);
    request.setAttribute("listC",listC);
    request.setAttribute("p",last);
    request.setAttribute("tag",cateID);
    request.getRequestDispatcher("Home.jsp").forward(request,response);
  }
}
