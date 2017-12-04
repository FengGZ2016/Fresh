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
    public boolean register(User user) {
        boolean register=false;

        //1,先检查用户是否已存在
        UserDao userDao=new UserDao();
        boolean checkUser=userDao.checkUser(user.getName());
        if (checkUser){
            //注册
            register=userDao.register(user);

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
