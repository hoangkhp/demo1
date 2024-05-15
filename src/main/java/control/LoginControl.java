package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import dao.DAO;
import entity.Account;

@WebServlet(name = "LoginControl", value = {"/login"})
public class LoginControl extends HttpServlet {
  protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    String user = request.getParameter("user");
    String pass = request.getParameter("pass");

    DAO dao = new DAO();
    Account a = dao.login(user, pass);

    if (a == null) {
      request.setAttribute("mess", "Username or password is incorrect");
      request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else{
      HttpSession session = request.getSession();
      session.setAttribute("user", a);
      request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }
}
