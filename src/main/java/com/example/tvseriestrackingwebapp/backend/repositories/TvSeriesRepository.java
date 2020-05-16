package com.example.tvseriestrackingwebapp.backend.repositories;

import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {
}
