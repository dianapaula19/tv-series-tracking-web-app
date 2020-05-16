package com.example.tvseriestrackingwebapp.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Challenge {

    @ManyToOne
    @MapsId("requestId")
    private Request request;


    @ManyToOne
    @MapsId("tvSeriesId")
    private TvSeries tvSeries;

    @EmbeddedId
    private Challenge.ChallengeId id;

    public Request getRequest() {
        return request;
    }

    public TvSeries getTvSeries() {
        return tvSeries;
    }

    public Challenge(Request request, TvSeries tvSeries) {
        this.request = request;
        this.tvSeries = tvSeries;
        this.id = new ChallengeId(request.getId(), tvSeries.getId());
    }

    public Challenge() {
    }

    @Embeddable
    public static class ChallengeId implements Serializable {

        @Column
        private Request.RequestId requestId;

        @Column
        private Integer tvSeriesId;


        public ChallengeId(Request.RequestId requestId, Integer tvSeriesId) {
            this.requestId = requestId;
            this.tvSeriesId = tvSeriesId;
        }

        public ChallengeId() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Challenge.ChallengeId that = (Challenge.ChallengeId) o;
            return Objects.equals(requestId, that.requestId) &&
                    Objects.equals(tvSeriesId, that.tvSeriesId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(requestId, tvSeriesId);
        }

    }
}
