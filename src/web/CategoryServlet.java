package web;

import bean.Category;
import org.apache.commons.beanutils.BeanUtils;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2017/12/4
 */
public class CategoryServlet extends BaseServlet{


    /**
     * 添加生鲜种类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            //获取所有请求参数
            Map<String, String[]> parameterMap = req.getParameterMap();
            Category category=new Category();
            BeanUtils.populate(category,parameterMap);
            category.setCreatetime(new Date());

            //执行操作逻辑
            CategoryService categoryService=new CategoryService();
            boolean b=categoryService.addCategory(category);
            if (b){
                //添加成功
                resp.setStatus(201);
                req.getRequestDispatcher("/category-add.jsp").forward(req,resp);

            }else {
                //添加失败
                resp.setStatus(600);
                req.getRequestDispatcher("/category-add.jsp").forward(req,resp);

            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
