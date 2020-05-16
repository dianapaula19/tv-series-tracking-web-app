package com.example.tvseriestrackingwebapp.backend.service;

import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.repositories.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TVSeriesService {

    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    public List<TvSeries> findAll() {
        return tvSeriesRepository.findAll();
    }

    public TVSeriesService(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }
}
