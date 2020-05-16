package com.example.tvseriestrackingwebapp.backend.repositories;

import com.example.tvseriestrackingwebapp.backend.models.WatchedEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedEpisodeRepository extends JpaRepository<WatchedEpisode, Integer> {


}
