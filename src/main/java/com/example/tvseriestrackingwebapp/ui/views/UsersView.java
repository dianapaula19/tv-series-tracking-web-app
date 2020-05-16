package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Request;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.RequestService;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.example.tvseriestrackingwebapp.ui.components.UserComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import javax.swing.*;
import java.util.Iterator;

@Route(value = "users", layout = MainLayout.class)
@PreserveOnRefresh
public class UsersView extends VerticalLayout {

    private UserService userService;
    private RequestService requestService;

    public UsersView(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;


        updateList();
    }


    public void updateList() {
        for (User u : userService.findAll()) {
            if(requestService.requestExists(u, SignInForm.currentUser) == false &&
                    requestService.requestExists(SignInForm.currentUser, u) == false &&
                    u.equals(SignInForm.currentUser) == false
            ) {
                add(new UserComponent(u, requestService));
            }
        }

    }

    public static class UserComponent extends VerticalLayout {
        private User user;
        private RequestService requestService;

        public UserComponent(User user, RequestService requestService) {
            this.user = user;
            this.requestService = requestService;
            Button sendFriendRequestButton = new Button("Add friend");
            sendFriendRequestButton.addClickListener(buttonClickEvent -> {
                requestService.save(new Request(SignInForm.currentUser, user));
                removeAll();
            });
            add(
                    new H4(user.getFirstName() + " " + user.getLastName()),
                    sendFriendRequestButton
                    );
        }
    }
}
