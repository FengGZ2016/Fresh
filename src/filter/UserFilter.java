package filter;

import bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * 过滤用户
 * 必须经过登录方能执行增删改查操作
 * @author Administrator
 * @date 2017/12/5
 */
public class UserFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //获取用户的登录信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user==null){
            //用户没有登录，跳转到登录界面
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        //用户已经登录，就放行
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
