package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.conn.JDBCConn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;

@WebServlet("/cart/")
public class cart extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            String oid=req.getParameter("value");
            String operation=req.getParameter("operation");
            JDBCConn jdbcConn = new JDBCConn();
            Connection conn = jdbcConn.getConn();
            Statement stmt=conn.createStatement();
            if(Objects.equals(operation, "add"))
            {
                String query="update cart set Quantity=Quantity+1 where ProductId='"+oid+"';"; //------------------------Kartikey insert cart id condition in query by session-------------------------------------
                stmt.executeUpdate(query);
            }
            else if (Objects.equals(operation, "min"))
            {
                String query="update cart set Quantity=Quantity-1 where ProductId='"+oid+"';";  //------------------------Kartikey insert cart id condition in query by session-------------------------------------
                stmt.executeUpdate(query);
                query="delete from cart where Quantity=0;";
                stmt.executeUpdate(query);
            }

            resp.sendRedirect("/ShoppingCart/cart.jsp");
        }
        catch (SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

    }
}
