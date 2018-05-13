package main.java;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewFilms extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        URL url = new URL("https://api.themoviedb.org/3/movie/upcoming?api_key=60d60bb4759635c09cde63d375d1b094");

        Scanner scanner = new Scanner(url.openStream());
        String str = new String();
        while (scanner.hasNext())
            str += scanner.nextLine();
        scanner.close();

        JSONObject obj = new JSONObject(str);

        ArrayList<Films> films = new ArrayList<Films>();
        JSONArray array = obj.getJSONArray("results");
        for (int i = 0; i < array.length(); i++) {
            films.add(new Films(array.getJSONObject(i).getString("title"), array.getJSONObject(i).getString("overview"),
                    array.getJSONObject(i).getInt("id")));

        }

        for (int i = 0; i < films.size(); i++) {
            if (request.getParameter("title").equals(films.get(i).getTitle())) {

                request.setAttribute("over", films.get(i).getOverview());


            }
        }


        request.getRequestDispatcher("descriptionF.jsp").forward(request, response);

    }


}

