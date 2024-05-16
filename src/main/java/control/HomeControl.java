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
@WebServlet(name = "HomeControl", value = "/home")
public class HomeControl extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    //b1:get data from dao
    DAO dao = new DAO();
    List<Product> list = dao.getAllProduct();
    List<Category> listC = dao.getAllCategory();
    Product last = dao.getLast();
    //b2: set data to jsp
    request.setAttribute("listP", list);
    request.setAttribute("listC",listC);
    request.setAttribute("p",last);
    request.getRequestDispatcher("Home.jsp").forward(request, response);
  }
}
