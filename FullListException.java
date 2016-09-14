package mkobilas.homework.menu;

/**
 * Class used to throw FullList exceptions for use throughout the program whenever a Menu object's array is full and
 *   cannot store anymore MenuItems
 * @author Matthew Kobilas
 *     matthew.kobilas@stonybrook.edu
 *     Stony Brook ID: 111152838
 */
public class FullListException extends Exception {
    
    private static final long serialVersionUID = -2862290382094834685L;
    /**
     * Empty constructor for throwing a FullListException without a message or cause
     * @postcondition
     *     throws an exception for a FullList
     */
    public FullListException(){
    }
    /**
     * Constructor with a String message stating what may have caused the problem and detailing how it can be fixed
     * @param message
     *     a detailed String message stating why the exception was thrown and how it can be avoided
     * @postcondition
     *     throws an exception for a FullList with a message that gets printed in the printStackTrace
     */
    public FullListException(String message){
        super(message);
    }
    /**
     * Constructor with a String message stating what may have caused the problem and detailing how it can be fixed
     *   along with an actual Throwable cause for the exception being thrown
     * @param message
     *     a detailed String message stating why the exception was thrown and how it can be avoided
     * @param cause
     *     a Throwable cause for the exception being thrown
     * @postcondition
     *     throws an exception for a FullList with a message that gets printed in the printStackTrace along with a cause
     *       in the form of a Throwable object 
     */
    public FullListException(String message, Throwable cause){
        super(message, cause);
    }
}
