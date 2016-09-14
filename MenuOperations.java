package mkobilas.homework.menu;
import java.util.Scanner;

/**
 * The main class in the Menu Homework Project which creates the menu which the user will interact with in order to
 *   manipulate the mainMenu, orderMenu, and the many MenuItem objects that are used throughout the project. It features
 *   a way to input selections in order to fulfill many different operations that were implemented in the other classes
 *   throughout the project.
 * @author Matthew Kobilas
 *     matthew.kobilas@stonybrook.edu
 *     Stony Brook ID: 111152838
 */
public class MenuOperations{
    
    /**
     * Contains the main while loop which loops and asks the user for their selection in order to manipulate the many
     *   objects throughout the project. Has try-catch blocks to handle exceptions that arise throughout the program.
     * @param args
     *     not implemented in the method except to declare the method as a main method
     * @precondition
     *     keyboard inputs for Strings are values that can be incorporated into Strings; keyboard inputs for doubles are
     *       values that can be considered double values
     * @precondition
     *     double values > 0
     * @precondition
     *     1 <= position values; position values <= size()
     * @precondition
     *     keyboard selections for the menu options are selected as listed and are not unlisted values or inputs longer
     *       than one character long
     * @postcondition
     *     selections in the menu will change the mainMenu and orderMenu MenuItems in the ways listed in the menu
     * @postconditions
     *     pressing q in the menu will shut the program down gracefully
     * @throws FullListException
     *     used to indicate to the user if the mainMenu or orderMenu is full of MenuItem objects and no other MenuItem
     *       objects can be added to it
     * @throws InputMismatchException
     *     used to indicate to the user if the value entered is not of the correct type
     * @throws IllegalArgumentException
     *     used to indicate if the value entered is not within valid bounds of the method being used
     * @throws IllegalArgumentException
     *     used to indicate if the value entered was nonpositive in the case of declaring a price
     * @throws IllegalArgumentException
     *     used to indicate if the value entered was not found within the Menu's array of MenuItem objects
     * @throws NullPointerException
     *     used to indicate if the value entered was not found within the Menu's array of MenuItem objects
     */
    public static void main(String[] args) throws FullListException{
        //keyboard is the source of input throughout the class
        Scanner keyboard = new Scanner(System.in);
        //ranAlready determines whether or not an erroneous line must be consumed
        boolean ranAlready = false;
        //invalidResponse determines whether or not the keyboard input entered was invalid
        boolean invalidResponse = false;
        //answer is the input coming from the keyboard in the form of one character
        char answer;
        //tempPrice is used to temporarily hold the input from the keyboard for the double for price of the MenuItem
        double tempPrice;
        //tempPosition is used to temporarily hold the input from the keyboard for the int for position in the
          //mainMenu array
        int tempPosition;
        //orderCount is used to determine the number of MenuItems in the orderMenu
        int orderCount = 0;
        Menu mainMenu = new Menu();
        Menu orderMenu = new Menu();
        //unformattedAnswer is the raw input from the keyboard in String form
        String unformattedAnswer;
        //tempName is used to temporarily hold the input from the keyboard for the String for the name of the MenuItem
        String tempName;
        //tempDescription is used to temporarily hold the input from the keyboard for the String for the description
          //of the MenuItem
        String tempDescription;
        
        //The main while loop for the operation of the class, entering 'q' or an invalidResponse causes the loop to
          //restart from the beginning
        while(true){
            //if-else block used to print out specific things depending on whether or not the user entered a valid
              //response or not
            if(invalidResponse){
                System.out.print("\nNo such operation. Select an operation: ");
                invalidResponse = false;
            }
            else{
                //Prints the basic menu so that the user knows about the options and what keys to press in order to
                  //select them
                printOptions();
                System.out.print("\nSelect an operation: ");
            }
            //if statement used to take keyboard input an additional time to take care of any extra lines being taken
              //as input
            if(ranAlready){
                keyboard.nextLine();
                ranAlready = false;
            }
            //keyboard input statement used to check if the input was a String or a character
              //If it was a String, then the loop is restarted with the invalidResponse switched to true, otherwise,
              //the rest of the loop is ran as normal
            unformattedAnswer = keyboard.nextLine();
            System.out.println("");
            if(unformattedAnswer.length() != 1){
                invalidResponse = true;
                continue;
            }
            answer = unformattedAnswer.toLowerCase().charAt(0);
            //if statement used to determine whether or not the answer entered was a 'q' in case the user wants the
              //program to exit gracefully
            if(answer ==  'q')
                break;
            //Main switch-case block used to determine what the user wants the program to do
            switch(answer){
                //case statement for if the user wants to add a MenuItem object to the Menu object's array
                case 'a':
                    ranAlready = true;    //Consumes additional lines
                    System.out.print("Enter the name: ");
                    tempName = keyboard.nextLine();
                    System.out.print("\nEnter the description: ");
                    tempDescription = keyboard.nextLine();
                    System.out.print("\nEnter the price: ");
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        tempPrice = keyboard.nextDouble();
                    }
                    catch (java.util.InputMismatchException err){
                        System.out.println("\nArgument entered is not of the correct type.");
                        continue;
                    }
                    System.out.print("\nEnter the position: ");
                    try{
                        tempPosition = keyboard.nextInt();
                    }
                    catch (java.util.InputMismatchException err){
                        System.out.println("\nArgument entered is not of the correct type.");
                        continue;
                    }
                    try{
                        mainMenu.addItem((new MenuItem()), tempPosition);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    catch (FullListException err){
                        System.out.println("\nThere is no more room inside of the Menu to store the new " + 
                          "MenuItem object.");
                        continue;
                    }
                    try{
                        mainMenu.getItem(tempPosition).setPrice(tempPrice);
                        mainMenu.getItem(tempPosition).setName(tempName);
                        mainMenu.getItem(tempPosition).setDescription(tempDescription);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPrice + " is nonpositive.");
                        mainMenu.removeItem(tempPosition);
                        continue;
                    }
                    //Confirmation statement for the MenuItem object being added to the Menu object's array, then
                      //restarting the loop
                    System.out.println(String.format("%s%3.2f%s", "\nAdded \"" + tempName + ": " + tempDescription +
                      "\" for $", tempPrice, " at position " + tempPosition));
                    continue;
                //case statement which prints the information for the MenuItem object at the designated position
                case 'g':
                    ranAlready = true;    //Consumes additional lines
                    System.out.print("Enter the position: ");
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        tempPosition = keyboard.nextInt();
                    }
                    catch (java.util.InputMismatchException err){
                        System.out.println("\nArgument entered is not of the correct type.");
                        continue;
                    }
                    try{
                        mainMenu.getItem(tempPosition);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    //Prints information regarding the MenuItem object at the position and then restarts the loop
                    System.out.println("\n" + 
                      String.format("%-5s%-25s%-75s%-6s", "#", "Name", "Description", "Price") + 
                      "\n--------------------------------------------------------------" + 
                      "-------------------------------------------------");
                    System.out.println((String.format("%-5d%-25s%-75s%-1s%-3.2f", tempPosition,
                      mainMenu.getItem(tempPosition).getName(), mainMenu.getItem(tempPosition).getDescription(), "$",
                      mainMenu.getItem(tempPosition).getPrice())));
                    continue;
                //case statement which removes a MenuItem object from the Menu object's array of MenuItem objects
                case 'r':
                    System.out.print("Enter the name: ");
                    tempName = keyboard.nextLine();
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        tempPosition = mainMenu.getPosition(tempName);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempName + " does not exist within the menu.");
                        continue;
                    }
                    catch (NullPointerException err){
                        System.out.println("\nArgument" + tempName + " does not exist within the menu.");
                        continue;
                    }
                    try{
                        mainMenu.removeItem(tempPosition);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    //Confirmation statement regarding the removal of a MenuItem object, then restarts the loop
                    System.out.println("Removed: \"" + tempName + "\"");
                    continue;
                //case statement which prints all the MenuItem objects in the mainMenu Menu object into a neatly
                  //formatted table, then restarts the loop
                case 'p':
                    mainMenu.printAllItems();
                    continue;
                //case statement which prints the number of MenuItem objects in the mainMenu Menu object, then
                  //restarts the loop
                case 's':
                    System.out.println("There are " + mainMenu.size() + " items in the menu.");
                    continue;
                //case statement which prompts the user to enter a new description for a MenuItem object in order to
                  //reset the description to something different
                case 'd':
                    System.out.print("Enter the name: ");
                    tempName = keyboard.nextLine();
                    System.out.print("\nEnter the new description: ");
                    tempDescription = keyboard.nextLine();
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        mainMenu.getItemByName(tempName).setDescription(tempDescription);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempName + " does not exist within the menu.");
                        continue;
                    }
                    //Prints the confirmation telling the user a new description was set, then restarts the loop
                    System.out.println("\nNew description set.");
                    continue;
                //case statement which prompts the user to enter a new price for a MenuItem object in order to
                  //reset the price to something different
                case 'c':
                    ranAlready = true;    //Consumes additional lines
                    System.out.print("Enter the name: ");
                    tempName = keyboard.nextLine();
                    System.out.print("\nEnter the new price: ");
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        tempPrice = keyboard.nextDouble();
                    }
                    catch (java.util.InputMismatchException err){
                        System.out.println("\nArgument entered is not of the correct type.");
                        continue;
                    }
                    try{
                        mainMenu.getItemByName(tempName);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempName + " does not exist within the menu.");
                        continue;
                    }
                    try{
                        mainMenu.getItemByName(tempName).setPrice(tempPrice);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println(String.format("%s%3.2f%s", "\nArgument ", tempPrice, " is nonpositive."));
                        continue;
                    }
                    //Prints the confirmation telling the user a new price was set, then restarts the loop
                    System.out.println("\nNew price set.");
                    continue;
                //case statement which adds a MenuItem object from the mainMenu, to the orderMenu
                case 'o':
                    ranAlready = true;    //Consumes additional lines
                    System.out.print("Enter the position: ");
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        tempPosition = keyboard.nextInt();
                    }
                    catch (java.util.InputMismatchException err){
                        System.out.println("\nArgument entered is not of the correct type.");
                        continue;
                    }
                    try{
                        mainMenu.getItem(tempPosition);
                        tempName = mainMenu.getItem(tempPosition).getName();
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    try{
                        orderMenu.addItem(mainMenu.getItem(tempPosition), orderCount+1);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    catch (FullListException err){
                        System.out.println("\nThere is no more room inside of the Order to store the new " + 
                          "MenuItem object.");
                        continue;
                    }
                    //Gives a confirmation regarding the addition of a MenuItem object to the orderMenu, increments the
                      //orderCount counter by one to show the number of things in the orderMenu's object array, then
                      //restarts the loop
                    System.out.println("\nAdded \"" + tempName + "\" to order.");
                    orderCount++ ;
                    continue;
                //case statement which removes a MenuItem object from the orderMenu's array of MenuItems
                case 'i':
                    ranAlready = true;    //Consumes additional lines
                    System.out.print("Enter the position: ");
                    //Exception-handling which tests certain lines of code for missed preconditions, and if they were
                      //missed, the loop restarts and throws a controlled error statement telling the user what went
                      //wrong
                    try{
                        tempPosition = keyboard.nextInt();
                    }
                    catch (java.util.InputMismatchException err){
                        System.out.println("\nArgument entered is not of the correct type.");
                        continue;
                    }
                    try{
                        tempName = orderMenu.getItem(tempPosition).getName();
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    try{
                        orderMenu.removeItem(tempPosition);
                    }
                    catch (IllegalArgumentException err){
                        System.out.println("\nArgument " + tempPosition + " is not within the valid range.");
                        continue;
                    }
                    //Gives a confirmation regarding the removal of a MenuItem object from the orderMenu, decrements the
                      //orderCount counter by one to show the number of things in the orderMenu's object array, then
                      //restarts the loop
                    System.out.println("\nRemoved \"" + tempName + "\" from order.");
                    orderCount--;
                    continue;
                //case statement which prints all the MenuItem objects from the orderMenu into a neatly formatted table,
                  //then restarts the loop
                case 'v':
                    orderMenu.printAllItems();
                    continue;
                //default case which determines that the user entered an invalidResponse, triggering the invalidResponse
                  //switch to change to true, then restarts the loop
                default:
                    invalidResponse = true;
                    continue;
            }
        }
        //Begins the process of shutting down in the case that the user enters a 'q', by closing the keyboard, sending
          //a statement which says that the program is shutting down, the shutting down the actual application
          //gracefully
        keyboard.close();
        System.out.println("\nSHUTTING DOWN\n");
        System.exit(0);
    }
    /**
     * Method used to print the options the user has the potential to pick in the main while loop above
     * @postcondition
     *     prints out a neatly formatted table giving the user a list of possible selections they can make while using
     *       the program
     */
    public static void printOptions(){
        System.out.println("\nMain Menu\n\nA) Add Item\nG) Get Item\nR) Remove Item\nP) Print All Items\nS) Size" + 
          "\nD) Update Description\nC) Update Price\nO) Add to Order\nI) Remove from Order\nV) View Order\nQ) Quit\n");
    }
}