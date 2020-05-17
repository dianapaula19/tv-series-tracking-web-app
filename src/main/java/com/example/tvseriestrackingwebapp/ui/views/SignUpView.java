package com.example.tvseriestrackingwebapp.ui.views;

import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.example.tvseriestrackingwebapp.ui.components.SignUpForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("registration")
public class SignUpView extends VerticalLayout {

    private UserService userService;

    public SignUpView(UserService userService) {
        this.userService = userService;
        SignUpForm signUpForm = new SignUpForm(userService);
        H2 header = new H2("Register");
        HorizontalLayout horizontalLayout = new HorizontalLayout(header, signUpForm);

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
