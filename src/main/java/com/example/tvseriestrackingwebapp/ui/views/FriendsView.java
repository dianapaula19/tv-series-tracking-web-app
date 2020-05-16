package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Challenge;
import com.example.tvseriestrackingwebapp.backend.models.Request;
import com.example.tvseriestrackingwebapp.backend.models.TvSeries;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.ChallengeService;
import com.example.tvseriestrackingwebapp.backend.service.RequestService;
import com.example.tvseriestrackingwebapp.backend.service.TVSeriesService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "friends", layout = MainLayout.class)
public class FriendsView extends VerticalLayout {

    private RequestService requestService;
    private TVSeriesService tvSeriesService;
    private ChallengeService challengeService;

    public FriendsView(RequestService requestService, TVSeriesService tvSeriesService, ChallengeService challengeService) {
        this.tvSeriesService = tvSeriesService;
        this.requestService = requestService;
        this.challengeService = challengeService;
        updateList();
    }

    public void updateList() {
        for (User u : requestService.friendsofUser(SignInForm.currentUser)) {
            add(new FriendComponent(u, tvSeriesService, challengeService));
        }
    }

    public static class FriendComponent extends VerticalLayout {

        private User user;
        private TVSeriesService tvSeriesService;
        private ChallengeService challengeService;

        public FriendComponent(User user, TVSeriesService tvSeriesService, ChallengeService challengeService) {
            this.user = user;
            this.tvSeriesService = tvSeriesService;
            this.challengeService = challengeService;
            add(new H4(user.getFirstName() + " " + user.getLastName()));
            ComboBox<TvSeries> tvSeriesComboBox = new ComboBox<>();
            List<TvSeries> tvSeries = tvSeriesService.findAll();
            tvSeriesComboBox.setItemLabelGenerator(TvSeries::getTitle);
            tvSeriesComboBox.setItems(tvSeries);
            tvSeriesComboBox.addValueChangeListener(event -> {
               if(event.getValue() != null) {
                    challengeService.save(new Challenge(new Request(SignInForm.currentUser, user), event.getValue()));
               }
            });
            add(new HorizontalLayout(new H5("Challenge to binge watching: "), tvSeriesComboBox));
        }
    }
}
