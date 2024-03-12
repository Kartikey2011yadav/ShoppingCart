package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
//        try
//        {
//            String oid=req.getParameter("value");
//            String operation=req.getParameter("operation");
//            JDBCConn jdbcConn = new JDBCConn();
//            Connection conn = jdbcConn.getConn();
//            Statement stmt=conn.createStatement();
//            if(Objects.equals(operation, "add"))
//            {
//                String query="update cart set pquantity=pquantity+1 where oid='"+oid+"';";
//                stmt.executeUpdate(query);
//            }
//            else if (Objects.equals(operation, "min"))
//            {
//                String query="update cart set pquantity=pquantity-1 where oid='"+oid+"';";
//                stmt.executeUpdate(query);
//                query="delete from cart where pquantity=0;";
//                stmt.executeUpdate(query);
//            }
//
//            resp.sendRedirect("/Shopping-Cart/cart.jsp");
//        }
//        catch (SQLException | ClassNotFoundException e)
//        {
//            throw new RuntimeException(e);
//        }


    }
}
