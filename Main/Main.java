/**
 * This is the Main Packages containing the Main Class. See Main  Class description for Author and Enhancement Info. Logical Runtime Error is
 * located in MainController Class in the Controller package.
 * */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Product;


import java.util.Objects;
/** Author Priscilla Hennig
 * WGU Student ID 001163207
 * JavaDocs location: file:///Users/Killa_Cilla/JavaDocsWGUProject
 * FUTURE ENHANCEMENT: Future enhancements to this application could be to create a method that tracks how much inventory is depleted in a month  After
 * after collecting this data, the application could generate a user report with this data-- with this data the company could forecast
 * when they will need to place an order for additional inventory, before they are close to the minimum;
 * the application could also benefit from an alert that is generated when your inventory is approaching it's allowed minimum requirements
 *
 * This class creates an Inventory Application that allows the user to enter and manipulate Part and Product Data. The user can also link  Associated Parts
 * to Products.*/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main Screen.fxml"));
        root.setStyle("-fx-font-family: Times New Roman;");
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1300, 1000));
        primaryStage.show();
    }


/**This is the main method. This is the first method that gets called when the application is run and it adds in sample data */
    public static void main(String[] args) {

        InHouse testInHouse = new InHouse(50, "Tire", 40.99, 10, 5, 50, 112);
        OutSourced testOutSourced = new OutSourced(14, "Chain", 98.67, 55, 50, 150, "Atlantic Outfitters");
        Inventory.addPart(testInHouse);
        Inventory.addPart(testOutSourced);
        Product testOne = new Product(90,"Airplane",600.00,4,2,10);
        Product testTwo = new Product(44,"Go Kart",900.45,8, 2,10);
        Inventory.addProduct(testOne);
        Inventory.addProduct(testTwo);


        launch(args);

    }

}

