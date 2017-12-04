package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 *
 * @author Administrator
 * @date 2017/12/4
 */
public class BaseServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     req.setCharacterEncoding("utf-8");
        try {
            String method=req.getParameter("method");
            //通过反射获取当前对象的字节码文件
            Class clazz=this.getClass();
            //获取字节码文件中的方法
            Method clazzMethod=clazz.getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //执行方法
            clazzMethod.invoke(this,req,resp);
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
