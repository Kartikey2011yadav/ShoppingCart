<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="main.conn.JDBCConn" %>
  <%!
     Connection conn = null;
     Statement stmt = null;
     ResultSet rs = null;
     String user = "root";
  %>
  <%
   try
   {
       JDBCConn jdbcConn = new JDBCConn();
       Connection conn = jdbcConn.getConn();
       stmt=conn.createStatement();
       String query="select * from cart;";  // ------------------------Kartikey insert cart id condition in query by session-------------------------------------
       rs=stmt.executeQuery(query);
   }
   catch (SQLException e)
   {
       throw new RuntimeException(e);
   }
  %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="style.css">

</head>
<style>
.f-size
{
   font-size:20px;
}
</style>
<body>
<br>
<br>
<table class="table">
   <tr>
       <td>
            <div style="text-align: center;">
            <h1>Cart Items</h1>
            </div>
            <table class="table container">
                <thead>
                <tr>
                    <th class="f-size" scope="col">Order ID</th>
                    <th class="f-size" scope="col">Order Name</th>
                    <th class="f-size" scope="col">Order Price</th>
                    <th class="f-size" scope="col">Quantity</th>
                    <th class="f-size" scope="col">Sub-Total</th>
                </tr>
                </thead>
                <tbody>
                <%
                    int item_count=0;
                    int item_total=0;
                    try
                    {
                        while(rs.next())
                        {
                            String url_add="/ShoppingCart/cart/?value="+rs.getString("ProductId")+"&operation=add";
                            String url_del="/ShoppingCart/cart/?value="+rs.getString("ProductId")+"&operation=min";
                %>
                            <tr>
                                <th class="f-size" scope="row"><%=rs.getString("ProductId")%></th>
                                    <td class="f-size">
                                        <b><%=rs.getString("Name")%></b>
                                    </td>
                                    <td class="f-size">
                                       <b><%=rs.getInt("Price")%></b>
                                    </td>
                                    <td>
                                    <a style='text-decoration:none;' href=<%= url_add %> >
                                        <button class="btn btn-primary"><b>+</b></button>&nbsp
                                    </a>
                                       <b class='f-size'><%=rs.getInt("Quantity")%></b>&nbsp
                                    <a style='text-decoration:none;' href=<%= url_del %>>
                                        <button class="btn btn-danger"><b>-</b></button>
                                    </a>
                                    </td>
                                    <td class="f-size">
                                        <b><%=rs.getInt("Quantity")*rs.getInt("Price")%></b>
                                    </td>
                            </tr>
                <%
                        item_count+=rs.getInt("Quantity");
                        item_total+=rs.getInt("Quantity")*rs.getInt("Price");
                        }
                    }
                    catch (SQLException e)
                    {
                       throw new RuntimeException(e);
                    }
                %>
                </tbody>
            </table>
       </td>
       <td>
           <div class="d-grid gap-3">
           <h1>Payment</h1>
           <div style="font-size:20px">
               <span><b>Total items:</b></span>
               <span style="color:brown"> <b><%=item_count%></b></span>
           </div>

           <div style="font-size:20px">
               <span><b>Total Price:</b></span>
               <span style="color:brown"> <b><%=item_total%></b></span>
           </div>

           <div class="d-grid gap-3 col-8 mx-auto">
               <button class="btn btn-warning" type="button">Proceed To Checkout</button>
               <button class="btn btn-danger" type="button">Clear Cart</button>
           </div>
           </div>
       </td>

   </tr>


</table>

</body>
</html>