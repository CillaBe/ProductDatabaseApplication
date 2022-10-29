

package Controller;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Part;
import model.Product;
import model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * This class initializes the Main Controller*/

public class MainController implements Initializable {

    /**
     * Initializing the FXML View Files
     */
    @FXML
    private TextField SearchProducts;

    @FXML
    private Button MainExitButton;
    @FXML
    private TextField SearchParts;


    @FXML
    private TableView<Part> allPartsTable;

    @FXML
    private TableColumn<Part, Integer> allPartIDs;

    @FXML
    private TableColumn<Part, String> allPartNames;

    @FXML
    private Button deletePart;

    @FXML
    private Button ModifyPart;

    @FXML
    private Button AddPart;

    @FXML
    private TableColumn<Part, Integer> allPartInventory;

    @FXML
    private Button deleteProduct;

    @FXML
    private Button ModifyProduct;

    @FXML
    private Button addProduct;

    @FXML
    private TableColumn<Product, Double> allProductPrices;

    @FXML
    private TableColumn<Product, Integer> allProductInventory;

    @FXML
    private TableColumn<Part, Double> allPartPrice;

    @FXML
    private TableView<Product> allProductsTable;

    @FXML
    private TableColumn<Product, String> allProductNames;

    @FXML
    private TableColumn<Product, Integer> allProductIDs;


    /**This method deleted the selected product.
      @param actionEvent deletes selected Product
     */
    public void onDeleteProduct(ActionEvent actionEvent) {

        Product selectedProduct = allProductsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No product selected");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wait!");
            alert.setContentText("Are you sure you want to delete this product?");
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();
                if (associatedParts.size() >= 1) {
                    Alert deleteProduct = new Alert(Alert.AlertType.ERROR);
                    deleteProduct.setHeaderText("Error");
                    deleteProduct.setContentText("Parts are Associated, all parts must be removed from Product before deletion.");
                    deleteProduct.showAndWait();
                } else Inventory.deleteProduct(selectedProduct);
            }
        }
    }


    /**This method sends the user to the Add Product page.
     @param actionEvent Add Product button Switches to Add Product Controller
     */
    public void onAddProduct(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Product clicked!");
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Add Product.fxml"));
        Scene AddProductScene = new Scene(parent, 1300, 1000);
        Stage AddProductStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AddProductStage.setScene(AddProductScene);
        parent.setStyle("-fx-font-family: Times New Roman;");
        AddProductStage.setTitle("Add Product");
        AddProductStage.show();


    }

    /**This method deletes the selected part and prompts user for confirmation.
      @param actionEvent Delete Part Button
     */
    public void onDeletePart(ActionEvent actionEvent) throws IOException {
        System.out.println("On Delete Part clicked");
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wait!");
            alert.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }

        }


    }



    /**This method sends the user to the Add Part page.
      @param actionEvent Add button
     */
    public void onAddPart(ActionEvent actionEvent) throws IOException {
        System.out.println("On Add Part clicked");

        Parent parent = FXMLLoader.load(getClass().getResource("/view/Add Part.fxml"));
        Scene addPartScene = new Scene(parent, 1300, 1000);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        parent.setStyle("-fx-font-family: Times New Roman;");
        addPartStage.setTitle("Add Part");
        addPartStage.show();
    }

    /**This method exists the program.

     * @param actionEvent Exit button
     */

    public void OnMainExitButton(ActionEvent actionEvent) {
        System.out.println("On Main Exit Button Clicked");
        System.exit(0);
    }

    /**This method searches the parts table.
      @param actionEvent search Parts button
     */
    public void OnSearchParts(ActionEvent actionEvent) {
        System.out.println("On Search Parts Clicked");
        String searchedPart = SearchParts.getText();
        ;
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

    /**This method searches the products table.
     @param actionEvent search Product button
     */
    public void OnSearchProducts(ActionEvent actionEvent) {
        System.out.println("On Search Products button Clicked");
        String searchedProduct = SearchProducts.getText();
        ;
        ObservableList<Product> results = Inventory.lookupProduct(searchedProduct);
        try {
            while (results.size() == 0) {
                int ProductID = Integer.parseInt(searchedProduct);
                results.add(Inventory.lookupProduct(ProductID));
            }
            allProductsTable.setItems(results);
        } catch (NumberFormatException e) {
            Alert alertPart = new Alert(Alert.AlertType.ERROR);
            alertPart.setTitle("Error");
            alertPart.setHeaderText("Sorry!");
            alertPart.setContentText("The product you searched for was not found");
            alertPart.showAndWait();
        }
    }

    /**This method selects the product to modify and sends the user to the modify product page with the selected product info.
      @param actionEvent navigates to modify product screen
     */
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {
        System.out.println("On Modify Product clicked");
        if (allProductsTable.getSelectionModel().isEmpty() == false) {


            Stage stage;
            Parent root;
            stage = (Stage) ModifyPart.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Modify Product.fxml"));
            root = loader.load();
            ModifyProductController productToAdd = loader.getController();
            int ProductIndex = allProductsTable.getSelectionModel().getSelectedIndex();
            Product SelectedProduct = allProductsTable.getSelectionModel().getSelectedItem();

            productToAdd.getProductToModify(ProductIndex,SelectedProduct);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modify Product");
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a Product to Modify");
            alert.showAndWait();
        }
    }

    /**
     * Initializes Main Controller.
     * LOGICAL RUNTIME ERROR: I ran into a logical error when I tried to add in sample parts. After changing a few variables and testing, I realized I had my table values
     * populating by using the "setCellFactory" and the program was looking to utilize my inputs in the .setCellValueFactory. I fixed this by changing the
     * "setCellFctory" to "setCellValueFactory"
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized!");

        allPartsTable.setItems(Inventory.getAllParts());

        allPartIDs.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNames.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

        allProductsTable.setItems(Inventory.getAllProducts());

        allProductIDs.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        allProductNames.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        allProductInventory.setCellValueFactory(new PropertyValueFactory<>("ProductStock"));
        allProductPrices.setCellValueFactory(new PropertyValueFactory<>("ProductPrice"));


    }
    /**This method switches to Modify Part page and moves over the selected part.
      @param actionEvent Modify button
     */

    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        System.out.println("On Modify Part clicked");
        if (allPartsTable.getSelectionModel().isEmpty() == false) {


            Stage stage;
            Parent root;
            stage = (Stage) ModifyPart.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Modify Part.fxml"));
            root = loader.load();
            ModifyPartController partToAdd = loader.getController();
            int PartIndex = allPartsTable.getSelectionModel().getSelectedIndex();
            Part SelectedPart = allPartsTable.getSelectionModel().getSelectedItem();

            partToAdd.getPartToModify(PartIndex, SelectedPart);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modify Part");
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a Part to Modify");
            alert.showAndWait();
        }
    }
}
