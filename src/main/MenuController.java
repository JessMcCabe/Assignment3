
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The MenuController class uses the console I/O to interact with the user
 * It creates an instance of the GymApi class and allow the user to navigate (a subset of)
 * the system's features through a series of menus
 * This class also loads the current gym members and trainers
 *
 * @version (15th April 2019)
 */

public class MenuController {


    private Scanner input;
    private GymAPI gym;
    private Trainer trainer;
    private Member member;
    private boolean registerd;
    private boolean loggedIn;
    private String userType;
    private boolean emailCorrect = false;
    boolean matches = true;
    public MenuController() {


        input = new Scanner(System.in);
        gym = new GymAPI();


    }

    public static void main(String args[]) {

        MenuController app = new MenuController();
        app.populateGym();
        app.run();
    }

    //make this the main menu and when we know if it is a member or trainer we then call the appropriate submenu
    private String mainMenu() {
        System.out.println("Welcome To Play Gym Online");
        System.out.println("  1) To Login press L , to register, press R");
        do {
            try {
                System.out.print("==>> ");
                return input.nextLine();
            } catch (Exception e) {
                input.nextLine();  //swallows Scanner bug
                System.out.println("Something went Wrong, please try again");
            }
        } while (true);

    }

    private String memMenu() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("OPTION \t \t \t \t \t DESCRIPTION"
        +"\n V  \t \t \t \t \t  To view your profile press v"
        +"\n U  \t \t \t \t \t  To update your profile press u"
        +"\n P  \t \t \t \t \t  To view your progress press p"
        +"\n L  \t \t \t \t \t  To Logout press l");

