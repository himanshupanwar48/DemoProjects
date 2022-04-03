package mycart.servlet;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

//import cn.techtutorial.model.*;
import mycart.connection.DbCon;
import mycart.dao.OrderDao;
import mycart.model.Cart;
import mycart.model.Order;
import mycart.model.User;


//Servlet implementation class checkoutservlet
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
         try(PrintWriter out= response.getWriter()){

           SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
           Date date = new Date();

           //retrive all cart products
           ArrayList<Cart> cart_list= (ArrayList<Cart>) request.getSession(). getAttribute("cart-list");
           
           //user authentication
           User auth= (User) request.getSession().getAttribute("auth");

           //check auth and cart list
           if(cart_list!= null&&auth!=null){
             
              //prepare the order object
              for(Cart c:cart_list){
                Order order = new Order();
                order.setId(c.getId());
                order.setUid(auth.getId());
                order.setQuantity(c.getQuantity());
                order.setDate(formatter.format(date));

                //instantiate the dao class
                OrderDao oDao= new OrderDao(DbCon.getConnetion());

                //calling the insert method
                boolean result= oDao.insertOrder(order);
                
                if(!result)
                   break;

             }

             cart_list.clear();
             response.sendRedirect("orders.jsp");             

           }else{
               if(auth==null) response.sendRedirect("login.jsp");
                  response.sendRedirect("cart.jsp");
            }


         }catch(Exception e){
              e.printStackTrace();
          }
}
       //response.getWriter().append("Served at: ").append(request.getContextPath());//
//}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

       doGet(request, response);
}
    }
