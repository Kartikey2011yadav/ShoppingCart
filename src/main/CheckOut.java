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

@WebServlet("/checkout/")
public class CheckOut extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            Connection conn= JDBCConn.getConn();
            Statement statement= conn.createStatement();
            String query="insert into order(OrderId,ProductId,Name,Price,Quantity,UserId) select "; //------------Session Kartikey User ID
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
