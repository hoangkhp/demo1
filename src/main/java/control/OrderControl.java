package control;

import java.io.IOException;
import java.util.List;


import dao.DAO;

import entity.Account;
import entity.Cart;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ForgotPasswordControl
 */
@WebServlet(name = "OrderControl", urlPatterns = {"/order"})
public class OrderControl extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Account a = (Account) session.getAttribute("acc");
    if(a == null) {
      response.sendRedirect("login");
      return;
    }
    int accountID = a.getId();
    DAO dao = new DAO();
    List<Cart> list = dao.getCartByAccountID(accountID);
    List<Product> list2 = dao.getAllProduct();
    request.setAttribute("listCart",list);
    request.setAttribute("listProduct",list2);
    double totalMoney=0;
    for(Cart c : list) {
      for(Product p : list2) {
        if(c.getProduct().getId()==p.getId()) {
          totalMoney=totalMoney+(p.getPrice()*c.getAmount());
        }
      }
    }
    double totalMoneyVAT=totalMoney+totalMoney*0.1;
    request.getRequestDispatcher("CustomerInformation.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    try {
      String emailAddress = request.getParameter("email");
      String name = request.getParameter("name");
      String phoneNumber = request.getParameter("phoneNumber");
      String deliveryAddress = request.getParameter("deliveryAddress");

      HttpSession session = request.getSession();
      Account a = (Account) session.getAttribute("acc");
      if(a == null) {
        response.sendRedirect("login");
        return;
      }
      int accountID = a.getId();
      DAO dao = new DAO();
      List<Cart> list = dao.getCartByAccountID(accountID);
      List<Product> list2 = dao.getAllProduct();
      request.setAttribute("listCart",list);
      request.setAttribute("listProduct",list2);
      double totalMoney=0;
      for(Cart c : list) {
        for(Product p : list2) {
          if(c.getProduct().getId()==p.getId()) {
            totalMoney=totalMoney+(p.getPrice()*c.getAmount());
          }
        }
      }
      double totalMoneyVAT=totalMoney+totalMoney*0.1;
      dao.insertInvoice(accountID, totalMoneyVAT);

      //old code

      request.setAttribute("mess", "Dat hang thanh cong!");

      dao.deleteCartByAccountID(accountID);


      //new code
				request.setAttribute("email", emailAddress);
				request.getRequestDispatcher("CustomerInformation.jsp").forward(request, response);


    } catch (Exception e) {
      request.setAttribute("error", "Dat hang that bai!");
      e.printStackTrace();
    }

    request.getRequestDispatcher("CustomerInformation.jsp").forward(request, response);
  }

}
