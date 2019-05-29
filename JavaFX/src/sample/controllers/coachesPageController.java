package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import sample.Main;
import sample.model.persons.Coach;
import java.io.File;
import javafx.stage.FileChooser;

public class coachesPageController extends BaseController {

    private CollectionCoaches collectionCoaches = new CollectionCoaches();

    @FXML
    private Label surnameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label otchLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label typeOfSportLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private TableView<Coach> personTable;
    @FXML
    private TableColumn<Coach, String> firstNameColumn;
    @FXML
    private TableColumn<Coach, String> lastNameColumn;
    @FXML
    private TextField searchTextField;

    public Coach getCoach() {
        return coach;
    }
    private Coach coach;

    @FXML
    void btnToFirstPageAction(ActionEvent event) {
        Main.getNavigation().load("/sample/views/firstPage.fxml").Show();
    }

    @FXML
    private void initialize() {
        collectionCoaches.fillTestData();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Coach, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Coach, String>("secondName"));

        personTable.setItems(collectionCoaches.getCoaches());

        showAllInformation(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAllInformation(newValue));

    }

    private void showAllInformation(Coach coach) {
        if (coach != null) {
            surnameLabel.setText(coach.getSecondName());
            nameLabel.setText(coach.getFirstName());
            otchLabel.setText(coach.getOtchestvo());
            birthdayLabel.setText(WorkingWithDate.format(coach.getBirthday()));
            typeOfSportLabel.setText(coach.getTypeOfSports().getName());
            categoryLabel.setText(WorkingWithTextFiles.nameOfCategory(coach.getCategory().toString()));
        } else {
            birthdayLabel.setText("");
            surnameLabel.setText("");
            nameLabel.setText("");
            otchLabel.setText("");
            birthdayLabel.setText("");
            typeOfSportLabel.setText("");
            categoryLabel.setText("");
        }
    }

    @FXML
    private void btnDeleteAction() {
        Coach currentCoach = personTable.getSelectionModel().getSelectedItem();
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);

            for(int i = 0; i < collectionCoaches.getCoaches().size(); i++){
                if(collectionCoaches.getCoaches().get(i).equals(currentCoach)){
                    collectionCoaches.coaches.remove(collectionCoaches.getCoaches().get(i));
                }
            }

            WorkingWithTextFiles.inputInformation(collectionCoaches.getCoaches());

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет выделенного объекта");
            alert.setContentText("Выделите объект, который желаете удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnAddAction() {
        Main.getNavigation().load("/sample/views/addPage.fxml").Show();
    }

    @FXML
    private void btnEditAction() {
        Coach currentCoach = personTable.getSelectionModel().getSelectedItem();
        if (currentCoach != null) {
            WorkingWithTextFiles.inputInformation(currentCoach);
            Main.getNavigation().load("/sample/views/editPage.fxml").Show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет выделенного объекта");
            alert.setContentText("Чтобы изменить данные о тренере, надо его выделить.");
            alert.showAndWait();
        }
    }

    @FXML
    void searchButtonAction(ActionEvent event) {
        String searchString = searchTextField.getText();

        List<Coach> persons = personTable.getItems();
        List<Coach> findCoaches = WorkingWithListsOfPersons.searchByName(searchString,persons);
        findCoaches.addAll(WorkingWithListsOfPersons.searchBySecondName(searchString,persons));

        ObservableList<Coach> needingList = FXCollections.observableArrayList(findCoaches);

        if(needingList.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Нет совпадений");
            alert.setContentText("Человека с таким именем или фамилией нет.");
            alert.showAndWait();
            personTable.getItems().clear();
            Main.getNavigation().load("/sample/views/coachesPage.fxml").Show();
        }
        else{
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<Coach, String>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Coach, String>("secondName"));

            personTable.setItems(needingList);

            showAllInformation(null);
            personTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showAllInformation(newValue));
        }
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        Main main = new Main();

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.coachesFromFile(file);
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        Main main = new Main();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.coachesToXml(file);
        }
    }
}