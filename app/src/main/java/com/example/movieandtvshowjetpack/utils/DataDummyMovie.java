package com.example.movieandtvshowjetpack.utils;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.sort;

public class DataDummyMovie {


    public static ArrayList<MovieEntity> generateDummyMovies() {
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();

        movieEntities.add( new MovieEntity(
                330457,
                "Frozen II",
                "/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
               "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "2019-11-20",
                7.1,
                null
        ));

        movieEntities.add(new MovieEntity(
                398978,
                "The Irishman",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Pennsylvania, 1956. Frank Sheeran, a war veteran of Irish origin who works as a truck driver, accidentally meets mobster Russell Bufalino. Once Frank becomes his trusted man, Bufalino sends him to Chicago with the task of helping Jimmy Hoffa, a powerful union leader related to organized crime, with whom Frank will maintain a close friendship for nearly twenty years.",
                "2019-11-01",
                8,
                null
        ));

        movieEntities.add(new MovieEntity(
                512200,
                "Jumanji: The Next Level",
                "/tmItDhOFsRgFlxX9VaWJEGfstJV.jpg",
                "Spencer returns to the world of Jumanji, prompting his friends, his grandfather and his grandfather’s friend to enter a different and more dangerous version to save him.",
                "2019-12-04",
                7.2,
                null
        ));

        movieEntities.add(new MovieEntity(
                522938,
                "Rambo: Last Blood",
                "/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg",
                "After fighting his demons for decades, John Rambo now lives in peace on his family ranch in Arizona, but his rest is interrupted when Gabriela, the granddaughter of his housekeeper María, disappears after crossing the border into Mexico to meet her biological father. Rambo, who has become a true father figure for Gabriela over the years, undertakes a desperate and dangerous journey to find her.",
                "2019-09-19",
                5.9,
                null

        ));

        movieEntities.add(new MovieEntity(
                475557,
                "Joker",
                "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "2019-10-02",
                8.4,
                null
        ));

        movieEntities.add(new MovieEntity(
                419704,
                "Ad Astra",
                "/xJUILftRf6TJxloOgrilOTJfeOn.jpg",
                "An astronaut travels to the outer edges of the solar system to find his father and unravel a mystery that threatens the survival of Earth. In doing so, he uncovers secrets which challenge the nature of human existence and our place in the cosmos.",
                "2019-09-17",
                6.1,
                null
        ));

        movieEntities.add(new MovieEntity(
                449924,
                "Ip Man 4: The Finale",
                "/vn94LlNrbUWIZZyAdmvUepFBeaY.jpg",
                "Ip Man 4 is an upcoming Hong Kong biographical martial arts film directed by Wilson Yip and produced by Raymond Wong. It is the fourth in the Ip Man film series based on the life of the Wing Chun grandmaster of the same name and features Donnie Yen reprising the role. The film began production in April 2018 and ended in July the same year.",
                "2019-12-20",
                0,
                null
        ));

        movieEntities.add(new MovieEntity(
                474350,
                "It Chapter Two",
                "/zfE0R94v1E8cuKAerbskfD3VfUt.jpg",
                "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.",
                "2019-09-04",
                6.8,
                null
        ));

        movieEntities.add(new MovieEntity(
                466272,
                "Once Upon a Time… in Hollywood",
                "/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg",
                "Los Angeles, 1969. TV star Rick Dalton, a struggling actor specialized in westerns, and stuntman Cliff Booth, his best friend, try to survive to a constantly changing movie industry. Dalton is neighbor of the young and promising actress and model Sharon Tate, who has just married the prestigious Polish director Roman Polanski…",
                "2019-07-25",
                7.5,
                null
        ));

        movieEntities.add(new MovieEntity(
                290859,
                "Terminator: Dark Fate",
                "/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg",
                "Decades after Sarah Connor prevented Judgment Day, a lethal new Terminator is sent to eliminate the future leader of the resistance. In a fight to save mankind, battle-hardened Sarah Connor teams up with an unexpected ally and an enhanced super soldier to stop the deadliest Terminator yet.",
                "2019-10-23",
                6.3,
                null

        ));
        return movieEntities;
    }

    public static MovieEntity getMovie(int id) {
        List<MovieEntity> list = new ArrayList<>(generateDummyMovies());
        int size = generateDummyMovies().size();
        for (int i=0; i<size; i++) {
            MovieEntity movie = generateDummyMovies().get(i);
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
}
