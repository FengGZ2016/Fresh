package dao;

import bean.Category;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/12/4
 */
public class CategoryDao {


    /**
     * 添加生鲜种类
     * @param category
     * @return
     */
    public boolean addCategory(Category category) throws SQLException {
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(comboPooledDataSource);
        String sqlStr="insert into category value(null,?,?,?,?)";
        int row = queryRunner.update(sqlStr, category.getC_name(), category.getPlace(), category.getCreatetime(), category.getType());
        if (row>0){
            //插入成功
            return true;
        }else {
            //插入失败
            return false;
        }

    }


    /**
     * 查询生鲜列表
     */
    public  List<Category> queryCategoryList() throws SQLException {
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(comboPooledDataSource);
        String sqlStr="select * from category";
        List<Category> categoryList = queryRunner.query(sqlStr, new BeanListHandler<Category>(Category.class));

        return categoryList;
    }


    /**
     * 分页查询数据
     * @param startPosition 起始位置
     * @param currentCount 当前页的数量
     * @return
     * @throws SQLException
     */
    public  List<Category> queryPageCategoryList(int startPosition,int currentCount) throws SQLException {
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(comboPooledDataSource);
        String sqlStr="select * from category limit ?,?";
        List<Category> categoryList = queryRunner.query(sqlStr, new BeanListHandler<Category>(Category.class),startPosition,currentCount);

        return categoryList;
    }


    /**
     * 生鲜数据的总数
     */
    public int queryCount() throws SQLException {
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(comboPooledDataSource);
        String sqlStr="select count(*) from category";
        Long query = queryRunner.query(sqlStr, new ScalarHandler<>());

        return query.intValue();
    }
}
