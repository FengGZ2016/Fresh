package service;

import bean.Category;
import bean.Page;
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


    /**
     *查询生鲜列表
     * @param currentPage
     * @param currentCount
     */
    public  Page findPageCategory(int currentPage, int currentCount) throws SQLException {

        CategoryDao categoryDao=new CategoryDao();
        //查询出生鲜数据的总数
        int totalCount = categoryDao.queryCount();
        //根据总数和当前页面显示数计算出总页数
        int totalPage= (int) Math.ceil(1.0*totalCount/currentCount);

        //封装page到bean中
        Page page=new Page();
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);

        //计算分页查询的起始位置
        int startPosition=(currentPage-1)*currentCount;

        //分页查询数据
        List<Category> pageCategoryList = categoryDao.queryPageCategoryList(startPosition, currentCount);
        // 将集合数据封装到page类中
        page.setList(pageCategoryList);

        return page;

    }


    /**
     * 更新生鲜
     * @param category
     * @return
     */
    public boolean updateCategory(Category category) throws SQLException {

        CategoryDao categoryDao=new CategoryDao();
        boolean updateCategory=categoryDao.updateCategory(category);
        return updateCategory;
    }
}
