package com.example.tvseriestrackingwebapp.backend.service;

import com.example.tvseriestrackingwebapp.backend.models.Episode;
import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.models.WatchedEpisode;
import com.example.tvseriestrackingwebapp.backend.repositories.WatchedEpisodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class WatchedEpisodeService {

    private WatchedEpisodeRepository watchedEpisodeRepository;

    public void save(WatchedEpisode watchedEpisode) {
        watchedEpisodeRepository.save(watchedEpisode);
    }

    public void remove(WatchedEpisode watchedEpisode) {
        watchedEpisodeRepository.delete(watchedEpisode);
    }

    public WatchedEpisode findByUserAndEpisode(User user, Episode episode) {

        for (WatchedEpisode watchedEpisode : watchedEpisodeRepository.findAll()) {
            if(watchedEpisode.getUser().equals(user) && watchedEpisode.getEpisode().equals(episode)) {
                return watchedEpisode;
            }
        }
        return null;
    }


    public List<TvSeries> findWatchedTvSeries(User user) {

        List<TvSeries> tvSeriesWatched = new ArrayList<>();
        for (WatchedEpisode watchedEpisode : watchedEpisodeRepository.findAll()) {
            if(watchedEpisode.getUser().equals(user)) {
                tvSeriesWatched.add(watchedEpisode.getEpisode().getSeason().getTvSeries());
            }
        }
        // makes sure there aren't any duplicates in the list
        Set<TvSeries> tvSeriesSet = new HashSet<>(tvSeriesWatched);
        tvSeriesWatched.clear();
        tvSeriesWatched.addAll(tvSeriesSet);
        tvSeriesWatched.sort(TvSeries::compareTo);
        return tvSeriesWatched;
    }

    public int timeSpentPerMonthPerTvSeries(int month, TvSeries tvSeries, User user) {

        int time = 0;
        for (WatchedEpisode watchedEpisode : watchedEpisodeRepository.findAll()) {
            if(watchedEpisode.getDateStarted() != null) {
                if (watchedEpisode.getUser().equals(user) &&
                        watchedEpisode.getEpisode().getSeason().getTvSeries().equals(tvSeries) &&
                        watchedEpisode.getDateStarted().getMonthValue() == month) {
                    time += watchedEpisode.getEpisode().getDuration();
                }
            }
        }
        return time;

    }

    public List<WatchedEpisode> findWatchedEPisodesByUser(User user) {
        List<WatchedEpisode> watchedEpisodes = new ArrayList<>();
        for (WatchedEpisode watchedEpisode : watchedEpisodeRepository.findAll() ) {
            if(watchedEpisode.getUser().equals(user)) {
                watchedEpisodes.add(watchedEpisode);
            }
        }
        // makes sure there aren't any duplicates in the list
        Set<WatchedEpisode> watchedEpisodeSet = new HashSet<>(watchedEpisodes);
        watchedEpisodes.clear();
        watchedEpisodes.addAll(watchedEpisodeSet);
        return watchedEpisodes;
    }





    public WatchedEpisodeService(WatchedEpisodeRepository watchedEpisodeRepository) {
        this.watchedEpisodeRepository = watchedEpisodeRepository;
    }


}
