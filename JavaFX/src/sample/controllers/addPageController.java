package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.TypeOfSports;
import sample.util.BaseController;
import sample.util.WorkingWithDate;
import sample.util.WorkingWithTextFiles;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addPageController extends BaseController {
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField otchField;
    @FXML
    public TextField birthdayField;
    @FXML
    private ComboBox<String> typesBox;
    @FXML
    private ComboBox<String> categoryBox;

    @FXML
    private void initialize() {
        //ComboBox отделения
        List<TypeOfSports> types = WorkingWithTextFiles.getAllTypesOfSports();
        List<String> typeOfSportChoose = new ArrayList<>();
        String s;
        for(int i = 0; i < types.size(); i++ ){
            s = types.get(i).getName();
            typeOfSportChoose.add(s);
        }
        typesBox.getItems().addAll(typeOfSportChoose);
        //ComboBox категории
        List<String> categoriesRussian = new ArrayList<>();
        categoriesRussian.add("Молодой специалист");
        categoriesRussian.add("Вторая категория");
        categoriesRussian.add("Первая категория");
        categoriesRussian.add("Высшая категория");
        categoryBox.getItems().addAll(categoriesRussian);
    }

    @FXML
    private void btnOkAction() {
        if (isInputValid()) {
            try(FileWriter writer = new FileWriter("allCoachesAtTheMoment.txt", true))
            {
                String text = firstNameField.getText()+ " "+lastNameField.getText()+" "
                        +otchField.getText()+" "+ birthdayField.getText()
                        +" "+WorkingWithTextFiles.categoryFromRussian(categoryBox.getSelectionModel().getSelectedItem()).toString()
                        +" "+whatISTypeOfSports().getName()+ "\n";

                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            Main.getNavigation().load("/sample/views/coachesPage.fxml").Show();
        }
    }

    @FXML
    void btnCancelAction(ActionEvent event) {
        Main.getNavigation().GoBack();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0 || isValisString(firstNameField.getText()) == false) {
            errorMessage += "Некорректно написано имя.\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0 || isValisString(lastNameField.getText()) == false) {
            errorMessage += "Некорректно написана фамилия.\n";
        }
        if (otchField.getText() == null || otchField.getText().length() == 0 || isValisString(otchField.getText()) == false) {
            errorMessage += "Некорректно написано отчество.\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0 || WorkingWithDate.validDate(birthdayField.getText()) == false) {
            errorMessage += "Неправильно написана дата рождения.\n";
        }
        String type = typesBox.getSelectionModel().getSelectedItem();
        if(type == null){
            errorMessage += "Не выбран вид спорта.\n";
        }

        String categoryStr = categoryBox.getSelectionModel().getSelectedItem();
        if(categoryStr == null){
            errorMessage += "Не выбрана категория.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Неправильное заполнение полей");
            alert.setHeaderText("Проверьте правильность введённых данных.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    private boolean isValisString(String thisString){
        int k =0;
        char[] charArray = thisString.toCharArray();
        char[] chars = {'.',',',' ','-','!','?','"',';',':','(',')','/',
        '1','2','3','4','5','6','7','8','9','0'};
        for(int i = 0; i < charArray.length; i++){
            for(int j = 0; j < chars.length; j++){
                if(charArray[i]==chars[j]){
                    k++;
                }
            }
        }
        if(k==0){return true;}
        else{return false;}
    }

    private TypeOfSports whatISTypeOfSports(){
        TypeOfSports ts = new TypeOfSports("");
        String type = typesBox.getSelectionModel().getSelectedItem();
        if(type != null){
            List<TypeOfSports> types = WorkingWithTextFiles.getAllTypesOfSports();
            for(int i = 0; i < types.size(); i++ ){
                if(types.get(i).getName().equals(type)){
                    ts = types.get(i);
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Неправильное заполнение полей");
            alert.setHeaderText("Проверьте правильность введённых данных.");
            alert.setContentText("Не выбран вид спорта");
            alert.showAndWait();
        }
        return ts;
    }
}
