package com.example.tvseriestrackingwebapp.backend.repositories;

import com.example.tvseriestrackingwebapp.backend.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
}
