package ModelServlet;

import Dao.UserDAO;
import User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    UtilServlet.getUtilServlet().showNewForm(req, resp);
                    break;
                case "/insert":
                    UtilServlet.getUtilServlet().addUser(req, resp);
                    break;
                case "/delete":
                    UtilServlet.getUtilServlet().deleteUser(req, resp);
                    break;
                case "/edit":
                    UtilServlet.getUtilServlet().showEditForm(req, resp);
                    break;
                case "/update":
                    UtilServlet.getUtilServlet().updateUser(req, resp);
                    break;
                default:
                    UtilServlet.getUtilServlet().listUsers(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
