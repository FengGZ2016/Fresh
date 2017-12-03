package service;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;

/**
 *
 * @author Administrator
 * @date 2017/12/3
 */
public class UserService {

    /**
     * 注册
     * @param name
     * @param password
     * @param email
     */
    public boolean register(String name, String password, String email) {
        boolean register=false;

        //1,先检查用户是否已存在
        UserDao userDao=new UserDao();
        boolean checkUser=userDao.checkUser(name);
        if (checkUser){
            //注册
            register=userDao.register(name,password,email);

        }
        return register;

    }


    /**
     * 登录
     * @param name
     * @param password
     */
    public User login(String name, String password) throws SQLException {

        UserDao userDao=new UserDao();
        User user = userDao.login(name, password);

        return user;

    }
}
