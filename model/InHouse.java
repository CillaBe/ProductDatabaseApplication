
package model;

/**
 * This class creates an InHouse Class which extends from the Part Class
 */

 public class InHouse extends Part {
    private int machineId;
    /**This is the constuctor method to create an instance of InHouse.  */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** This method gets the machine ID from the InHousePart.
     @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /** This method returns the machineID for the InHouse Part.
     @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }
}
