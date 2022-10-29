
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This class creates the Product class which also can have associated Parts
        */
public class Product {
        private  ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        private int ProductId;
        private String ProductName;
        private double ProductPrice;
        private int ProductStock;
        private int ProductMin;
        private int ProductMax;
    /**This is the constuctor method to create an instance of the Product class.  */
        public Product (int ProductId, String ProductName, double ProductPrice, int ProductStock, int ProductMin, int ProductMax) {
            this.ProductId = ProductId;
            this.ProductName = ProductName;
            this.ProductPrice = ProductPrice;
            this.ProductStock = ProductStock;
            this.ProductMin = ProductMin;
            this.ProductMax = ProductMax;
        }

   /** This method returns the ProductID.
  @return Returns the ProductId
 */
     public int getProductId() {
    return ProductId;
}

    /** This method sets the ProductID.
     @param ProductId the ProductId to set
     */
    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    /**This method returns the product name.
      @return the Product name
     */
    public String getProductName() {
        return ProductName;
    }

    /** This method sets the product name.
      @param ProductName the Product Name to set
     */
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    /**This method returns the product price.
      @return the Product price
     */
    public double getProductPrice() {
        return ProductPrice;
    }

    /**This method sets the product price.
     @param ProductPrice the Product price to set
     */
    public void setProductPrice(double ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    /**This method returns the product inventory.
     @return the Product stock aka inventory
     */
    public int getProductStock() {
        return ProductStock;
    }

    /**This method sets the product inventory.
      @param ProductStock the product stock  aka inventory to set
     */

    public void setProductStock(int ProductStock) {
        this.ProductStock = ProductStock;
    }

    /**This method returns the inventory minimum for the product.
      @return the Product Min
     */
    public int getProductMin() {
        return ProductMin;
    }

    /**This method sets the minimum for the product inventory.
      @param ProductMin the product min to set
     */
    public void setProductMin(int ProductMin) {
        this.ProductMin = ProductMin;
    }

    /**This method returns the inventory maximum for the product.
      @return the ProductMax
     */
    public int getProductMax() {
        return ProductMax;
    }

    /** This methods sets the maximum inventory the product.
      @param ProductMax the product max to set
     */
    public void setProductMax(int ProductMax) {
        this.ProductMax = ProductMax;
    }

    /** This method adds a selected part the associated parts list for the product.
     @param part the part to add to the associated Parts
     */
    public void addAssociatedPart (Part part) {
        associatedParts.add(part);
    }

    /** This method returns true when the selected associated part is deleted.
     @return returns true when the selected associcated part is deleted
     */

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
       associatedParts.remove(selectedAssociatedPart);
       return true;
    }

    /**This method returns all parts associated with the product.
      @return returns all associated Parts
     */
    public ObservableList<Part> getAllAssociatedParts () {
        return associatedParts;
    }

}

