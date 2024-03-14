package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.conn.JDBCConn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/add-cart/")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();

        String product = req.getParameter("prod");
        String quantity = req.getParameter("Quantity");
        String UsrId = (String)session.getAttribute("uId");
        String cartId = "";
        PrintWriter out =  resp.getWriter();

        if(UsrId != null){
            try {
                Connection conn = JDBCConn.getConn();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("select CartId from user where UsrName = '"+(String)session.getAttribute("uname")+"';");

                while (rs.next()){
                    cartId = rs.getString("CartId");
                }

                stat.executeUpdate("insert into cart (CartId,Name,Price,ProductId,Quantity) values('')");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            resp.setStatus(resp.SC_ACCEPTED);
            resp.setHeader("Location", "/ShoppingCart/login.html");
            resp.sendRedirect("/ShoppingCart/login.html");
        }

//        if(pass.equals(confPass)){
//            try {
//                Connection conn = JDBCConn.getConn();
//                Statement stat = conn.createStatement();
//
//                String cId = "cart"+((int) (Math.random() * 100))+usr.substring(0,2);
//                stat.executeUpdate("INSERT INTO user(CartId, UsrName, Pass) VALUES('"+cId+"','"+usr+"','"+pass+"')");
//
//            } catch (ClassNotFoundException | SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            out.println("<script type='text/javascript'>");
//            out.println("window.alert('Registration successful');");
//            out.println("location='login.html';");
//            out.println("</script>");
//            return;
//        }
//        else {
//            out.println("<script type='text/javascript'>");
//            out.println("window.alert('password and confirm password does not match up');");
//            out.println("location='createAccount.html';");
//            out.println("</script>");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
