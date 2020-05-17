package com.example.tvseriestrackingwebapp.ui;

import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.example.tvseriestrackingwebapp.ui.views.*;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "dashboard")
@PreserveOnRefresh
public class MainLayout extends AppLayout {

    public MainLayout() {

        if (ComponentUtil.getData(UI.getCurrent(), User.class) == null) {
            UI.getCurrent().navigate("error");
        } else {
            createHeader();
            createDrawer();
        }
    }

    private void createHeader() {

        H2 userGreeting = new H2("Hello, " + ComponentUtil.getData(UI.getCurrent(), User.class).getFirstName());

        userGreeting.addClassName("logo");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), userGreeting);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");


        addToNavbar(header);

    }

    private void createDrawer() {

        RouterLink statsLink = new RouterLink("Stats", StatsView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink tvSeriesLink = new RouterLink("TV Series", TvSeriesView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink requestsLink = new RouterLink("Friend Requests", FriendRequestsView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink usersLink = new RouterLink("People You May Know", UsersView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink episodesListLink = new RouterLink("My List", EpisodesListView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink friendsLink = new RouterLink("Friends", FriendsView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink logOutLink = new RouterLink("Log out", SignInView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink challengesLink = new RouterLink("Challenges", ChallengesView.class);
        statsLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(statsLink, tvSeriesLink, episodesListLink, friendsLink, challengesLink, requestsLink, usersLink, logOutLink));
    }
}
