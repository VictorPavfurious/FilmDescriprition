package main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ButtonAction")
public class ButtonAction extends HttpServlet {
    private Films film = new Films();
    private FilmDAO filmDAO = new FilmDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            try {
                //filmDAO.getFilm(film.getId());
                if (Integer.parseInt(request.getParameter("idFilm")) == (film.getId())) {
                    request.setAttribute("filmGet",
                            filmDAO.getFilm(Integer.parseInt(request.getParameter("idFilm"))));
                }


                request.getRequestDispatcher("GetFilm.jsp").forward(request, response);
            }


             catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

    }
}
