package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Episode;
import com.example.tvseriestrackingwebapp.backend.models.Season;
import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.WatchedEpisode;
import com.example.tvseriestrackingwebapp.backend.service.TVSeriesService;
import com.example.tvseriestrackingwebapp.backend.service.WatchedEpisodeService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "tvseries", layout = MainLayout.class)
@PreserveOnRefresh
public class TvSeriesView extends VerticalLayout {


    private TVSeriesService tvSeriesService;
    private WatchedEpisodeService watchedEpisodeService;

    public TvSeriesView(TVSeriesService tvSeriesService, WatchedEpisodeService watchedEpisodeService) {
        this.tvSeriesService = tvSeriesService;
        this.watchedEpisodeService = watchedEpisodeService;
        if (SignInForm.currentUser == null) {
            UI.getCurrent().navigate("error");
        } else {
            updateList();
        }
    }

    private void updateList() {
        for (TvSeries t : tvSeriesService.findAll()) {
            add(new TvSeriesComponent(
                    t,
                    watchedEpisodeService
            ));
        }
    }

    public static class TvSeriesComponent extends VerticalLayout {

        private WatchedEpisodeService watchedEpisodeService;
        private TvSeries tvSeries;

        public TvSeriesComponent(TvSeries tvSeries, WatchedEpisodeService watchedEpisodeService) {
            this.watchedEpisodeService = watchedEpisodeService;
            this.tvSeries = tvSeries;
            Accordion accordion = new Accordion();
            VerticalLayout verticalLayout = new VerticalLayout();
            Details tvSeriesDetails = new Details("Description", new Span(tvSeries.getDescription()));
            Select<String> seasonSelect = new Select<>();
            verticalLayout.add(tvSeriesDetails);
            verticalLayout.add(seasonSelect);
            seasonSelect.setItems(getSeasonsTitles(tvSeries.getSeasons()));
            VerticalLayout episodeLayout = new VerticalLayout();
            seasonSelect.addValueChangeListener(event -> {
                episodeLayout.removeAll();
                String string = event.getValue();
                int seasonNumber = Integer.parseInt(string.substring(string.length() - 1));
                for (Season s:tvSeries.getSeasons()) {
                    if(s.getSeasonNumber() == seasonNumber) {
                        updateList(s, episodeLayout);
                        verticalLayout.add(episodeLayout);
                    }

                }
            });
            verticalLayout.add();
            accordion.add(tvSeries.getTitle(), verticalLayout);
            add(accordion);
        }

        public List<String> getSeasonsTitles(List<Season> seasons) {
            List<String> seasonTitles = new ArrayList<>();
            for (Season s : seasons) {
                seasonTitles.add("Season " + s.getSeasonNumber());
            }
            return seasonTitles;
        }

        public void updateList(Season season, VerticalLayout episodesLayout) {
            List<Episode> episodes = season.getEpisodes();
            Episode.order(episodes);
            for (Episode e : episodes) {
                if(e.getSeason().equals(season)) {
                    EpisodeComponent episodeComponent = new EpisodeComponent(
                            e,
                            watchedEpisodeService
                    );
                    episodesLayout.add(episodeComponent);
                }
            }
        }

        public static class EpisodeComponent extends VerticalLayout {

            private WatchedEpisodeService watchedEpisodeService;
            private Episode episode;
            public EpisodeComponent(Episode episode, WatchedEpisodeService watchedEpisodeService) {
                this.watchedEpisodeService = watchedEpisodeService;
                this.episode = episode;
                Details episodeDetails = new Details("Description", new Span(episode.getDescription()));
                Button addButton = new Button("Add to my list");
                Button alreadyAdded = new Button("Already added to my list");
                alreadyAdded.setEnabled(false);
                addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                addButton.addClickListener(buttonClickEvent -> {
                    watchedEpisodeService.save(new WatchedEpisode(SignInForm.currentUser, episode));
                });
                add(
                        new H5(episode.getTitle()),
                        new H5(episode.getDuration() + "min"),
                        episodeDetails
                );
                if (watchedEpisodeService.findByUserAndEpisode(SignInForm.currentUser, episode) == null) {
                    add(alreadyAdded);
                } else {
                    add(addButton);
                }
            }
        }


    }



}
