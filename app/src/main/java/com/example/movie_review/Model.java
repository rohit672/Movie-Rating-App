package com.example.movie_review;

import java.util.HashMap;
import java.util.Map;

public class Model {

      String movieName;
      Double rating ;
      Map<String, Double> usersRated = new HashMap<>();

      Model() {

      }

    public Model(String movieName, Double rating, Map<String, Double> usersRated) {
        this.movieName = movieName;
        this.rating = rating;
        this.usersRated = usersRated;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Map<String, Double> getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(Map<String, Double> usersRated) {
        this.usersRated = usersRated;
    }
}
