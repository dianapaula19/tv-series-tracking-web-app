package com.example.tvseriestrackingwebapp.backend.service;

import com.example.tvseriestrackingwebapp.backend.models.Request;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.models.WatchedEpisode;
import com.example.tvseriestrackingwebapp.backend.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public List<User> friendsofUser(User user) {
        List<User> friends = new ArrayList<>();
        for (Request r : requestRepository.findAll()) {
            if(areFriends(user, r.getUser())) {
                friends.add(r.getUser());
            }
        }
        Set<User> friendsSet = new HashSet<>(friends);
        friends.clear();
        friends.addAll(friendsSet);
        return friends;
    }

    public List<User> friendRequests(User user) {
        List<User> friendRequests = new ArrayList<>();
        for(Request r : requestRepository.findAll()) {
            if(requestExists(r.getUser(), user) && !requestExists(user, r.getUser())) {
                friendRequests.add(r.getUser());
            }
        }
        Set<User> friendRequestsSet = new HashSet<>(friendRequests);
        friendRequests.clear();
        friendRequests.addAll(friendRequestsSet);
        return friendRequests;
    }

    public void save(Request request) {
        requestRepository.save(request);
    }

    public void delete(Request request) {
        requestRepository.delete(request);
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public boolean requestExists(User user, User friend) {
        for (Request r : requestRepository.findAll()) {
            if(r.getUser().equals(user) && r.getFriend().equals(friend)){
                return true;
            }
        }
        return false;
    }

    public boolean areFriends(User user, User friend) {
        if(requestExists(user, friend) && requestExists(friend, user)) {
            return true;
        }
        return false;
    }

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
}
