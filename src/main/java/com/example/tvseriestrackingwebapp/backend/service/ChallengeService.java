package com.example.tvseriestrackingwebapp.backend.service;

import com.example.tvseriestrackingwebapp.backend.models.Challenge;
import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.repositories.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    private ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> findAllByUser(User user) {
        List<Challenge> challenges = new ArrayList<>();
        for (Challenge c : challengeRepository.findAll()) {
            if(c.getRequest().getFriend().equals(user)) {
                challenges.add(c);
            }
        }
        return challenges;
    }

    public void save(Challenge challenge) {
        challengeRepository.save(challenge);
    }

}
