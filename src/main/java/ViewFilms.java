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
            films.add(new Films(array.getJSONObject(i).getString("overview")));
            //getOver(array.getJSONObject(i).getString("overview"));

        }



        for (int i = 0; i < films.size(); i++) {
            request.setAttribute("over" + i, films.get(i).getOverview());

        }

        /*for (int i = 0; i < array.length(); i++) {
            if(array.getJSONObject(i).has(films.get(i).getTitle())) {
                request.setAttribute("overview" , films.get(i).getTitle());
                out.print(films.get(i).getTitle());
            }
        }*/

       /* Iterator<Films> itr = films.iterator();

            while (itr.hasNext()) {
                if (itr.next().getTitle().equals(itr.next().getOverview())) {
                    request.setAttribute("overview", itr.next().getOverview());
                }
            }*/

        request.getRequestDispatcher("descriptionF.jsp").forward(request, response);

    }

    /*public String getOver(String over) {
        Films films = new Films();
        films.setOverview(over);

        return over;
    }*/

}

