package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.jfr.Frequency;
import model.*;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * This class creates the Controller for the Moddify Part scene
        */
public class ModifyProductController implements Initializable {
    /**
     * Initializing the FXML View Files
     */

    @FXML
    private TableColumn AssociatedPartID;

    @FXML
    private TableView <Part> AssociatedPartTable;

    @FXML
    private TableColumn AssociatedInventoryLevel;

    @FXML
    private TableColumn AssociatedPartPrice;

    @FXML
    private TextField ModifyProductID;

    @FXML
    private TextField ModifyProductName;

    @FXML
    private TextField ModifyProductPrice;

    @FXML
    private TextField ModifyProductMax;

    @FXML
    private TableView <Part>allPartsTable;

    @FXML
    private TableColumn allPartIDs;


    @FXML
    private TableColumn allPartInventory;

    @FXML
    private TextField ModifyProductInventory;

    @FXML
    private TableColumn allPartNames;

    @FXML
    private TableColumn AssociatedPartName;

    @FXML
    private TableColumn allPartPrice;

    @FXML
    private Button RemoveAssociatedPart;

    @FXML
    private Button AddModifyPartToProduct;

    @FXML
    private TextField SearchParts;

    @FXML
    private TextField ModifyProductMin;

    @FXML
    private Button SaveAddProduct;

    @FXML
    private Button CancelAddProduct;
    @FXML
    int ProductToModifyIndex;
    @FXML
    private ObservableList<Part> AssociatedParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Modify Product Initialized!");
        allPartsTable.setItems(Inventory.getAllParts());
        allPartIDs.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNames.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyProductID(ActionEvent actionEvent) {
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyProductInventory(ActionEvent actionEvent) {
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyProductName(ActionEvent actionEvent) {
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
      @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyProductMax(ActionEvent actionEvent) {
    }

    /**This method removes the associated part from the product, prompts for confirmation.
      @param actionEvent Removes Associated part, prompts with confirmation
     */
    public void onRemoveAssociatedPart(ActionEvent actionEvent) {

        System.out.println("Remove Part Clicked");
        Part selectedPart = AssociatedPartTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wait!");
            alert.setContentText("Are you sure you want to remove this part?");
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                AssociatedParts.remove(selectedPart);
            }

        }
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onModifyProductMin(ActionEvent actionEvent) {
    }

    /** This method saves the modified product metrics and updated the original part, then sends the user back to the main page.
      @param actionEvent Saves Modified Product and sends you back to main paige
     */
    public void onSaveAddProduct(ActionEvent actionEvent) throws IOException {
        try {
            Integer ProductID = Integer.valueOf(ModifyProductID.getText());
            String ProductName = ModifyProductName.getText();
            Integer ProductInventory = Integer.parseInt(ModifyProductInventory.getText());
            Double ProductPrice = Double.parseDouble(ModifyProductPrice.getText());
            Integer ProductMax = Integer.parseInt(ModifyProductMax.getText());
            Integer ProductMin = Integer.parseInt(ModifyProductMin.getText());

            if (ProductMax < ProductMin) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setContentText("Error: Min must be greater than Max");
                newAlert.setHeaderText("Error");
                newAlert.showAndWait();
                return;
            } else if (ProductInventory < ProductMin || ProductInventory > ProductMax) {
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setHeaderText("Error");
                newAlert.setContentText("Error: Inventory must be between Min and Max");
                newAlert.showAndWait();
                return;
            }

            Product ProductToModify = new Product(ProductID, ProductName, ProductPrice, ProductInventory, ProductMin, ProductMax);
            for (Part i : AssociatedParts) {
                ProductToModify.addAssociatedPart(i);
            }
            Inventory.updateProduct(ProductToModifyIndex, ProductToModify);


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

    /**This method moves the selected part to the associated parts table.
      @param actionEvent moves selected part to associated parts table
     */
    public void onAddModifyPartToProduct(ActionEvent actionEvent) {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (allPartsTable.getSelectionModel().isEmpty() == false){
            AssociatedParts.add(selectedPart);
            AssociatedPartTable.setItems(AssociatedParts);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to add");
            alert.showAndWait();
            return;

        }
    }

    /**This method searches the currnt parts table.
      @param actionEvent Searches current parts
     */
    public void onSearchParts(ActionEvent actionEvent) {
        String searchedPart = SearchParts.getText();
        ObservableList<Part> results = Inventory.lookupPart(searchedPart);
        try {
            while (results.size() == 0) {
                int PartID = Integer.parseInt(searchedPart);
                results.add(Inventory.lookupPart(PartID));
            }
            allPartsTable.setItems(results);
        } catch (NumberFormatException e) {
            Alert alertPart = new Alert(Alert.AlertType.ERROR);
            alertPart.setTitle("Error");
            alertPart.setHeaderText("Sorry!");
            alertPart.setContentText("The part you searched for was not found");
            alertPart.showAndWait();
        }
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */


    public void onModifyProductPrice(ActionEvent actionEvent) {
    }

    /**This method cancels the modify product page and sends the user back to the main page.
     @param actionEvent Cancels add part and sends user back to main screen
     */
    public void onCancelAddProduct(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Main Screen.fxml"));
        Scene MainScene = new Scene(parent, 1300, 1000);
        Stage MainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        MainStage.setScene(MainScene);
        parent.setStyle("-fx-font-family: Times New Roman;");
        MainStage.setTitle("Add Product");
        MainStage.show();
    }
  /**This method creates a methos to pull seleted prodcut and that prodcut's index from the main page to the modify products page.
   * @param ProductInt this is the product index
   * @param selectedProduct  this is the product to modify*/
    public void getProductToModify(int ProductInt, Product selectedProduct) {
        ProductToModifyIndex = ProductInt;


        ModifyProductID.setText(String.valueOf(selectedProduct.getProductId()));
        ModifyProductName.setText(selectedProduct.getProductName());
        ModifyProductInventory.setText(String.valueOf(selectedProduct.getProductStock()));
        ModifyProductPrice.setText(String.valueOf(selectedProduct.getProductPrice()));
        ModifyProductMax.setText(String.valueOf(selectedProduct.getProductMax()));
        ModifyProductMin.setText(String.valueOf(selectedProduct.getProductMin()));

        AssociatedPartTable.setItems(selectedProduct.getAllAssociatedParts());
        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssociatedInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }
}