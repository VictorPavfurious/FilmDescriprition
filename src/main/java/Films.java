package main.java;

public class Films {
    private String title;
    private String overview;
    private int id;


    public Films(String title, String over , int id) {
        this.title = title;
        this.overview = over;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
