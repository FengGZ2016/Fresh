package service;

import bean.Category;
import dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/12/4
 */
public class CategoryService {

    /**
     * 添加生鲜种类
     * @param category
     * @return
     */
    public boolean addCategory(Category category) throws SQLException {
        //调用dao层操作数据库
        CategoryDao categoryDao=new CategoryDao();
        boolean addCategory=categoryDao.addCategory(category);
        return addCategory;

    }

    /**
     *查询生鲜列表
     */
    public  List<Category> findCategory() throws SQLException {
        //调用dao层操作数据库
        CategoryDao categoryDao=new CategoryDao();
        List<Category> categoryList = categoryDao.queryCategoryList();

        return categoryList;
    }
}
