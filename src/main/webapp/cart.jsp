<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
  <%!
     Connection conn = null;
     Statement stmt = null;
     ResultSet rs = null;

     String user = "root";
  %>
  <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","2003");
       stmt=conn.createStatement();
       String query="select * from cart;";
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
                            String url_add="/Shopping-Cart/cart/?value="+rs.getString("oid")+"&operation=add";
                            String url_del="/Shopping-Cart/cart/?value="+rs.getString("oid")+"&operation=min";
                %>
                            <tr>
                                <th class="f-size" scope="row"><%=rs.getString("oid")%></th>
                                    <td class="f-size">
                                        <b><%=rs.getString("pname")%></b>
                                    </td>
                                    <td class="f-size">
                                       <b><%=rs.getInt("pprice")%></b>
                                    </td>
                                    <td>
                                    <a style='text-decoration:none;' href=<%= url_add %> >
                                        <button class="btn btn-primary">+</button>&nbsp
                                    </a>
                                       <b class='f-size'><%=rs.getInt("pquantity")%></b>&nbsp
                                    <a style='text-decoration:none;' href=<%= url_del %>>
                                        <button class="btn btn-danger">-</button>
                                    </a>
                                    </td>
                                    <td class="f-size">
                                        <b><%=rs.getInt("pquantity")*rs.getInt("pprice")%></b>
                                    </td>
                            </tr>
                <%
                        item_count+=rs.getInt("pquantity");
                        item_total+=rs.getInt("pquantity")*rs.getInt("pprice");
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