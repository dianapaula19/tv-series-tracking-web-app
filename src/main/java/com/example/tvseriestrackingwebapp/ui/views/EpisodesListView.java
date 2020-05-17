package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.models.WatchedEpisode;
import com.example.tvseriestrackingwebapp.backend.service.WatchedEpisodeService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "watched_eps", layout = MainLayout.class)
public class EpisodesListView extends VerticalLayout {

    private WatchedEpisodeService watchedEpisodeService;

    public EpisodesListView(WatchedEpisodeService watchedEpisodeService) {
        this.watchedEpisodeService = watchedEpisodeService;
        ComboBox<TvSeries> tvSeriesComboBox = new ComboBox<>();
        tvSeriesComboBox.setItemLabelGenerator(TvSeries::getTitle);
        tvSeriesComboBox.setItems(watchedEpisodeService.findWatchedTvSeries(ComponentUtil.getData(UI.getCurrent(), User.class)));
        VerticalLayout episodeListView = new VerticalLayout();
        tvSeriesComboBox.addValueChangeListener(event -> {
            episodeListView.removeAll();
            updateList(event.getValue(), episodeListView);
        });
        add(tvSeriesComboBox, episodeListView);

    }
    public void updateList(TvSeries tvSeries, VerticalLayout verticalLayout) {

        for (WatchedEpisode w : watchedEpisodeService.findWatchedEPisodesByUser(ComponentUtil.getData(UI.getCurrent(), User.class))) {
            if(w.getEpisode().getSeason().getTvSeries().equals(tvSeries)) {
                verticalLayout.add(new WatchedEpisodeComponent(w, watchedEpisodeService));
            }
        }
    }

    public static class WatchedEpisodeComponent extends VerticalLayout {

        private WatchedEpisodeService watchedEpisodeService;
        private WatchedEpisode watchedEpisode;
        public WatchedEpisodeComponent(WatchedEpisode watchedEpisode, WatchedEpisodeService watchedEpisodeService) {
            this.watchedEpisodeService = watchedEpisodeService;
            this.watchedEpisode = watchedEpisode;
            if(ComponentUtil.getData(UI.getCurrent(), User.class) == null) {
                UI.getCurrent().navigate("error");
            } else {
                Button removeButton = new Button("Delete");
                removeButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
                DatePicker datePicker = new DatePicker();
                datePicker.setValue(watchedEpisode.getDateStarted());
                datePicker.addValueChangeListener(event -> {
                    if (event.getValue() != null) {
                        watchedEpisode.setDateStarted(event.getValue());
                        watchedEpisodeService.save(watchedEpisode);
                    }
                });
                removeButton.addClickListener(buttonClickEvent -> {
                    watchedEpisodeService.remove(watchedEpisode);
                    removeAll();
                });
                add(
                        new H4(watchedEpisode.getEpisode().getTitle()),
                        new H5("Season: " + watchedEpisode.getEpisode().getSeason().getSeasonNumber()),
                        new Details("Description", new Span(watchedEpisode.getEpisode().getDescription())),
                        datePicker,
                        removeButton
                );
            }
        }
    }
}
