package dao;

import bean.Category;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

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
}
