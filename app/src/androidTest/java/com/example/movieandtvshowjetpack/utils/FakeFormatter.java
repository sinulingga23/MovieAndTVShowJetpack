package com.example.movieandtvshowjetpack.utils;

public class FakeFormatter {

    public String getNewFormatGenres(String genres) {
        String[] listGenres = genres.split(",");

        StringBuilder newFormat = new StringBuilder();
        for (int i=0; i<listGenres.length; i++) {

            if ((i+1) % 3  == 0) {
                newFormat.append(listGenres[i]).append('\n');
            } else {
                if (listGenres.length-1 == i) {
                    newFormat.append(listGenres[i]);
                } else {
                    newFormat.append(listGenres[i]).append(" - ");
                }
            }
        }
        return newFormat.toString();
    }

    public String getNewFormatActors(String actors) {
        String[] listActors = actors.split(",");

        StringBuilder newFormat = new StringBuilder();
        for (int i=0; i<listActors.length; i++) {

            if ((i+1) % 3  == 0) {
                newFormat.append(listActors[i]).append('\n');
            } else {
                if (listActors.length-1 == i) {
                    newFormat.append(listActors[i]);
                } else {
                    newFormat.append(listActors[i]).append(" - ");
                }
            }
        }
        return newFormat.toString();
    }

    public String eachLine(String text, int k, String del) {
        StringBuilder result = new StringBuilder();
        String[] temp = text.split(del);
        for (int i=0; i<temp.length; i++) {
            if ((i+1) % k == 0) {
                result.append(temp[i]).append('\n');
            } else {
                result.append(temp[i]);
            }
        }
        return result.toString();
    }
}
