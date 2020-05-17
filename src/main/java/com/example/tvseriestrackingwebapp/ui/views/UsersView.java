package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.models.Request;
import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.RequestService;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Route(value = "users", layout = MainLayout.class)
@PreserveOnRefresh
public class UsersView extends VerticalLayout {

    private UserService userService;
    private RequestService requestService;

    public UsersView(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
        if(ComponentUtil.getData(UI.getCurrent(), User.class) == null) {

        } else {
            updateList();
        }
    }


    public void updateList() {
        for (User u : userService.findAll()) {
            if(requestService.requestExists(u, ComponentUtil.getData(UI.getCurrent(), User.class)) == false &&
                    requestService.requestExists(ComponentUtil.getData(UI.getCurrent(), User.class), u) == false &&
                    u.equals(ComponentUtil.getData(UI.getCurrent(), User.class)) == false
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
                requestService.save(new Request(ComponentUtil.getData(UI.getCurrent(), User.class), user));
                removeAll();
            });
            add(
                    new H4(user.getFirstName() + " " + user.getLastName()),
                    sendFriendRequestButton
                    );
        }
    }
}
