package mkobilas.homework.menu;
import java.lang.StringBuilder;

/**
 * The Menu class has a constructor method to create a Menu object which instantiates and creates an array of MenuItem
 *   objects as well as methods to manipulate and read information regarding the Menu object
 *  @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      Stony Brook ID: 111152838
 */
public class Menu implements Cloneable{
    
    //sizeOfMenu is the number of MenuItem objects in this Menu object's array
    private int sizeOfMenu = 0;
    //MAX_ITEMS is a final variable showing the maximum number of MenuItem objects that can be put into this Menu
      //object's array
    private final int MAX_ITEMS = 50;
    //MAX_MENU_WIDTH is a final variable showing the maximum width of the formatted menu that can be printed to show
      //all the MenuItem objects
    private final int MAX_MENU_WIDTH = 111;
    //MAX_MENU_SIZE is a final variable containing the maximum possible number of characters that may be contained in
      //the StringBuilder used in the toString() and printAllItems() methods
    private final int MAX_MENU_SIZE = (MAX_ITEMS*MAX_MENU_WIDTH);
    private MenuItem[] menu = new MenuItem[MAX_ITEMS];
    /**
     * Empty constructor to be used for creation of Menu objects.
     * @postcondition
     *     creates a Menu object
     */
    public Menu(){
    }
    /**
     * Clones this Menu object
     * @see java.lang.Object#clone()
     * @return
     *     an object that may be typecast into the Menu object type with MenuItem objects in the same order in the array
     *       and with the same information as this Menu object
     * @throws CloneNotSupportException
     *     thrown when the clone method in the class Object has been called to clone an object, but that the object's
     *       class does not implement the Cloneable interface
     */
    public Object clone() throws CloneNotSupportedException{
        //result is the returned menu object as a result of cloning the menu object
        Menu result = (Menu) super.clone();
        result.setSize(size());
        return result;
    }
    /**
     * Checks whether this Menu object is the same type as another object and has the same MenuItems in the same order
     *   in the array
     * @see java.lang.Object#equals(java.lang.Object)
     * @param other
     *     the object that this Menu is being compared to
     * @return
     *     a boolean that shows whether or not this Menu and another object are equal
     */
    public boolean equals(Object other){
        if(!(other instanceof Menu))
            return false;
        //result is the other object typecasted into a Menu object
        Menu result = (Menu) other;
        if(!(size() ==  result.size()))
            return false;
        for(int i = 0; i < result.size(); i++ )
            if(!(menu[i].equals(result.getItem(i+1))))
                return false;
        return true;
    }
    /**
     * Gives the number of MenuItem objects in this Menu object
     * @return
     *     an integer telling how many MenuItems are in this Menu
     */
    public int size(){
        return sizeOfMenu;
    }
    /**
     * Sets the variable telling how many MenuItem objects are in this Menu object to a different integer
     * @param newSize
     *     the new size to set the Menu to
     * @precondition
     *     1 <= newSize; newSize >= MAX_ITEMS
     * @postcondition
     *     the size of this Menu is set to newSize
     * @throws IllegalArgumentException
     *     notifies the user if newSize is not within the valid bounds listed in the preconditions
     */
    public void setSize(int newSize){
        if(!(1 <= newSize) && (newSize >= MAX_ITEMS))
            throw new IllegalArgumentException("New size " + newSize + " is not within valid range.");
        sizeOfMenu = newSize;
    }
    /**
     * Adds a MenuItem object to this Menu in the directed position, moving other MenuItem objects out of the way
     * @param item
     *     the MenuItem object being added to the Menu object
     * @param position
     *     the integer position in the array in which the MenuItem objected will be placed
     * @precondition
     *     1 <= position; position <= size()+1
     * @precondition
     *     size() < MAX_ITEMS
     * @precondition
     *     item.getPrice() > 0
     * @postcondition
     *     the MenuItem object is added to the array in the Menu object at integer position and the size of the Menu is
     *       incremented
     * @throws IllegalArgumentException
     *     notifies the user if the position is not within the valid bounds listed in the preconditions
     * @throws FullListException
     *     notifies the user if this Menu object has no more empty spots in the array to store additional MenuItem
     *       objects
     * @throws IllegalArgumentException
     *     notifies the user if the price of the MenuItem being added is nonpositive
     */
    public void addItem(MenuItem item, int position) throws FullListException{
        if(!((1 <= position) && (position <= size()+1)))
          throw new IllegalArgumentException("Argument " + position + " is not within the valid range.");
        if(size() >= MAX_ITEMS)
          throw new FullListException("There is no more room inside of the Menu to store the new " + 
            "MenuItem object.");
        if(item.getPrice() <= 0)
            throw new IllegalArgumentException("Argument " + item.getPrice() + " is nonpositive.");
        if(position > (size()))
            menu[position-1] = item;
        else{
            //i is the counter for the main loop that moves MenuItems forward if a MenuItem is inserted behind them
            int i;
            //temp1 and temp2 are used to move MenuItems around in order to eventually move them forward in the array
            MenuItem temp1 = menu[position-1];
            MenuItem temp2 = null;
            menu[position-1] = item;
            for(i = 0; i <= ((size()) - position); i++ ){
                if((i % 2) ==  0){
                    temp2 = menu[position+i];
                    menu[position+i] = temp1;
                }
                else{
                    temp1 = menu[position+i];
                    menu[position+i] = temp2;
                }
            }
            if((i % 2) ==  0)
                menu[position+i] = temp1;
            else
                menu[position+i] = temp2;
        }
        sizeOfMenu++ ;
    }
    /**
     * Removes an MenuItem object from this Menu object based on an integer position for the array in this Menu object
     * @param position
     *     integer position in the Menu object's array from which to delete the MenuItem object
     * @precondition
     *     1 <= position; position <= size()
     * @postcondition
     *     the MenuItem at the integer position in the array in the Menu object is deleted and the size of the Menu
     *       object is decremented
     * @throws IllegalArgumentException
     *     used to indicate to the user that the position is not within the valid bounds shown in the preconditions
     */
    public void removeItem(int position){
        if(!((1 <= position) && (position <= (size()))))
            throw new IllegalArgumentException("Argument " + position + " is not within the valid range.");
        if(position ==  (size()))
            menu[position] = null;
        else{
            for(int i = 0; i < ((size()) - position); i++ )
                menu[position-1+i] = menu[position+i];
            menu[size()] = null;
        }
        sizeOfMenu--;
    }
    /**
     * Accessor method for the MenuItem object found at the position in the array in the Menu object
     * @param position
     *     integer position that the MenuItem object can be found at in the array in the Menu object
     * @precondition
     *     1 <= position; position <= size()
     * @return
     *     returns the MenuItem object at the position in the array
     * @throws IllegalArgumentException
     *     used to indicate to the user if the position is not within the valid bounds as shown in the preconditions
     */
    public MenuItem getItem(int position){
        if(!((1 <= position) && (position <= (size()))))
            throw new IllegalArgumentException("Argument " + position + " is not within the valid range.");
        return menu[position-1];
    }
    /**
     * Searches the Menu object for a MenuItem object that has the same name and returns the MenuItem
     * @param name
     *     the String name to search the Menu object's MenuItem array for
     * @return
     *     returns the first MenuItem with the same String name
     * @throws IllegalArgumentException
     *     used to indicate to the user that the indicated name has not been found in the Menu object
     */
    public MenuItem getItemByName(String name){
        for(int i = 0; i < size(); i++ )
            if(menu[i].getName().compareTo(name) ==  0)
                return menu[i];
        throw new IllegalArgumentException("Argument " + name + " does not exist within the menu.");
    }
    /**
     * Used to find the position of the Menu
     * @param name
     *     the String name to search the Menu object's MenuItems for
     * @return
     *     returns the position in the array in which the String name was found
     * @throws IllegalArgumentException
     *     indicates to the user that the MenuItem with the given name was not found within the Menu object's array
     */
    public int getPosition(String name){
        try{
            for(int i = 0; i <= size(); i++ )
                if(menu[i].getName().compareTo(name) ==  0)
                    return (i+1);
            throw new IllegalArgumentException("Argument " + name + " does not exist within the menu.");
        }
        catch (NullPointerException err){
            throw new IllegalArgumentException("Argument " + name + " does not exist within the menu.");
        }
    }
    /**
     * Prints all the MenuItem objects in this Menu object into a table
     * @postcondition
     *     prints all the MenuItem objects
     */
    public void printAllItems(){
        System.out.print(toString());
    }
    /**
     * Determines the String with the table of all the MenuItem objects in this Menu object and returns it
     * @returns
     *     returns a string with a formatted table of all the MenuItem objects in the Menu object's array
     */
    public String toString(){
        //result is a StringBuilder that adds on information and is eventually returned as a String
        StringBuilder result = new StringBuilder(MAX_MENU_SIZE);
        result.append("MENU:\n\n");
        result.append(String.format("%-5s%-25s%-75s%-6s", "#", "Name", "Description", "Price"));
        result.append("\n--------------------------------------------------------------");
        result.append("-------------------------------------------------\n");
        //i is a counter that allows the StringBuilder to append MenuItems in order to allow for a large String to be
          //returned
        for(int i = 0; i < size(); i++ ){
            result.append(String.format("%-5d%-25s%-75s%-1s%-3.2f", (i+1), menu[i].getName(), menu[i].getDescription(),
              "$", menu[i].getPrice()));
            result.append("\n");
        }
        return result.toString();
    }
}
