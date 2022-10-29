
package model;
   /**
    * This class creates an instance of an Outsourced Part Class which extends the Part Class
     */
public class OutSourced extends Part{
    private String companyName;
       /**This is the constuctor method to create an instance of the OutSourced class.  */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }


    /**This method sets the company name for the selected part.
      @param companyName the companyName to set
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
   /**This method returns the company name for the selected part.
    @return the companyName
   */
   public String getCompanyName(){
       return companyName;
   }
}

