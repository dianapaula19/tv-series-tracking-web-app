package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Challenge;
import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.ChallengeService;
import com.example.tvseriestrackingwebapp.backend.service.TVSeriesService;
import com.example.tvseriestrackingwebapp.backend.service.WatchedEpisodeService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Route(value = "stats", layout = MainLayout.class)
@PreserveOnRefresh
public class StatsView extends VerticalLayout {


    H2 timeSpent = new H2("This is how much time you spent watching tv series in " + getCurrentYear());

    private WatchedEpisodeService watchedEpisodeService;
    private TVSeriesService tvSeriesService;
    private ChallengeService challengeService;

    public StatsView(WatchedEpisodeService watchedEpisodeService, TVSeriesService tvSeriesService, ChallengeService challengeService) {
        this.watchedEpisodeService = watchedEpisodeService;
        this.tvSeriesService = tvSeriesService;
        this.challengeService = challengeService;
        if (ComponentUtil.getData(UI.getCurrent(), User.class) == null) {
            UI.getCurrent().navigate("error");
        } else {
            VerticalLayout verticalLayout = new VerticalLayout();
            ComboBox <Month> timeComboBox = new ComboBox();
            timeComboBox.setItemLabelGenerator(Month::getName);
            timeComboBox.setItems(monthList());
            timeComboBox.addValueChangeListener(event -> {
                verticalLayout.removeAll();
                updateList(event.getValue().getNumber(), verticalLayout);
            });
            add(timeSpent, timeComboBox, verticalLayout);

        }
    }


    public static int getCurrentYear() {
        Date d = new Date();
        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    public void updateList(int month, VerticalLayout verticalLayoutStats) {
        for (TvSeries t:watchedEpisodeService.findWatchedTvSeries(ComponentUtil.getData(UI.getCurrent(), User.class))) {
            String message = t.getTitle() + ": ";
            int hours = watchedEpisodeService.timeSpentPerMonthPerTvSeries(month, t, ComponentUtil.getData(UI.getCurrent(), User.class)) / 60;
            int mins = watchedEpisodeService.timeSpentPerMonthPerTvSeries(month, t, ComponentUtil.getData(UI.getCurrent(), User.class));
            if(hours != 0){
                switch (hours) {
                    case 1:
                        message += hours + " hour";
                        break;
                    default:
                        message += hours + " hours";
                }
                verticalLayoutStats.add(new H4(message));
            } else if (mins != 0) {
                message += "0 hours and " + mins + " mins";
                verticalLayoutStats.add(new H4(message));
            }
        }
    }

    public List<Month> monthList() {
        List<Month> monthList = new ArrayList<>();
        for (int i = 1; i < 13 ; ++i) {
            monthList.add(new Month(i));

        }
        return monthList;
    }

    public static class Month {
        public int number;
        public String name = "";

        public Month(int number) {
            this.number = number;
            this.name = getMonthName(number);
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public String getMonthName(int month) {
            switch(month) {
                case 1:
                    return "January";
                case 2:
                    return "February";
                case 3:
                    return "March";
                case 4:
                    return "April";
                case 5:
                    return "May";
                case 6:
                    return "June";
                case 7:
                    return "July";
                case 8:
                    return "August";
                case 9:
                    return "September";
                case 10:
                    return "October";
                case 11:
                    return "November";
                case 12:
                    return "December";
                default:
                    return "Not a valid month";
            }

        }

    }

}


