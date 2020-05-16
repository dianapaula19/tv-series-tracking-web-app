package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Request;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.RequestService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Route(value = "requests", layout = MainLayout.class)
@PreserveOnRefresh
public class FriendRequestsView extends VerticalLayout {

    private RequestService requestService;
    public FriendRequestsView(RequestService requestService) {
        this.requestService = requestService;
        if (SignInForm.currentUser == null)
            UI.getCurrent().navigate("error");
        updateList();
    }
    public void updateList() {

        for (User u : requestService.friendRequests(SignInForm.currentUser)) {
            if(u.equals(SignInForm.currentUser) == false) {
                add(new FriendRequestComponent(u, requestService));
            }
        }

    }
    public static class FriendRequestComponent extends VerticalLayout {
        private RequestService requestService;
        private User user;
        public FriendRequestComponent(User user, RequestService requestService) {
            this.requestService = requestService;
            this.user = user;
            Button acceptRequestButton = new Button("Accept");
            Button deleteRequestButton = new Button("Delete");
            acceptRequestButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            acceptRequestButton.addClickListener(buttonClickEvent -> {
                requestService.save(new Request(SignInForm.currentUser, user));
                removeAll();
            });
            deleteRequestButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteRequestButton.addClickListener(buttonClickEvent -> {
               requestService.delete(new Request(user, SignInForm.currentUser));
               removeAll();
            });
            add(
                    new H4(user.getFirstName() + " " + user.getLastName()),
                    new HorizontalLayout(
                            acceptRequestButton,
                            deleteRequestButton
                    )
            );
        }
    }
}
