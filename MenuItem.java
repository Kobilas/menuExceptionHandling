package mkobilas.homework.menu;

/**
 * The MenuItem class has a constructor to create a MenuItem object along with necessary mutator, accessor, equals, and
 *   clone methods.
 * @author Matthew Kobilas
 *     matthew.kobilas@stonybrook.edu
 *     Stony Brook ID: 111152838
 */
public class MenuItem implements Cloneable{
    
    private double itemPrice;
    private String itemName;
    private String itemDescription;
    /**
     * Constructor for the MenuItem object incorporating all variables
     * @param name
     *     the name of the MenuItem object as a String
     * @param description
     *     the description of the MenuItem object as a String
     * @param price
     *     the price of the MenuItem object as a double
     * @precondition
     *     price > 0
     * @postcondition
     *     constructs a MenuItem object
     * @throws IllegalArgumentException
     *     indicates that the price is nonpositive
     */
    public MenuItem(String name, String description, double price){
	    itemName = name;
	    itemDescription = description;
	    if(price <= 0)
	        throw new IllegalArgumentException("Argument " + price + " is nonpositive.");
	    itemPrice = price;
    }
    /**
     * Empty constructor for the MenuItem object allowing for the user to enter values using mutator methods
     * @postcondition
     *     constructs a MenuItem object with String variables holding null and the price being 1
     */
	public MenuItem(){
	    itemName = null;
	    itemDescription = null;
	    itemPrice = 1;
	}
	/**
	 * Accessor method for the MenuItem object's name
	 * @return
	 *     returns the String for the MenuItem object's name
	 */
	public String getName(){
	    return itemName;
	}
	/**
	 * Accessor method for the MenuItem object's description
	 * @return
	 *     returns the String for the MenuItem object's description
	 */
	public String getDescription(){
	    return itemDescription;
	}
	/**
	 * Accessor method for the MenuItem object's price
	 * @return
	 *     returns the double for the MenuItem object's price
	 */
	public double getPrice(){
	    return itemPrice;
	}
	/**
	 * Mutator method for the MenuItem object's name
	 * @param newName
	 *     the new name for the MenuItem object
	 * @postcondition
	 *     the MenuItem object's name is set to newName
	 */
	public void setName(String newName){
	    itemName = newName;
	}
	/**
	 * Mutator method for the MenuItem object's description
	 * @param newDescription
	 *     the new description for the MenuItem object
	 * @postcondition
	 *     the MenuItem object's description is set to newDescription
	 */
	public void setDescription(String newDescription){
	    itemDescription = newDescription;
	}
	/**
	 * Mutator method for the MenuItem object's price
	 * @param newPrice
	 *     the new price for the MenuItem object
	 * @precondition
	 *     newPrice > 0
	 * @postcondition
	 *     the MenuItem object's price is set to newPrice
	 */
	public void setPrice(double newPrice){
	    if(newPrice <= 0)
	        throw new IllegalArgumentException("Argument " + newPrice + " is nonpositive.");
	    itemPrice = newPrice;
	}
	/**
	 * Checks whether this MenuItem and another object are the same type and have the same values set to their variables
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param other
	 *     the object that is being checked for equality with the MenuItem
	 * @return
	 *     boolean value of whether or not the object is equal to the MenuItem
	 */
	public boolean equals(Object other){
	    if(!(other instanceof MenuItem))
	        return false;
	    //result is the other object typecasted into a MenuItem object
	    MenuItem result = (MenuItem) other;
	    if(!(getName().compareTo(result.getName()) ==  0))
	        return false;
	    if (!(getDescription().compareTo(result.getDescription()) ==  0))
	        return false;
	    if (!(getPrice() ==  result.getPrice()))
	            return false;
	    return true;
	}
	/**
	 * Clones this MenuItem object
	 * @see java.lang.Object#clone()
	 * @return
	 *     returns an object that may be typecast into a MenuItem object that is identical to this MenuItem
	 * @throws CloneNotSupportException
	 *     thrown when the clone method in the class Object has been called to clone an object, but that the object's
	 *       class does not implement the Cloneable interface
	 */
	public Object clone() throws CloneNotSupportedException{
	    return super.clone();
	}
}