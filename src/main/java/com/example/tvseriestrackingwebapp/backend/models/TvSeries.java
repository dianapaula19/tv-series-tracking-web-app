package com.example.tvseriestrackingwebapp.backend.models;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class TvSeries implements Comparable<TvSeries> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String title = "";

    private String description = "";

    @NotNull
    @NotEmpty
    private int noOfSeasons;


    @OneToMany(mappedBy = "tvSeries", fetch = FetchType.EAGER)
    private List<Season> seasons  = new ArrayList<>();

    @OneToMany(mappedBy = "tvSeries")
    private List<Challenge> challenges;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNoOfSeasons() {
        return noOfSeasons;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return "TvSeries{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", noOfSeasons=" + noOfSeasons +
                '}';
    }


    @Override
    public int compareTo(TvSeries tvSeries) {
        return this.getId().compareTo(tvSeries.getId());
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof TvSeries)) {
            return false;
        }

        TvSeries t = (TvSeries) o;

        return this.getId().equals(t.getId());
    }
}