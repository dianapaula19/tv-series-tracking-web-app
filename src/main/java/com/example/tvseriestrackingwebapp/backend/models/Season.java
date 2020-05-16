package com.example.tvseriestrackingwebapp.backend.models;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty

    private int seasonNumber;

    @NotNull
    @NotEmpty

    private int numberOfEpisodes;


    @OneToMany(mappedBy = "season", fetch = FetchType.EAGER)
    private List<Episode> episodes;

    @ManyToOne
    private TvSeries tvSeries;

    public Season() {
    }


    public Integer getId() {
        return id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public TvSeries getTvSeries() {
        return tvSeries;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", seasonNumber=" + seasonNumber +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", episodes=" + episodes +
                ", tvSeries=" + tvSeries +
                '}';
    }
}
