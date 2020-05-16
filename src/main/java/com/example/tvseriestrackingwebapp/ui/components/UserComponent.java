package com.example.tvseriestrackingwebapp.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserComponent extends VerticalLayout {

    public Button sendFriendRequestButton = new Button("Add Friend");
    public UserComponent(String firstName, String lastName) {
        add(
                new H4(firstName + " " + lastName),
                sendFriendRequestButton
        );
    }
}
