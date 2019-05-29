package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import sample.Main;
import sample.util.BaseController;
import javafx.scene.control.PasswordField;

public class loginPageController extends BaseController {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    void btnInputAction(ActionEvent event) {

        if(loginTextField.getText().equals("qwerty") && passwordTextField.getText().equals("zxcv5565")){
            loginTextField.setText("");
            passwordTextField.setText("");
            Main.getNavigation().load("/sample/views/firstPage.fxml").Show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный логин или пароль.");
            alert.showAndWait();
        }
    }

    @FXML
    void guestPagesAction(ActionEvent event) {
        Main.getNavigation().load("/sample/views/firstPageGuest.fxml").Show();
    }
}
