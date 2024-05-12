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

@WebServlet(name = "Detail Control", value ="/detail")

public class DetailControl extends HttpServlet {
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException{}
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String id = request.getParameter("pid");
    DAO dao = new DAO();
    Product p = dao.getProductByID(id);
    List<Category> listC = dao.getAllCategory();
    Product last = dao.getLast();

    request.setAttribute("listC",listC);
    request.setAttribute("p",last);
    request.setAttribute("d", p);
    request.getRequestDispatcher("Detail.jsp").forward(request,response);
  }
}
