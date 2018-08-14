
package main.java;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class FilmDAO {

    public  Connection conn;


    public void connect() throws ClassNotFoundException, SQLException
    {
        File f = new File("OpenFilms.db");
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("JDBC:sqlite:"+f.getAbsolutePath());

    }

    public void disconnect() throws SQLException {
        if(conn != null && !conn.isClosed()) {
            conn.close();
        }
    }


    public void drop () throws SQLException{
        Statement statmt = conn.createStatement();
        statmt.execute("DROP TABLE if EXISTS films");
    }


    public void createTable () throws SQLException, ClassNotFoundException {
        connect();
        drop();
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS films (id int, title varchar(40), description varchar(200))");
    }

    public boolean insertFilm () throws SQLException, IOException, ClassNotFoundException {
        URL url = new URL ("https://api.themoviedb.org/3/movie/upcoming?api_key=60d60bb4759635c09cde63d375d1b094");
        Scanner scanner = new Scanner(url.openStream());
        String str= new String();
        while(scanner.hasNext())
            str += scanner.nextLine();
        scanner.close();
           JSONObject obj = new JSONObject(str);
           JSONArray array = obj.getJSONArray("results");

        String sql = "INSERT INTO films VALUES (?,?,?)";
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        for (int i = 0; i < array.length(); i++) {
            int id = array.getJSONObject(i).getInt("id");
            String title = array.getJSONObject(i).getString("title");
            String description = array.getJSONObject(i).getString("overview");

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
        }

        boolean row = preparedStatement.executeUpdate() > 0;

        preparedStatement.close();
        disconnect();

        return row;

    }

    public List<Films> listAllFilms() throws SQLException, ClassNotFoundException {

        ArrayList<Films> listFilm = new ArrayList<>();

        String sql = "SELECT * FROM films";
        connect();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            Films film = new Films(id,title,description);
            listFilm.add(film);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listFilm;

    }

    public boolean deleteFilm (Films film) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM films WHERE id = ?";
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, film.getId());

        boolean rowDelete = preparedStatement.executeUpdate() > 0;

        preparedStatement.close();
        disconnect();

        return rowDelete;

    }


    public boolean updateFilm (Films film) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE films SET title = ?, description = ?";
        sql += "WHERE id = ?";

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, film.getTitle());
        preparedStatement.setString(2, film.getOverview());
        preparedStatement.setInt(3,film.getId());

        boolean filmUpdate = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return filmUpdate;
    }


    public Films getFilm (int id) throws SQLException, ClassNotFoundException {
        Films films = null;
        String sql = "SELECT * FROM films WHERE id = ?";

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String desc = resultSet.getString("description");

            films = new Films(id, title, desc);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();
        return films;
    }
}
