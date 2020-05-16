package com.example.tvseriestrackingwebapp.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class WatchedEpisode implements Serializable {


    @ManyToOne
    @MapsId("userId")
    private User user;


    @ManyToOne
    @MapsId("episodeId")
    private Episode episode;

    @EmbeddedId
    private WatchedEpisodeId id;

    private LocalDate dateStarted;

    public WatchedEpisodeId getId() {
        return id;
    }

    public WatchedEpisode() {
    }

    public WatchedEpisode(User user, Episode episode) {
        this.user = user;
        this.episode = episode;
        this.id = new WatchedEpisodeId(user.getId(), episode.getId());
    }

    public User getUser() {
        return user;
    }

    public Episode getEpisode() {
        return episode;
    }

    public LocalDate getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }



    @Embeddable
    public static class WatchedEpisodeId implements Serializable {

        @Column
        private Integer userId;

        @Column
        private Integer episodeId;

        public WatchedEpisodeId(Integer userId, Integer episodeId) {
            this.userId = userId;
            this.episodeId = episodeId;
        }

        public WatchedEpisodeId() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            WatchedEpisodeId that = (WatchedEpisodeId) o;
            return Objects.equals(userId, that.userId) &&
                    Objects.equals(episodeId, that.episodeId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, episodeId);
        }

    }

    @Override
    public String toString() {
        return "WatchedEpisode{" +
                "user=" + user +
                ", episode=" + episode +
                ", id=" + id +
                ", dateStarted=" + dateStarted +
                '}';
    }
}
