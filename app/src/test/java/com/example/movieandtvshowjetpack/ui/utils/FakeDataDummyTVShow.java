package com.example.movieandtvshowjetpack.ui.utils;

import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.remote.response.TVShowResponse;

import java.util.ArrayList;

public class FakeDataDummyTVShow {
    public static ArrayList<TVShowEntity> generateDummyTVShow() {
        ArrayList<TVShowEntity> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TVShowEntity(
                82856,
                "The Mandalorian",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "2019-11-12",
                7.6,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                60625,
                "Rick and Morty",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "2013-12-02",
                8.6,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                44217,
                "Vikings",
                "/ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "2013-03-03",
                7.5,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                456,
                "The Simpsons",
                "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989-12-17",
                7.2,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                1412,
                "Arrow",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012-10-10",
                5.8,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                60735,
                "The Flash",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                6.7,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                1622,
                "Supernatural",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "2005-09-13",
                7.4,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                71641,
                "4 Blocks",
                "/jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
                "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple.",
                "2017-05-0",
                4.4,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                68507,
                "His Dark Materials",
                "/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                "2019-11-03",
                7.7,
                null
        ));

        tvShowEntities.add(new TVShowEntity(
                69050,
                "Riverdale",
                "/4X7o1ssOEvp4BFLim1AZmPNcYbU",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                7.3,
                null
        ));

        return tvShowEntities;
    }

    public static TVShowEntity getTVShow(int tvShowID) {
        int size = generateDummyTVShow().size();
        for (int i=0; i<size; i++) {
            TVShowEntity tvShowEntity = generateDummyTVShow().get(i);
            if (generateDummyTVShow().get(i).getId() == tvShowID) {
                return tvShowEntity;
            }
        }
        return null;
    }

    public static ArrayList<TVShowResponse> generateRemoteDummyTVShow() {
        ArrayList<TVShowResponse> tvShowResponses = new ArrayList<>();

        tvShowResponses.add(new TVShowResponse(
                82856,
                "The Mandalorian",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "2019-11-12",
                7.6
        ));

        tvShowResponses.add(new TVShowResponse(
                60625,
                "Rick and Morty",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "2013-12-02",
                8.6
        ));

        tvShowResponses.add(new TVShowResponse(
                44217,
                "Vikings",
                "/ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "2013-03-03",
                7.5
        ));

        tvShowResponses.add(new TVShowResponse(
                456,
                "The Simpsons",
                "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989-12-17",
                7.2
        ));

        tvShowResponses.add(new TVShowResponse(
                1412,
                "Arrow",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012-10-10",
                5.8
        ));

        tvShowResponses.add(new TVShowResponse(
                60735,
                "The Flash",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                6.7
        ));

        tvShowResponses.add(new TVShowResponse(
                1622,
                "Supernatural",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "2005-09-13",
                7.4
        ));

        tvShowResponses.add(new TVShowResponse(
                71641,
                "4 Blocks",
                "/jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
                "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple.",
                "2017-05-0",
                4.4
        ));

        tvShowResponses.add(new TVShowResponse(
                68507,
                "His Dark Materials",
                "/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                "2019-11-03",
                7.7
        ));

        tvShowResponses.add(new TVShowResponse(
                69050,
                "Riverdale",
                "/4X7o1ssOEvp4BFLim1AZmPNcYbU",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                7.3
        ));

        return tvShowResponses;
    }

    public static TVShowResponse getRemoteTVShow(int tvShowID) {
        int size = generateRemoteDummyTVShow().size();
        for (int i=0; i<size; i++) {
            TVShowResponse tvShowResponse = generateRemoteDummyTVShow().get(i);
            if (tvShowResponse.getId() == tvShowID) {
                return tvShowResponse;
            }
        }
        return null;
    }

    public static TVShowResponse generateRemoteDummyTVShow(int id) {
        TVShowResponse tvShowResponse = getRemoteTVShow(id);
        if (tvShowResponse != null) {
            return new TVShowResponse(
              tvShowResponse.getId(),
              tvShowResponse.getName(),
              tvShowResponse.getPosterPath(),
              tvShowResponse.getOverview(),
              tvShowResponse.getFirstAirDate(),
              tvShowResponse.getVoteAverage()
            );
        }
        return null;
    }

    public static TVShowEntity generateDummyTVShow(int id) {
        TVShowResponse tvShowResponse = getRemoteTVShow(id);
        if (tvShowResponse != null) {
            return new TVShowEntity(
                    tvShowResponse.getId(),
                    tvShowResponse.getName(),
                    tvShowResponse.getPosterPath(),
                    tvShowResponse.getOverview(),
                    tvShowResponse.getFirstAirDate(),
                    tvShowResponse.getVoteAverage(),
                    null
            );
        }
        return null;
    }
}
