package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login-main")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usr = req.getParameter("user");
        String pass = req.getParameter("password");
        System.out.println(usr+" "+pass);

        PrintWriter out =  resp.getWriter();

        if(usr.equals("admin") && pass.equals("1234")){
            resp.setStatus(resp.SC_ACCEPTED);
            resp.setHeader("Location", "/ShoppingCart/");
            resp.sendRedirect("/ShoppingCart/");
            return;
        }
        else {
            out.println("<script type='text/javascript'>");
            out.println("window.alert('password or user is incorrect');");
            out.println("location='Login.html';");
            out.println("</script>");
        }

    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {

        doGet(request, response);
    }
}