        do {
            try {
                System.out.print("==>> ");
                return input.nextLine();
            } catch (Exception e) {
                input.nextLine();  //swallows Scanner bug
                System.out.println("Something went Wrong, please try again");
            }
        } while (true);

    }

    /**
     * run() - This method displays the menu and processes the user's menu
     * choice.  Option "L" lets the user Login, Option "R" lets the user Register,
     * Option "CLOSE" exits the app
     */
    private void run() {
        String option = mainMenu();
        while (!option.equals("close")) {
            switch (option.toUpperCase()) {
                case "L":
                    System.out.println("You have chosen to Login");
                    System.out.println("To Login as a Member please enter M, to Login as a Trainer please enter T");
                    userType = input.nextLine();
                    while(!loggedIn) {
                        switch (userType.toUpperCase()) {
                            case "M":
                                System.out.println("Please enter your email address");
                                String email = input.nextLine();
                                try {

                                     member = authenticateAsMember(email);//authenticate member
                                    if(member.getEmail() == null){
                                        System.out.println("You have not been recognised as a member on our system");
                                        System.out.println("If you are registered, please try re-typing your email address,otherwise please return to the main menu and register");
                                    }
                                    System.out.println("Hello " + member.getName());
                                }
                                catch(Exception e){
                                    System.out.println("You have not been recognised as a member on our system");
                                    System.out.println("If you are registered, please try re-typing your email address,otherwise please return to the main menu and register");
                                }
                                break;
                            case "T":
                                registerTrainer(); //authenticate trainer
                                break;
                            default:
                                break;

                        }
                       // System.out.println("Press enter to continue");
                       // loggedIn=false;
                       // input.nextLine();
                       // run();
                    }//here we have options for the logged in member
                while(loggedIn) {
                    String memOp = memMenu();
                    switch (memOp.toUpperCase()) {
                        case "V":
                            System.out.println("You have chose to view your profile:");
                            System.out.println("Please press enter to show your profile:");
                            input.nextLine();
                            try {

                                System.out.println( member.toString());

                            }
                            catch(Exception e){
                                System.out.println("Something went wrong");

                            }
                            break;
                        case "U":
                            System.out.println("You have chosen to update your profile:");
                            break;
                        case "P":
                            System.out.println("You have chosen to view your progress:");
                            break;
                        case "L":
                            System.out.println("You have logged out");
                            loggedIn = false;
                            mainMenu();
                            break;
                        default:
                            break;

                    }
                    System.out.println("Press enter to continue");
                    loggedIn=false;
                    input.nextLine();
                    memMenu();
                }
                break;
                case "R":
                    System.out.println("You have chosen to Register");
                    System.out.println("To Register as a Member please enter M, to Register as a Trainer please enter T");
                    userType = input.nextLine();
                    while(!registerd) {
                        switch (userType.toUpperCase()) {
                            case "M":
                                registerMember();
                                break;
                            case "T":
                                registerTrainer();
                                break;
                            default:
                                break;

                        }
                        System.out.println("Press enter to continue");
                        registerd=false;
                         input.nextLine();
                        run();
                    }
                    break;
                case "CLOSE":
                    System.out.println("You have chosen to Close the App.");
                    break;
                case "2":
                    System.out.println("persons." + gym.toString());
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }

            //display the main menu again
            System.out.println("");
            option = mainMenu();
        }
        System.out.println("Exiting... bye");
        System.out.println("");
    }

    private void registerMember() {

        registerd = false;
        Person personPart = registerUser();

        System.out.println("Please enter your chosen Package");
        String chosenPackage = input.nextLine();
        System.out.println("Please enter your height");
        float height = input.nextFloat();
        System.out.println("Please enter your start weight");
        float startWeight = input.nextFloat();
        System.out.println("All filled in ");
        input.nextLine();
        registerd= true;
        member = new Member(personPart.getEmail(),personPart.getName(), personPart.getAddress(),
                personPart.getGender(), height, startWeight, chosenPackage);
        gym.addPerson(member);
        //person= newMember;
        //System.out.println("All filled in ");
        try {
            gym.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void registerTrainer(){
            registerd= false;

            Person personPart = registerUser();

            System.out.println("Please enter your speciality");
            String speciality = input.nextLine();
            System.out.println("All filled in ");

            //input.nextLine();
            registerd = true;
            trainer = new Trainer(personPart.getEmail(), personPart.getName(), personPart.getAddress(), personPart.getGender(),speciality);
            gym.addPerson(trainer);

            //person= newMember;
            //System.out.println("All filled in ");
            try {
                gym.save();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    private Person registerUser(){
        String email = "";
        while (matches){
          System.out.println("Please enter your email address");
          email = input.nextLine();
          //check if the email already exists
          if (emailExists(email)) {
              System.out.println("email exists");
          }
      }

        System.out.println("Please enter your name");
        String name = input.nextLine();
        System.out.println("Please enter your address");
        String address = input.nextLine();
        System.out.println("Please enter your gender");
        String gender = input.nextLine();
        Person person = new Person(email,name,address,gender);
        return person;
    }

    private void populateGym() {

  try {
            gym.load();

        }
        catch(Exception e){
            System.out.println("Error Loading members.xml file" + e);
        }
}

 private boolean emailExists(String email){
    matches = false;
    //all persons who exist
     ArrayList<Person> persons = new ArrayList<Person>();
     persons = gym.getPersons();

     //check if the email already exists
     for(int i = 0; i<persons.size(); i ++){

         if(persons.get(i).getEmail().equals(email)){
             matches = true;
             emailCorrect = true;
             return matches;
             //break;
         }
         else {
             matches = false;
             emailCorrect = true;
         }
     }
     return matches;
 }


 private Member authenticateAsMember(String email) {
     loggedIn = false;
     Member member = null;
     if (emailExists(email)) {
         //gym.searchMembersByEmail(email);//authenticate member
         member = new Member(gym.searchMembersByEmail(email).getEmail(),gym.searchMembersByEmail(email).getName(),gym.searchMembersByEmail(email).getAddress(),gym.searchMembersByEmail(email).getGender(),
                 gym.searchMembersByEmail(email).getHeight(),gym.searchMembersByEmail(email).getWeight(),gym.searchMembersByEmail(email).getChosenPackage());
         loggedIn = true;
     }
     return member;
 }
    //This needs to be a hasMap
    /*
    ("Package 1", "Allowed access anytime to gym.\nFree access to all classes.\nAccess to all changing areas including deluxe changing rooms.");

("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes.\nAccess to all changing areas including deluxe changing rooms.");

("Package 3", "Allowed access to gym at off-peak times.
        \n€5 fee for all classes. \nNo access to deluxe changing rooms.");

            ("WIT", "Allowed access to gym during term time.
            \n€4 fee for all classes.  \nNo access to deluxe changing rooms.");*/
}

