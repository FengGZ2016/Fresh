package dao;

import bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 *
 * @author Administrator
 * @date 2017/12/3
 */
public class UserDao {


    /**
     * 检查用户是否已存在
     * @param userName
     * @return
     */
    public boolean checkUser(String userName) {

        try {
            ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(comboPooledDataSource);
            String sqlStr="select name from user where name =?";
            User user = queryRunner.query(sqlStr, new BeanHandler<User>(User.class),userName);
            if (user==null){
                //可以注册
                return true;

            }else {
                //不能注册
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     *
     * 注册
     * @param name
     * @param password
     * @param email
     */
    public boolean register(String name, String password, String email) {

        try {
            ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(comboPooledDataSource);
            String sqlStr="insert into user value(null,?,?,?)";
            int row = queryRunner.update(sqlStr, name, password, email);
            if (row>0){
                //注册成功
                return true;

            }else {
                //注册失败
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
