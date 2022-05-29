package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserNameTransporterService;
import org.loose.fis.sre.services.UserRoleTransporterService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Student", "Admin");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");

            String roleString = (String) role.getValue();

            if (roleString.equals("Student")) {
                Main m = new Main();
                m.changeScene("StudentForm.fxml");
                UserNameTransporterService.setUsername(usernameField);
                UserRoleTransporterService.setRole(roleString);
            } else if (roleString.equals("Farmer")) {
                Main m = new Main();
                m.changeScene("AdminForm.fxml");
                UserNameTransporterService.setUsername(usernameField);
                UserRoleTransporterService.setRole(roleString);
            }
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (IOException e) {
            registrationMessage.setText("error");
        }
    }

    @FXML
    public void handleLoginAction() {
        try {
            Main m = new Main();
            m.changeScene("login.fxml");
        } catch (IOException e) {
            registrationMessage.setText("error");
        }
    }
}
