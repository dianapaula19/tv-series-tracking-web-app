package com.example.tvseriestrackingwebapp.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private int episodeNumber;

    @NotNull
    @NotEmpty
    private int duration;

    private String description;

    @ManyToOne
    private Season season;

    @OneToMany(mappedBy = "episode")
    private List<WatchedEpisode> watchedEpisodes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public Season getSeason() {
        return season;
    }

    public List<WatchedEpisode> getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public static void order(List <Episode> episodes) {
        Collections.sort(episodes, new Comparator<Episode>() {
            @Override
            public int compare(Episode e1, Episode e2) {
                Integer x1 = e1.getEpisodeNumber();
                Integer x2 = e2.getEpisodeNumber();
                return x1.compareTo(x2);
            }
        });
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Episode)) {
            return false;
        }

        Episode e = (Episode) o;

        return this.getId().equals(e.getId());
    }
}
