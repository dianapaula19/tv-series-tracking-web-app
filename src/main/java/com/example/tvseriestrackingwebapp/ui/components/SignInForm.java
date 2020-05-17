package com.example.tvseriestrackingwebapp.ui.components;

import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;


public class SignInForm extends FormLayout {


    private UserService userService;

    TextField username = new TextField("username");
    PasswordField password = new PasswordField("password");

    Button signInButton = new Button("Sign in");

    Span notAMember = new Span("Not a member?");
    Anchor signUp = new Anchor("registration", "Sign Up");


    public SignInForm(UserService userService) {

        this.userService = userService;
        ComponentUtil.setData( UI.getCurrent() , User.class , null);
        signInButton.addClickListener(buttonClickEvent -> SignInEvent());
        signInButton.addClickShortcut(Key.ENTER);
        signInButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        username.setClearButtonVisible(true);
        password.setClearButtonVisible(true);

        add(new VerticalLayout(
                username,
                password,
                signInButton,
                notAMember,
                signUp
        ));
    }

    public void SignInEvent() {

        User user = userService.findUserByUsernameAndPassword((String)username.getValue(), (String)password.getValue());

        if(user != null) {
            ComponentUtil.setData( UI.getCurrent() , User.class , user);
            UI.getCurrent().navigate("dashboard");
        } else {
            com.vaadin.flow.component.notification.Notification notification = new Notification(
                    "BAD CREDENTIALS", 3000);
            notification.open();
        }
    }



}
