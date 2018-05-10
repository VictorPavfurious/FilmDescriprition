package main.java;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ListFilms extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        URL url = new URL ("https://api.themoviedb.org/3/movie/upcoming?api_key=60d60bb4759635c09cde63d375d1b094");

        Scanner scanner = new Scanner(url.openStream());
        String str= new String();
        while(scanner.hasNext())
            str += scanner.nextLine();
        scanner.close();

        JSONObject obj = new JSONObject(str);


        ArrayList<Films> films = new ArrayList<Films>();
        ArrayList<Films> id = new ArrayList<Films>();
        ArrayList<Films> overview = new ArrayList<Films>();
        JSONArray array = obj.getJSONArray("results");


        for (int i = 0; i < array.length(); i++) {
            films.add(new Films(array.getJSONObject(i).getString("title")));
           id.add(new Films(array.getJSONObject(i).getInt("id")));
            overview.add(new Films(array.getJSONObject(i).getString("overview")));
        }

        for (int i = 0; i < films.size(); i++) {
            request.setAttribute("title" + i, films.get(i).getTitle());
           request.setAttribute("id" + i, id.get(i).getId());
            request.setAttribute("overview" + i, overview.get(i).getOverview());


        }


        //String c = getServletConfig().getInitParameter("id");

       request.getRequestDispatcher("index.jsp").forward(request, response);

    }


}
