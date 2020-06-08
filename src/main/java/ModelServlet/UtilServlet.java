package ModelServlet;

import User.User;
import UserService.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;

public class UtilServlet {

    private static UtilServlet utilServlet;

    public static UtilServlet getUtilServlet(){
        if(utilServlet == null){
            utilServlet = new UtilServlet();
        }
        return utilServlet;
    }

    void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List< User > listUsers = UserService.getUserService().getAllUsers();
        req.setAttribute("listUsers", listUsers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user-list.jsp");
        requestDispatcher.forward(req, resp);
    }

    void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User updateUser = new User(id, name, email, country);
        UserService.getUserService().updateUser(updateUser);
        resp.sendRedirect("list");
    }

    void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        UserService.getUserService().deleteUser(id);
        resp.sendRedirect("list");
    }

    void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = UserService.getUserService().getUserById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        requestDispatcher.forward(req, resp);
    }

    void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    void addUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(name, email, country);
        UserService.getUserService().addUser(user);
        resp.sendRedirect("list");
    }
}
