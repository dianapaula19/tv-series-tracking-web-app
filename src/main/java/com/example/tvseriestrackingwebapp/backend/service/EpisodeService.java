package com.example.tvseriestrackingwebapp.backend.service;

import com.example.tvseriestrackingwebapp.backend.repositories.EpisodeRepository;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {
    private EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }


}
