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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/checkout/")
public class CheckOut extends HttpServlet
{
    String Genrate_OrderId()
    {
        java.util.Date date=new Date();
        String pattern="ddMMYsmH";
        SimpleDateFormat dateform=new SimpleDateFormat(pattern);
        return (String)(dateform.format(date));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            Connection conn= JDBCConn.getConn();
            Statement statement= conn.createStatement();
            Statement statement_delete= conn.createStatement();
            Statement statement_iterate= conn.createStatement();
            HttpSession session=req.getSession();

            String check_query="select * from cart where CartId=(select CartId from user where UserId="+session.getAttribute("uId")+");";
            ResultSet result_insert=statement_iterate.executeQuery(check_query);
            String orderId=Genrate_OrderId();
            while (result_insert.next())
            {
                statement.executeUpdate("insert into order_list(OrderId,ProductId,Name,Price,Quantity,UserId) values ('"+orderId+"','"+ result_insert.getString("ProductId")+"','"+ result_insert.getString("Name")+"',"+ result_insert.getString("Price")+","+ result_insert.getString("Quantity")+","+session.getAttribute("uId")+");");
            }
            String query_delete="delete from cart where CartId=(select CartId from user where UserId="+session.getAttribute("uId")+");";
            statement_delete.executeUpdate(query_delete);
            //alter table order_list modify OrderId varchar(14);

            resp.sendRedirect("/ShoppingCart/cart.jsp");

        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
