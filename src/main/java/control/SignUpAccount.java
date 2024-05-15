package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import dao.DAO;
import entity.Account;

@WebServlet(name = "SignUpControl", value = {"/signup"})
public class SignUpAccount extends HttpServlet {
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");

        if(!pass.equals(re_pass)){
            response.sendRedirect("Login.jsp");
        }else{
            DAO dao = new DAO();
            Account a = dao.checkAccount(user);
            if(a == null){
                //duoc signup
                dao.signup(user,pass);
                response.sendRedirect("Home.jsp");
            }else{
                //ve lai trang Login.jsp
                response.sendRedirect("Login.jsp");
            }
        }

    }
}
