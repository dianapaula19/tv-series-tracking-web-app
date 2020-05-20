package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Challenge;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.ChallengeService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "challenges", layout = MainLayout.class)
public class ChallengesView extends VerticalLayout {
    private ChallengeService challengeService;

    public ChallengesView(ChallengeService challengeService) {
        this.challengeService = challengeService;
        if(ComponentUtil.getData(UI.getCurrent(), User.class) == null) {
            UI.getCurrent().navigate("error");
        } else {
            updateChallengesList();
        }
    }

    public void updateChallengesList() {
        for (Challenge c : challengeService.findAllByUser(ComponentUtil.getData(UI.getCurrent(), User.class))) {
            add(new ChallengeComponent(c, challengeService));
        }
    }

    public static class ChallengeComponent extends VerticalLayout {
        private Challenge challenge;
        private ChallengeService challengeService;


        public ChallengeComponent(Challenge challenge, ChallengeService challengeService) {
            this.challenge = challenge;
            this.challengeService = challengeService;
            Button completeChallenge = new Button("Complete the challenge");
            completeChallenge.addClickListener(buttonClickEvent -> {
                challengeService.delete(challenge);
                removeAll();
            });
            completeChallenge.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            add(new H4("You were challenged by " +
                    challenge.getRequest().getUser().getFirstName() + " " + challenge.getRequest().getUser().getLastName() +
                    " to binge watch " + challenge.getTvSeries().getTitle()),
                    completeChallenge);

        }
    }
}
