package web;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 * @date 2017/12/3
 */
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        UserService userService=new UserService();

        User user=null;

        try {
            user  = userService.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user!=null){
            //登录成功
            resp.sendRedirect(req.getContextPath()+"/category-list.jsp");

        }else {
            //登录失败
           resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("登录失败！");
        }

    }
}
