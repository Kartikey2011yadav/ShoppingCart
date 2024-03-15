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
            int count = Integer.parseInt(req.getParameter("value"));
            String product = req.getParameter("prod");
            String operation=req.getParameter("operation");
            HttpSession session=req.getSession();

            Connection conn = JDBCConn.getConn();

            Statement stmt=conn.createStatement();

            ResultSet res= stmt.executeQuery("select * from product where Name = '"+product+"';");
            String pId ="";
            while (res.next()){
                pId = res.getString("ProductId");
            }

            if(Objects.equals(operation, "add"))
            {
                count +=1;
                String query="update cart set Quantity="+count+" where ProductId = '"+pId+"' and CartId = (select CartId from user where UsrName = '"+(String)session.getAttribute("uname")+"');";
                stmt.executeUpdate(query);
            }
            else if (Objects.equals(operation, "min"))
            {
                count -=1;
                String query="update cart set Quantity="+count+" where ProductId = '"+pId+"' and CartId = (select CartId from user where UsrName = '"+(String)session.getAttribute("uname")+"');";
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
