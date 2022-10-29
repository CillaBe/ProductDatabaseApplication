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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *This class creates the controller for the Add Product Scene
 */
public class AddProductController implements Initializable {
    /**
     Initializing the FXML View Files
     * */
    @FXML
    private ObservableList<Part> AssociatedParts = FXCollections.observableArrayList();
    @FXML
    private TableView <Part> allCurrentPartsTableAddProductScene;

    @FXML
    private TextField SearchParts;

    @FXML
    private TextField AddProductMin;

    @FXML
    private TextField AddProductID;

    @FXML
    private TextField AddProductName;

    @FXML
    private TextField AddProductInventory;

    @FXML
    private TextField AddProductPrice;

    @FXML
    private TextField AddProductMax;



    @FXML
    private TableColumn allPartNames;

    @FXML
    private TableColumn allPartIDs;



    @FXML
    private TableColumn allPartPrice;

    @FXML
    private TableColumn allPartInventory;

    @FXML
    private Button CancelAddProduct;

    @FXML
    private Button SaveAddProduct;

    @FXML
    private Button RemoveAssociatedPart;

    @FXML
    private Button AddSelectedPartToProduct;

    @FXML
    private TextField AddProductSearch;


    @FXML
    private TableView <Part> AssociatedPartTable;

    @FXML
    private TableColumn AssociatedPartID;

    @FXML
    private TableColumn AssociatedInventoryLevel;

    @FXML
    private TableColumn AssociatedPartName;

    @FXML
    private TableColumn AssociatedPartPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Product Initialized!");
        allCurrentPartsTableAddProductScene.setItems(Inventory.getAllParts());
        allPartIDs.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNames.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

        AssociatedPartTable.setItems(AssociatedParts);
        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }
/**This method was not used in this application, auto generated through SceneBuilder.
  @param actionEvent not used in this application, auto generated through SceneBuilder
 */
    public void OnAddProductID(ActionEvent actionEvent) {

    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddPartName(ActionEvent actionEvent) {
    }

    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddProductInventory(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */

    public void onAddProductMax(ActionEvent actionEvent) {
    }
    /**This method saves the entered product data and then sends user to Main Screen.
     @param actionEvent Saves entered product data and redirects to Main Screen
     */

    public void onSaveAddProduct(ActionEvent actionEvent) throws IOException {
        try {
            String ProductName = AddProductName.getText();
            Integer ProductInventory = Integer.parseInt(AddProductInventory.getText());
            Double ProductPrice = Double.parseDouble(AddProductPrice.getText());
            Integer ProductMax = Integer.parseInt(AddProductMax.getText());
            Integer ProductMin = Integer.parseInt(AddProductMin.getText());

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


            int min = 1;
            int max = 100000;
            int newInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

            Product newProduct= new Product(newInt,ProductName,ProductPrice,ProductInventory,ProductMin,ProductMax);
            for( Part i: AssociatedParts){
                newProduct.addAssociatedPart(i);
            }
            Inventory.getAllProducts().add(newProduct);


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
                    "Price/Cost, Inventory, Min/Max and Machine Id should be numbers" );
            newAlert.showAndWait();
            return;

        }



    }
    

    /**This method removes the selected associated part and prompts user to give permission to do so.
     @param actionEvent Removes selected Associated Product
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
    public void onAddProductPrice(ActionEvent actionEvent) {
    }
  /** This method cancels the add product action, and sends user back to main stage.
   @param actionEvent  cancels add product and sends user back to main stage*/
    public void onCancelAddProduct(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/view/Main Screen.fxml"));
        Scene MainScene = new Scene(parent,1300,1000);
        Stage MainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        MainStage.setScene(MainScene);
        parent.setStyle("-fx-font-family: Times New Roman;");
        MainStage.setTitle("Add Product");
        MainStage.show();


    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */

    public void onAddProductMachineID(ActionEvent actionEvent) {
    }
    /**This method was not used in this application, auto generated through SceneBuilder.
     @param actionEvent not used in this application, auto generated through SceneBuilder
     */
    public void onAddProductSearch(ActionEvent actionEvent) {
    }
    /**This method adds the selected part to the product's associated parts table.
     @param actionEvent adds selected Part to the Associated Parts Table
     */

    public void onAddSelectedPartToProduct(ActionEvent actionEvent) {
        Part selectedPart = allCurrentPartsTableAddProductScene.getSelectionModel().getSelectedItem();
        if (allCurrentPartsTableAddProductScene.getSelectionModel().isEmpty() == false){
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

    /**The method searches the exsisting parts table.
     @param actionEvent searches existing parts table
     */

    public void onSearchParts(ActionEvent actionEvent) {
        String searchedPart = SearchParts.getText();
        ObservableList<Part> results = Inventory.lookupPart(searchedPart);
        try {
            while (results.size() == 0) {
                int PartID = Integer.parseInt(searchedPart);
                results.add(Inventory.lookupPart(PartID));
            }
            allCurrentPartsTableAddProductScene.setItems(results);
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
    public void OnAddProductMin(ActionEvent actionEvent) {
    }


}
