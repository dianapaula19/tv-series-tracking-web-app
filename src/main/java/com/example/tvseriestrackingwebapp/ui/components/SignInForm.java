package com.example.tvseriestrackingwebapp.ui.components;

import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.example.tvseriestrackingwebapp.ui.MainLayout;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Inline;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import org.aspectj.weaver.Position;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Route("")
public class SignInForm extends FormLayout {


    private UserService userService;
    public static User currentUser;

    TextField username = new TextField("username");
    PasswordField password = new PasswordField("password");

    Button signInButton = new Button("Sign in");

    Span notAMember = new Span("Not a member?");
    Anchor signUp = new Anchor("registration", "Sign Up");


    public SignInForm(UserService userService) {

        this.userService = userService;
        currentUser = null;

        signInButton.addClickListener(buttonClickEvent -> SignInEvent());
        signInButton.addClickShortcut(Key.ENTER);

        username.setClearButtonVisible(true);
        password.setClearButtonVisible(true);

        add(new VerticalLayout(
                new H2("Sign in"),
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
            currentUser = user;

            UI.getCurrent().navigate("dashboard");
        } else {
            com.vaadin.flow.component.notification.Notification notification = new Notification(
                    "BAD CREDENTIALS", 3000);
            notification.open();
        }
    }





}
