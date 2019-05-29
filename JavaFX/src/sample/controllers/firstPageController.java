package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import sample.util.BaseController;
import sample.Main;

public class firstPageController extends BaseController {

    @FXML
    void initialize(){
    }

    public void btnAthletesAction(ActionEvent actionEvent) {
        Main.getNavigation().load("/sample/views/athletesPage.fxml").Show();
    }

    public void btnCoachesAction(ActionEvent actionEvent) {
        Main.getNavigation().load("/sample/views/coachesPage.fxml").Show();
    }

    public void btnExitAction(ActionEvent actionEvent) {
        Main.getNavigation().load("/sample/views/loginPage.fxml").Show();
    }
}