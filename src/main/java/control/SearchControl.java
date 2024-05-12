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
@WebServlet(name = "SearchControl", value = "/search")
public class SearchControl extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    String txtSearch = request.getParameter("txt"); //get by Name
    DAO dao = new DAO();
    List<Product> list = dao.searchByName(txtSearch);
    List<Category> listC = dao.getAllCategory();
    Product last = dao.getLast();
    request.setAttribute("listC",listC);
    request.setAttribute("p",last);
    request.setAttribute("txtS",txtSearch);
    request.setAttribute("listP", list);
    request.getRequestDispatcher("Home.jsp").forward(request,response);
  }
}
