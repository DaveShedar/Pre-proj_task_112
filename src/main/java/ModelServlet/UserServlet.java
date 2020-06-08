package ModelServlet;

import Dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(value = "/")
public class UserServlet extends HttpServlet {

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
