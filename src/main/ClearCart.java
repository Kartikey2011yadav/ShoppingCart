package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.conn.JDBCConn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/clear/")
public class ClearCart extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JDBCConn jdbcConn=new JDBCConn();
        try
        {
            Connection conn=jdbcConn.getConn();
            Statement statement= conn.createStatement();
            String query="delete from cart"; //-----------------------------Session Kartikey Cart----------------------
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
