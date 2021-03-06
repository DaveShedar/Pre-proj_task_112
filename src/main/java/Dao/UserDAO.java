package Dao;

import User.User;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {

    private static Connection connection;

    protected static Connection getConnection() {
        if(connection == null){
            connection = getMysqlConnection();
        }
        return connection;
    }

    private static UserDAO userDAO;

    public static UserDAO getUserDAO(){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public void addUser(User user) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().
                     prepareStatement("INSERT INTO users" + "(name, email, country) VALUES" + "(?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = getConnection().
                prepareStatement("update users set name = ?,email= ?, country =? where id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public User getUserById(int id) {
        User user = null;
        try (PreparedStatement preparedStatement = getConnection().
                     prepareStatement("SELECT name, email, country FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List< User > getAllUsers() {
        List< User > list = new ArrayList< User >();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                list.add(new User(id, name, email, country));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean usersDeleted;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            usersDeleted = preparedStatement.executeUpdate() > 0;
        }
        return usersDeleted;
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("users?").          //db name
                    append("user=root&").          //login
                    append("password=309201");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            System.out.println("db is connected!!!");
            return connection;
        } catch (Exception e) {
            System.out.println("Ошибка в создании Connection (ниже StackTrace");
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
