package com.example.tvseriestrackingwebapp.ui.components;


import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.vaadin.flow.component.UI;
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

public class SignUpForm extends FormLayout {


    private UserService userService;


    TextField username = new TextField("username");
    PasswordField password = new PasswordField("password");
    TextField email = new TextField("email");
    TextField firstName = new TextField("first name");
    TextField lastName = new TextField("last name");

    Button signUpButton = new Button("Sign up");

    Span alreadySignedUp = new Span("Already registered?");
    Anchor goToLoginForm = new Anchor("","Sign in");


    public SignUpForm(UserService userService) {

        this.userService = userService;
        signUpButton.addClickListener(buttonClickEvent -> signUpEvent());
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(new VerticalLayout(
                username,
                password,
                email,
                firstName,
                lastName,
                signUpButton,
                alreadySignedUp,
                goToLoginForm
        ));
    }

    public void signUpEvent() {
        User user = new User(
                username.getValue(),
                password.getValue(),
                email.getValue(),
                firstName.getValue(),
                lastName.getValue());
        if(userService.isValid(user) == null) {
            System.out.println("ok");
            userService.save(user);
            UI.getCurrent().navigate("");
        } else {
            System.out.println("not ok");
            for (String s : userService.isValid(user)) {
                Notification notification = new Notification(s, 3000);
                notification.open();
            }
        }

    }













}
