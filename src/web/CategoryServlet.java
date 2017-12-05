package web;

import bean.Category;
import bean.Page;
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


    /**
     * 查询生鲜列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getCategoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            //获取当前页数
            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
            //获取当前页的生鲜总数
            int currentCount = Integer.parseInt(req.getParameter("currentCount"));

            // 给分页数据设置默认值
            if (currentCount==0){
                currentCount=10;
            }
            if (currentPage==0){
                currentPage=1;
            }

            //直接调用service层操作
            CategoryService categoryService=new CategoryService();
           Page page = categoryService.findPageCategory(currentPage,currentCount);
            if (page!=null){

                req.setAttribute("page",page);
                req.getRequestDispatcher("/category-list.jsp").forward(req,resp);
            }else {
                req.getRequestDispatcher("/category-list.jsp").forward(req,resp);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改生鲜
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //获取前台所有的请求参数
            Map<String, String[]> parameterMap = req.getParameterMap();
            Category category=new Category();
            BeanUtils.populate(category,parameterMap);

            //调用业务逻辑层的查询方法
            CategoryService categoryService=new CategoryService();
            boolean updateCategory=categoryService.updateCategory(category);

            if (updateCategory){
                //修改成功
                // 修改成功后重定向到生鲜列表界面
                resp.sendRedirect(req.getContextPath()+"/category?method=getCategoryList&currentPage=1&currentCount=10");
            }else {
                //修改失败
                // 失败了直接提示
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("修改失败");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 删除生鲜
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            //获取前台的请求参数id
            Map<String, String[]> parameterMap = req.getParameterMap();
            Category category=new Category();
            BeanUtils.populate(category,parameterMap);
            //调用业务逻辑层的删除方法
            CategoryService categoryService=new CategoryService();
            boolean deleteCategory=categoryService.deleteCategory(category);

            if (deleteCategory){
                //删除成功
                // 删除成功后重定向到生鲜列表界面
                resp.sendRedirect(req.getContextPath()+"/category?method=getCategoryList&currentPage=1&currentCount=10");
            }else {
                //删除失败
                // 失败了直接提示
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("删除失败");
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
