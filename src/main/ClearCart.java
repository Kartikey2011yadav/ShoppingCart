package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.conn.JDBCConn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/clear/")
public class ClearCart extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            Connection conn= JDBCConn.getConn();
            Statement stat = conn.createStatement();
            HttpSession session = req.getSession();
            ResultSet rs = stat.executeQuery("select CartId from user where UsrName = '"+(String)session.getAttribute("uname")+"';");
            String cartId = "";
            while (rs.next()){
                cartId = rs.getString("CartId");
            }

            Statement statement= conn.createStatement();
            String query="delete from cart where CartId = '"+cartId+"';";
            statement.executeUpdate(query);
            resp.sendRedirect("/ShoppingCart/cart.jsp");
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
