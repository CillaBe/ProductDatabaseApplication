





package model;
/**
 * Supplied class Part.java
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /**This is the constuctor method to create an instance of the Part class.  */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**This method returns the part ID.
      @return the id
     */
    public int getId() {
        return id;
    }

  /** This method sets the Part ID for the part.
   @param id  the Part ID to set
   */
    public void setId(int id) {
        this.id = id;
    }

    /**This method sets the part ID
     @return the name
     */
    public String getName() {
        return name;
    }

    /**This method sets the name of the part.
      @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This method returns the part price.
      @return the price
     */
    public double getPrice() {
        return price;
    }

    /**This method sets the part price.
      @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**This method returns the inventory of the part.
     @return the stock aka inventory
     */
    public int getStock() {
        return stock;
    }
/**This method sets the inventory of the part
@param stock  the inventory aka stock to set
 */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This method returns the minimum inventory level of the part.
      @return the min
     */
    public int getMin() {
        return min;
    }

    /** This method set the minimum inventory of the part.
     @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** This method returns the maximum inventory of the part.
     @return the max
     */
    public int getMax() {
        return max;
    }

    /** This method sets the maximum inventory for the part.

      @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}