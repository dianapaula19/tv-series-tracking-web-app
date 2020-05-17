package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.example.tvseriestrackingwebapp.ui.components.SignInForm;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class SignInView  extends VerticalLayout {
    private UserService userService;

    public SignInView(UserService userService) {
        this.userService = userService;
        SignInForm signInForm = new SignInForm(userService);
        H2 header = new H2("Sign in");
        HorizontalLayout horizontalLayout = new HorizontalLayout(signInForm);

        horizontalLayout.setWidthFull();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        setHeightFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER, header);

        add(header, horizontalLayout);
    }
}
