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

    int quantity =0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();



        if( session.getAttribute("uId") != null){
            String product = req.getParameter("prod");
            int UsrId = (int)session.getAttribute("uId");
            String cartId = "";
            int price =0;
            String pId = "";

            PrintWriter out =  resp.getWriter();
            try {
                Connection conn = JDBCConn.getConn();
                Statement stat = conn.createStatement();
                System.out.println(quantity + " "+product+" "+UsrId);
                ResultSet rs = stat.executeQuery("select CartId from user where UsrName = '"+(String)session.getAttribute("uname")+"';");

                while (rs.next()){
                    cartId = rs.getString("CartId");
                }

                rs = stat.executeQuery("select * from product where Name = '"+product+"';");

                while (rs.next()){
                    pId = rs.getString("ProductId");
                    price = rs.getInt("Price");
                }

                rs = stat.executeQuery("select CartId, Quantity from cart where ProductId = '"+pId+"';");
                if(rs.isBeforeFirst()){
                    while (rs.next()){
                        quantity += rs.getInt("Quantity");
                        cartId = rs.getString("CartId");
                    }
                    stat.executeUpdate("update cart set Quantity="+quantity+" where ProductId = '"+pId+"' and CartId ='"+cartId+"' ;");
                }
                else{
                    stat.executeUpdate("insert into cart (CartId,Name,Price,ProductId,Quantity) values('"+cartId+"','"+product+"',"+price+",'"+pId+"',"+quantity+")");
                }


                resp.setStatus(resp.SC_ACCEPTED);
                resp.setHeader("Location", "/ShoppingCart/cart.jsp");
                resp.sendRedirect("/ShoppingCart/cart.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            resp.setStatus(resp.SC_ACCEPTED);
            resp.setHeader("Location", "/ShoppingCart/login.html");
            resp.sendRedirect("/ShoppingCart/login.html");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getParameter("Quantity"));
        quantity = Integer.parseInt(request.getParameter("Quantity"));
        doGet(request, response);
    }
}
