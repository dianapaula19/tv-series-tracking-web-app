package com.example.tvseriestrackingwebapp.ui.components;

import com.example.tvseriestrackingwebapp.backend.service.RequestService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class RequestComponent extends VerticalLayout {

    public Button acceptRequestButton = new Button("Accept");
    public Button deleteRequestButton = new Button("Delete");

    public RequestComponent(String firstName, String lastName) {
        add(
                new H4(firstName + " " + lastName),
                new HorizontalLayout(
                acceptRequestButton,
                deleteRequestButton
                )
        );
    }




}
