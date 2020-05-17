package com.example.tvseriestrackingwebapp.ui.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("error")
public class ErrorView extends VerticalLayout {
    public ErrorView() {

        add(new H1("You must be logged in to access this page"),
                new Anchor("", "Log in")
        );
    }
}
