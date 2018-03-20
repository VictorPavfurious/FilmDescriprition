package main.java;

public class Films {
    private String title;
    private String overview;

    public Films(String title, String overview) {
        this.title = title;

        this.overview = overview;
    }


    public Films(String title) {
        this.title = title;
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
