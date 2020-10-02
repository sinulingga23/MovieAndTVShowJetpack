package com.example.movieandtvshowjetpack.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TVShowEntity")
public class TVShowEntity implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID_TVShow")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "first_air_date")
    private String firstAirDate;

    @ColumnInfo(name = "vote_average")
    private double voteAverage;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    @Ignore
    public TVShowEntity() {

    }

    public TVShowEntity(int id, String name, String posterPath, String overview, String firstAirDate, double voteAverage, Boolean favorite) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.voteAverage = voteAverage;

        if (favorite != null) {
            this.favorite = favorite;
        }
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeString(this.firstAirDate);
        dest.writeDouble(this.voteAverage);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
    }

    protected TVShowEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.firstAirDate = in.readString();
        this.voteAverage = in.readDouble();
        this.favorite = in.readByte() != 0;
    }

    public static final Creator<TVShowEntity> CREATOR = new Creator<TVShowEntity>() {
        @Override
        public TVShowEntity createFromParcel(Parcel source) {
            return new TVShowEntity(source);
        }

        @Override
        public TVShowEntity[] newArray(int size) {
            return new TVShowEntity[size];
        }
    };
}
