package web;

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2017/12/4
 */
public class UserServlet extends BaseServlet{

    private final String REMEMBER="yes";
    private final String METHOD_LOGIN="login";
    private final String METHOD_REGISTER="register";

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req,resp);
//    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String method=req.getParameter("method");
//        if (METHOD_LOGIN.equals(method)){
//            //登录
//            login(req,resp);
//
//        }else if (METHOD_REGISTER.equals(method)){
//            //注册
//            register(req,resp);
//
//        }
//    }


    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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


    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            //获取是否记住密码
            String remember=req.getParameter("remember");
            if (REMEMBER.equals(remember)){
                //记住密码
                Cookie nameCookie=new Cookie("name",name);
                Cookie passwordCookie=new Cookie("password",password);
                nameCookie.setMaxAge(60*10);
                passwordCookie.setMaxAge(60*10);

                resp.addCookie(nameCookie);
                resp.addCookie(passwordCookie);


            }

            //登录成功/category?method=getCategoryList
            resp.sendRedirect(req.getContextPath()+"/category?method=getCategoryList&currentPage=1&currentCount=10");

        }else {
            //登录失败
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("登录失败！");
        }

    }
}
