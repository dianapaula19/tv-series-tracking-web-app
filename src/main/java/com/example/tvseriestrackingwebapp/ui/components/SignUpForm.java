package com.example.tvseriestrackingwebapp.ui.components;


import com.example.tvseriestrackingwebapp.backend.models.User;
import com.example.tvseriestrackingwebapp.backend.service.UserService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("registration")
public class SignUpForm extends FormLayout {


    private UserService userService;
    H1 header = new H1("Register");

    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");
    EmailField email = new EmailField("Email");
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");

    Button signUpButton = new Button("Sign up");

    Span alreadySignedUp = new Span("Already registered?");
    Anchor goToLoginForm = new Anchor("login","Sign in");


    public SignUpForm(UserService userService) {

        this.userService = userService;
        signUpButton.addClickListener(buttonClickEvent -> signUpEvent());
        add(new VerticalLayout(
                header,
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
        User user = new User(username.getValue(),
                password.getValue(),
                email.getValue(),
                firstName.getValue(),
                lastName.getValue());
        System.out.println(user);
        if(userService.save(user)) {
            UI.getCurrent().navigate("");
        } else {
            Notification notification = new Notification(
                    "This notification has text content", 3000);
            notification.open();
        }





    }











}
