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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/login-main")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usr = req.getParameter("user");
        String pass = req.getParameter("password");
        System.out.println(usr+" "+pass);

        try {
            Connection conn = JDBCConn.getConn();
            Statement stat = conn.createStatement();
            PrintWriter out =  resp.getWriter();

            ResultSet res = stat.executeQuery("Select Pass from user where UsrName = '"+usr+"';");
            if(!res.isBeforeFirst() && res.getRow() == 0){
                out.println("<script type='text/javascript'>");
                out.println("window.alert('user or password is incorrect');");
                out.println("location='login.html';");
                out.println("</script>");
            }
            else{
                while (res.next()){
                    if(pass.equals(res.getString("Pass"))){
                        resp.setStatus(resp.SC_ACCEPTED);
                        resp.setHeader("Location", "/ShoppingCart/");
                        resp.sendRedirect("/ShoppingCart/");
                    }
                }
                out.println("<script type='text/javascript'>");
                out.println("window.alert('Entered password is incorrect');");
                out.println("location='login.html';");
                out.println("</script>");
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
