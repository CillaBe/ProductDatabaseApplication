
package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This class creates an Inventory Class where we can store both Parts and Products and perform actions on them
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**This method adds a Part to the ObservableList of Parts.
     @param part the part to add to allParts List
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }
   /**This method add a Product to the ObservableList of Products.
    @param product the product to add to allProducts List
    */
   public static void addProduct(Product product){
       allProducts.add(product);
    }
    /**This method returns the Part associated with the entered PartID.
      @return the partID
     */
    public static Part lookupPart( int PartId) {
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == PartId) {
                    return allParts.get(i);
                }
            }
        }

            return null;
        }

        /**This method returns the Product associated with the entered ProductId.
         @return  id looks up Product ID and returns the product if it is found
         */
        public static Product lookupProduct( int id){
            if (!allProducts.isEmpty()) {
                for (int i = 0; i < allProducts.size(); i++) {
                    if (allProducts.get(i).getProductId() == id) {
                        return allProducts.get(i);
                    }
                }
            }
            return null;

                }
            /** This method looks up a part by it's name and returns it if it is found.
             * @param partName  Looks up part by it's name and returns it if it is found
             */
            public static ObservableList<Part> lookupPart (String partName){
                ObservableList<Part> parts = FXCollections.observableArrayList();
                if (!allParts.isEmpty()) {
                    for (int i = 0; i < allParts.size(); i++) {
                        if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                            parts.add(allParts.get(i));

                        }
                    }
                } else {
                    return parts;
                }
                return parts;
            }

    /**This method looks up a Product by it's name and returns it if it is found.
     @param productName looks up Product by it's name and returns it if it is found
     */
    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> products = FXCollections.observableArrayList();
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getProductName().toLowerCase().contains(productName.toLowerCase())) {
                    products.add(allProducts.get(i));

                }
            }
        } else {
            return products;
        }
        return products;
    }

    /** This method updates the entered part located at the index entered.
     @param selectedPart  Part that gets gets updated
     @param index  Index where the part that needs to be updated is located
     */
    public static void  updatePart( int index, Part selectedPart) {
        allParts.set(index, selectedPart);

    }
    /**This method updates the entered product located at the index entered.
     @param selectedProduct selects the product to be updated
      @param index selects the index of the product to be updated
     */
    public static void updateProduct ( int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    /** This method returns true if the part is found and then deleted, otherwise returns false.
     @return returns true if part is found and deleted, otherwise returns false
     */
    public static boolean deletePart( Part selectedPart) {
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;

        } else{
            return false;
        }
    }
    /** This method returns true if the product is in the table and is deleted, otherwise returns false.
     @return returns true if the product is in the table and is deleted, otherwise returns false
     */
    public static boolean deleteProduct( Product selectedProduct) {
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;

        } else{
            return false;
        }
    }
    /** This method returns the Observable List for all Parts.
      @return theObservableList for the Parts
     */
    public static ObservableList<Part> getAllParts (){
        return allParts;
    }
    /** This method returns the Observable List for all Products.
      @return theObservableList for the Products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }







}
