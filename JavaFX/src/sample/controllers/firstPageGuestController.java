package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Main;
import sample.util.BaseController;

public class firstPageGuestController extends BaseController {

    @FXML
    void initialize(){
    }

    public void btnAthletesAction(ActionEvent actionEvent) {
        Main.getNavigation().load("/sample/views/athletesPageGuest.fxml").Show();
    }

    public void btnCoachesAction(ActionEvent actionEvent) {
        Main.getNavigation().load("/sample/views/coachesPageGuest.fxml").Show();
    }

    public void btnExitAction(ActionEvent actionEvent) {
        Main.getNavigation().load("/sample/views/loginPage.fxml").Show();
    }

}
