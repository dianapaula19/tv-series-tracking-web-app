package com.example.tvseriestrackingwebapp.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Request implements Serializable {

    @ManyToOne
    @MapsId("userId")
    private User user;


    @ManyToOne
    @MapsId("friendId")
    private User friend;

    @EmbeddedId
    private Request.RequestId id;

    @OneToMany(mappedBy = "request")
    private List<Challenge> challenges;


    public User getUser() {
        return user;
    }

    public User getFriend() {
        return friend;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public RequestId getId() {
        return id;
    }

    public Request(User user, User friend) {
        this.user = user;
        this.friend = friend;
        this.id = new Request.RequestId(user.getId(), friend.getId());
    }


    public Request() {
    }

    @Embeddable
    public static class RequestId implements Serializable {

        @Column
        private Integer userId;

        @Column
        private Integer friendId;

        public RequestId(Integer userId, Integer friendId) {
            this.userId = userId;
            this.friendId = friendId;
        }

        public RequestId() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Request.RequestId that = (Request.RequestId) o;
            return Objects.equals(userId, that.userId) &&
                    Objects.equals(friendId, that.friendId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, friendId);
        }

    }

    @Override
    public String toString() {
        return "Request{" +
                "user=" + user +
                ", friend=" + friend +
                ", id=" + id +
                ", challenges=" + challenges +
                '}';
    }
}
