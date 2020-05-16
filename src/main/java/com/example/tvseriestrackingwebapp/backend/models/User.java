package com.example.tvseriestrackingwebapp.backend.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    @Size(min = 8, max = 30)
    private String username = "";

    public List<Request> getReceivedRequests() {
        return receivedRequests;
    }

    @NotNull
    @NotEmpty
    @Size(min = 8)
    private String password = "";

    @Email
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String email = "";

    @NotNull
    @NotEmpty
    private String firstName = "";

    @NotNull
    @NotEmpty
    private String lastName = "";


    @OneToMany(mappedBy = "user")
    List<WatchedEpisode> watchedEpisodes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Request> receivedRequests = new ArrayList<>();

    @OneToMany(mappedBy = "friend")
    List<Request> sentRequests = new ArrayList<>();


    public User(@NotNull @NotEmpty String username,
                @NotNull @NotEmpty String password,
                @Email @NotNull @NotEmpty String email,
                @NotNull @NotEmpty String firstName,
                @NotNull @NotEmpty String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<WatchedEpisode> getWatchedEpisodes() {
        return watchedEpisodes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User u = (User) o;

        return this.getId().equals(u.getId());
    }


}
