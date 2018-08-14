package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


public class ListFilms extends HttpServlet {

    private FilmDAO filmDAO;


    public ListFilms() {
        super();
        filmDAO = new FilmDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if(request.getParameter("name") != null
                    && request.getParameter("password") != null
                    && request.getParameter("id") != null) {
                Users users = new Users();
                users.addUsers(request.getParameter("name"),
                        request.getParameter("password"),
                        Integer.valueOf(request.getParameter("id")));
            }

            filmDAO.createTable();
            filmDAO.insertFilm();

            request.setAttribute("list", filmDAO.listAllFilms());

        }


       catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher("index.jsp").forward(request, response);


    }


}
