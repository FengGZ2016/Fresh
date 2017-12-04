package web;

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2017/12/3
 */
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收前台的请求参数
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
         User user=new User();

        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService=new UserService();
        boolean register=userService.register(user);
        if (register){
            resp.sendRedirect(req.getContextPath()+"login.jsp");
        }else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("注册失败！");

        }

    }
}
