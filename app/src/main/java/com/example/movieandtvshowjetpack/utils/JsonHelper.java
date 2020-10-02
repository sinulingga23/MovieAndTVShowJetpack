package com.example.movieandtvshowjetpack.utils;

import android.app.Application;

import com.example.movieandtvshowjetpack.data.source.remote.response.MovieResponse;
import com.example.movieandtvshowjetpack.data.source.remote.response.TVShowResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class JsonHelper {
    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream inputStream = application.getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<MovieResponse> loadListMovies() {
        ArrayList<MovieResponse> list = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("MovieAPILocal.json")));
            JSONArray resultResponse = responseObject.getJSONArray("results");

            int lengthResultResponse = resultResponse.length();
            for (int i=0; i<lengthResultResponse; i++) {
                JSONObject objectMovie = resultResponse.getJSONObject(i);
                int id = objectMovie.getInt("id");
                String title = objectMovie.getString("title");
                String posterPath = objectMovie.getString("poster_path");
                String overview  = objectMovie.getString("overview");
                String releaseDate = objectMovie.getString("release_date");
                double voteAverage = objectMovie.getDouble("vote_average");

                list.add(new MovieResponse(id,title, posterPath, overview, releaseDate, voteAverage));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ArrayList<TVShowResponse> loadListTVShow() {
        ArrayList<TVShowResponse> list = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("TVShowAPILocal.json")));
            JSONArray resultsResponse = responseObject.getJSONArray("results");

            int lengthResultsResponse = resultsResponse.length();
            for (int i=0; i<lengthResultsResponse; i++) {
                JSONObject objectTVShow = resultsResponse.getJSONObject(i);

                int id = objectTVShow.getInt("id");
                String name = objectTVShow.getString("name");
                String posterPath = objectTVShow.getString("poster_path");
                String overview = objectTVShow.getString("overview");
                String firstAirDate = objectTVShow.getString("first_air_date");
                float voteAverage = (float) objectTVShow.getDouble("vote_average");

                list.add(new TVShowResponse(id, name, posterPath, overview, firstAirDate, voteAverage));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public TVShowResponse getTVShow(int id) {
        TVShowResponse tvShowResponse = null;

        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("TVShowAPILocal.json")));
            JSONArray resultResponse = responseObject.getJSONArray("results");

            int lengthResultResponse = resultResponse.length();
            for (int i=0; i<lengthResultResponse; i++) {
                JSONObject objectMovie = resultResponse.getJSONObject(i);
                if (objectMovie.getInt("id") == id) {
                    tvShowResponse = new TVShowResponse(
                            objectMovie.getInt("id"),
                            objectMovie.getString("name"),
                            objectMovie.getString("poster_path"),
                            objectMovie.getString("overview"),
                            objectMovie.getString("first_air_date"),
                            objectMovie.getDouble("vote_average")
                    );
                    return tvShowResponse;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tvShowResponse;
    }

    public MovieResponse getMovie(int id) {
        MovieResponse movieResponse = null;

        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("MovieAPILocal.json")));
            JSONArray resultResponse = responseObject.getJSONArray("results");

            int lengthResultResponse = resultResponse.length();
            for (int i=0; i<lengthResultResponse; i++) {
                JSONObject objectMovie = resultResponse.getJSONObject(i);
                if (objectMovie.getInt("id") == id) {
                    movieResponse = new MovieResponse(
                            objectMovie.getInt("id"),
                            objectMovie.getString("title"),
                            objectMovie.getString("poster_path"),
                            objectMovie.getString("overview"),
                            objectMovie.getString("release_date"),
                            objectMovie.getDouble("vote_average")
                    );
                    return movieResponse;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieResponse;
    }
}
