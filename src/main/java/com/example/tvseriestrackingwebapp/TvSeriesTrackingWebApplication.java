package com.example.tvseriestrackingwebapp;

import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.service.RequestService;
import com.example.tvseriestrackingwebapp.backend.service.TVSeriesService;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.example.tvseriestrackingwebapp.ui.views.StatsView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import java.util.Iterator;
import java.util.Scanner;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class TvSeriesTrackingWebApplication {


	public static void main(String[] args) {

		SpringApplication.run(TvSeriesTrackingWebApplication.class, args);

	}

}
