package com.example.movieandtvshowjetpack.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShowResponse implements Parcelable {
    private int id;
    private String name;
    private String posterPath;
    private String overview;
    private String firstAirDate;
    private double voteAverage;

    public TVShowResponse() {

    }

    public TVShowResponse(int id, String name, String posterPath, String overview, String firstAirDate, double voteAverage) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(firstAirDate);
        parcel.writeDouble(voteAverage);
    }

    protected TVShowResponse(Parcel in) {
        id = in.readInt();
        name = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        firstAirDate = in.readString();
        voteAverage = in.readDouble();
    }

    public static final Creator<TVShowResponse> CREATOR = new Creator<TVShowResponse>() {
        @Override
        public TVShowResponse createFromParcel(Parcel in) {
            return new TVShowResponse(in);
        }

        @Override
        public TVShowResponse[] newArray(int size) {
            return new TVShowResponse[size];
        }
    };
}
