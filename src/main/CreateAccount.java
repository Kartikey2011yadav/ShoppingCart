package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.conn.JDBCConn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/create-Account")
public class CreateAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usr = req.getParameter("user");
        String pass = req.getParameter("password");
        String confPass = req.getParameter("conf-password");
        System.out.println(usr+" "+pass+" "+confPass);

        PrintWriter out =  resp.getWriter();

        if(pass.equals(confPass)){
            try {
                Connection conn = JDBCConn.getConn();
                Statement stat = conn.createStatement();

                String cId = "cart"+((int) (Math.random() * 100))+usr.substring(0,2);
                stat.executeUpdate("INSERT INTO user(CartId, UsrName, Pass) VALUES('"+cId+"','"+usr+"','"+pass+"')");

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

            resp.setStatus(resp.SC_ACCEPTED);
            resp.setHeader("Location", "/ShoppingCart/");
            resp.sendRedirect("/ShoppingCart/");
            return;
        }
        else {
            out.println("<script type='text/javascript'>");
            out.println("window.alert('password and confirm password does not match up');");
            out.println("location='createAccount.html';");
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
