package com.example.tvseriestrackingwebapp.backend.repositories;

import com.example.tvseriestrackingwebapp.backend.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
}
