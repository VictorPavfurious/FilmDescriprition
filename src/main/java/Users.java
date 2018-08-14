package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Users {

    private int id;
    private String name;
    private String password;

    public Users () {}

    public Users(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }


    public void addUsers(String name, String password, int id) throws IOException {
        ArrayList<Users> listUsers = new ArrayList<>();
        listUsers.add(new Users(name, password, id));


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("users.txt", true));
        for (Users us : listUsers) {
            bufferedWriter.write(us.toString()+ new Date() + "\n");
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
