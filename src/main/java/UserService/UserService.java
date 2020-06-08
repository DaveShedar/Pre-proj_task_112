package UserService;

import Dao.UserDAO;
import ModelServlet.UtilServlet;
import User.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;

    public static UserService getUserService(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public void addUser(User user) throws SQLException{
        UserDAO.getUserDAO().addUser(user);
    }

    public boolean updateUser(User user) throws SQLException{
        return UserDAO.getUserDAO().updateUser(user);
    }

    public User getUserById(int id){
        return UserDAO.getUserDAO().getUserById(id);
    }
    public List< User > getAllUsers(){
        return UserDAO.getUserDAO().getAllUsers();
    }
    public boolean deleteUser(int id) throws SQLException{
        return UserDAO.getUserDAO().deleteUser(id);
    }
}
