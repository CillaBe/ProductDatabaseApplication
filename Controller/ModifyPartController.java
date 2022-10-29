package Controller;

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
 * This class creates the Controller for the Modify Part scene
         */
public class ModifyPartController implements Initializable {

    /**
     * Initializing the FXML View Files
     */
    @FXML
    private Label modifyMachinePart;

    @FXML

    private ToggleGroup tmodify;
    @FXML
    private Button CancelModifyPart;

    @FXML
    private Button SaveModifyPart;

    @FXML
    private RadioButton ModifyOutSourcedPart;

    @FXML
    private RadioButton ModifyPartInHouseRadio;

    @FXML
    private TextField ModifyPartID;

    @FXML
    private TextField ModifyPartName;

    @FXML
    private TextField ModifyPartInventory;

    @FXML
    private TextField ModifyPartPrice;

    @FXML
    private TextField ModifyPartMax;

    @FXML
    private TextField ModifyPartMachineID;

    @FXML
    private TextField ModifyPartMin;

    @FXML
    private int PartToModifyID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized ModifyPartController!");
    }

    /**This creates method for pulling a part to modify to the Modify Parts Screen,it brings over the index and part for the part to be modified.
      @param PartInt brings over index for part to be modified
      @param selectedPart brings over the part to be modified

     */
    public void getPartToModify(int PartInt, Part selectedPart) {
        PartToModifyID = PartInt;
        if (selectedPart instanceof InHouse) {

            ModifyPartInHouseRadio.setSelected(true);
            ModifyPartID.setText(String.valueOf(selectedPart.getId()));
            ModifyPartName.setText(selectedPart.getName());
            ModifyPartInventory.setText(String.valueOf(selectedPart.getStock()));
            ModifyPartPrice.setText(String.valueOf(selectedPart.getPrice()));
            ModifyPartMax.setText(String.valueOf(selectedPart.getMax()));
            ModifyPartMin.setText(String.valueOf(selectedPart.getMin()));
            ModifyPartMachineID.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));


        }
        if (selectedPart instanceof OutSourced) {

            ModifyOutSourcedPart.setSelected(true);
            ModifyPartID.setText(String.valueOf(selectedPart.getId()));
            ModifyPartName.setText(selectedPart.getName());
            ModifyPartInventory.setText(String.valueOf(selectedPart.getStock()));
            ModifyPartPrice.setText(String.valueOf(selectedPart.getPrice()));
            ModifyPartMax.setText(String.valueOf(selectedPart.getMax()));
            ModifyPartMin.setText(String.valueOf(selectedPart.getMin()));
            ModifyPartMachineID.setText(String.valueOf(((OutSourced) selectedPart).getCompanyName()));

        }

    }

    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyPartID(ActionEvent actionEvent) {
    }

    /**This method changes text box to "Machine Id" when the In House radio button is selected.
      @param actionEvent Changes text box to  Machine ID
     */
    public void onModifyPartInHouseRadio(ActionEvent actionEvent) {
        modifyMachinePart.setText("Machine ID");

    }

    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyPartName(ActionEvent actionEvent) {
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyPartPrice(ActionEvent actionEvent) {
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyPartMax(ActionEvent actionEvent) {
    }


    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyPartMachineID(ActionEvent actionEvent) {

    }


    /**This method sets the text box to Company Name when the OutSourced radio button is selected.
      @param actionEvent Changes text box to Company Name
     */
    public void onModifyOutSourcedPart(ActionEvent actionEvent) {
        modifyMachinePart.setText("Company Name");
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */

    public void onModifyPartInventory(ActionEvent actionEvent) {

    }

    /**This method cancel the modify part page and sends the user back to the main page.
      @param actionEvent cancels modify part and sends user back to main page
     */

    public void onCancelModifyPart(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Main Screen.fxml"));
        Scene MainScene = new Scene(parent, 1300, 1000);
        Stage MainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        MainStage.setScene(MainScene);
        parent.setStyle("-fx-font-family: Times New Roman;");
        MainStage.setTitle("Main Page");
        MainStage.show();


    }
    /**This method saves the changed part metrics and updates the original part, then sends the user back to the main page.
      @param actionEvent Saves changed part metrics and overides original part
     */

    public void onSaveModifyPart(ActionEvent actionEvent) throws IOException {
        try {
            Integer PartID = Integer.valueOf(ModifyPartID.getText());
            String PartName = ModifyPartName.getText();
            Integer PartInventory = Integer.parseInt(ModifyPartInventory.getText());
            Double PartPrice = Double.parseDouble(ModifyPartMin.getText());
            Integer PartMax = Integer.parseInt(ModifyPartMax.getText());
            Integer PartMin = Integer.parseInt(ModifyPartMin.getText());
            String NameID = ModifyPartMachineID.getText();
            if (PartMax < PartMin) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setContentText("Error: Min must be greater than Max");
                newAlert.setHeaderText("Error");
                newAlert.showAndWait();
                return;
            }
            else if (PartInventory < PartMin || PartInventory > PartMax) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setHeaderText("Error");
                newAlert.setContentText("Error: Inventory must be between Min and Max");
                newAlert.showAndWait();
                return;
            }
            if (ModifyOutSourcedPart.isSelected()) {

                OutSourced newOutsourced = new OutSourced(PartToModifyID, PartName, PartPrice, PartInventory, PartMin, PartMax, NameID);
                Inventory.updatePart(PartToModifyID, newOutsourced);
            }
            if (ModifyPartInHouseRadio.isSelected()) {
                Integer CompanyID = Integer.parseInt(NameID);
                InHouse newInhouse = new InHouse(PartToModifyID, PartName, PartPrice, PartInventory, PartMin, PartMax, CompanyID);
                Inventory.updatePart(PartToModifyID, newInhouse);
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
    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */

    public void onModifyPartMin(ActionEvent actionEvent) {
    }
}




