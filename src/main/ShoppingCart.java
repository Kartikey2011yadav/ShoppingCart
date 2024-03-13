package main;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class ShoppingCart extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }
}
