

package Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**

        * This class creates the Controller for the AddPart scene
         */
public class AddPartController implements Initializable {



    /**
     Initializing the FXML View Files
     * */
    @FXML
    public TextField AddMaxPrice;
    @FXML
    private ToggleGroup tgroup;
    @FXML
    private Label ChangeMe;
    @FXML
    private TextField AddPartName;

    @FXML
   private TextField AddPartInventory;

    @FXML
    private TextField AddPartMachineID;

    @FXML
    private TextField AddPartPrice;

    @FXML
    private TextField AddPartMin;

    @FXML
    private TextField AddPartID;
   @FXML
    private Button CancelAddPart;

   @FXML
    private RadioButton InHouseRadio;

   @FXML
    private Button SaveAddPart;

   @FXML
    private RadioButton OutSourcedRadioButton;

    @Override
    /**This method initializes the class.
      * */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized AddPartController!");


    }
    /**This method cancels Add Part page and redirects to main page.
     @param actionEvent cancels Add Part page and redirects to main page
     */
    public void OnCancelAddPart(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/view/Main Screen.fxml"));
        Scene MainScene = new Scene(parent,1300,1000);
        Stage MainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        MainStage.setScene(MainScene);
        parent.setStyle("-fx-font-family: Times New Roman;");
        MainStage.setTitle("Add Product");
        MainStage.show();


    }
    /**This method sets the In House radio button to MachindID.
      @param actionEvent sets to MachineID
     */

    public void onInHouseRadio(ActionEvent actionEvent) {
        ChangeMe.setText("Machine ID");
    }
    /**This method saves a new part and sends the user back to the main page.
      @param actionEvent saves new part and sends user back to main page
     */
    public void onSaveAddPart(ActionEvent actionEvent) throws IOException {
        try {
            String PartName = AddPartName.getText();
            Integer PartInventory = Integer.parseInt(AddPartInventory.getText());
            Double PartPrice = Double.parseDouble(AddPartPrice.getText());
            Integer PartMax = Integer.parseInt(AddMaxPrice.getText());
            Integer PartMin = Integer.parseInt(AddPartMin.getText());
            String NameID = AddPartMachineID.getText();
            if (PartMax < PartMin) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setContentText("Error: Min must be greater than Max");
                newAlert.setHeaderText("Error");
                newAlert.showAndWait();
                return;
            } else if (PartInventory < PartMin || PartInventory > PartMax) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setHeaderText("Error");
                newAlert.setContentText("Error: Inventory must be between Min and Max");
                newAlert.showAndWait();
                return;
            }


            int min = 1;
            int max = 100000;
            int newInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

            if(OutSourcedRadioButton.isSelected() == false && InHouseRadio.isSelected() == false){
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setHeaderText("Error");
                newAlert.setContentText("Please select either In-House or Outsourced for part type");
                newAlert.showAndWait();
                return;

            }


            if (OutSourcedRadioButton.isSelected()) {

                OutSourced newOutsourced = new OutSourced(newInt, PartName, PartPrice, PartInventory, PartMin, PartMax, NameID);
                Inventory.addPart(newOutsourced);
            }
            if (InHouseRadio.isSelected()) {
                Integer CompanyID = Integer.parseInt(NameID);
                InHouse newInhouse = new InHouse(newInt, PartName, PartPrice, PartInventory, PartMin, PartMax, CompanyID);
                Inventory.addPart(newInhouse);
            }

            Parent parentMain = FXMLLoader.load(getClass().getResource("/view/Main Screen.fxml"));
            Scene MainScene = new Scene(parentMain, 1300, 1000);
            Stage MainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            MainStage.setScene(MainScene);
            parentMain.setStyle("-fx-font-family: Times New Roman;");
            MainStage.setTitle("Main Screen");
            MainStage.show();
        } catch (NumberFormatException e) {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setContentText("Error, data has been entered incorrectly." +
                    "Please check your inputs. Inventory should be a number between the min and max." +
                    "min cannot be larger than max or max smaller than min" +
                    "Price/Cost, Inventory, Min/Max and Machine Id should be numbers" +
                    "either 'In-House' or 'Outsourced' must be selected");
            newAlert.showAndWait();
            return;

        }
    }



    /**This method sets the Out Sourced Radio button to be selected.
      @param actionEvent sets to Company Name
     */
    public void onOutSourcedRadioButton(ActionEvent actionEvent) {
        ChangeMe.setText("Company Name");
    }
    /** This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */

    public void onAddPartID(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */

    public void onAddPartName(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddPartInventory(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddPartPrice(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddPartMachineID(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddPartMin(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddMaxPrice(ActionEvent actionEvent) {
    }
}