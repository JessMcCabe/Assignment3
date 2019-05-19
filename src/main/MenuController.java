
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;

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
    private boolean registered;
    private boolean memLoggedIn = false;
    private boolean trainLoggedIn = false;
    private boolean userLoggedIn = false;
    private String userType;
    private boolean emailCorrect = false;
    private static String email;
    boolean matches = true;


    public MenuController() {


        input = new Scanner(System.in);
        gym = new GymAPI();

    }

    public static void main(String args[]) {
        //This needs to be a hasMap

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
                + "\n V  \t \t \t \t \t  To view your profile press v"
                + "\n U  \t \t \t \t \t  To update your profile press u"
                + "\n P  \t \t \t \t \t  To view your progress press p"
                + "\n L  \t \t \t \t \t  To Logout press l");

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

    private String trainMenu() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("OPTION \t \t \t \t \t DESCRIPTION"
                + "\n A  \t \t \t \t \t  To add a new member press A"
                + "\n L  \t \t \t \t \t  To list all members press L"
                + "\n S  \t \t \t \t \t  To search for a member press S"
                + "\n O  \t \t \t \t \t  To list options for assessments press O");

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

    private String assessMenu() {
        System.out.println("Please choose from one of the following options:");
        System.out.println("OPTION \t \t \t \t \t DESCRIPTION"
                + "\n A  \t \t \t \t \t  To add an assessment for a member press A"
                + "\n C  \t \t \t \t \t  To comment on an assessment press C"
        );

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
        HashMap<String, String> memberPackages = new HashMap<>();

        memberPackages.put("Package 1", "Allowed access anytime to gym.\n Free access to all classes.\nAccess to all changing areas including deluxe changing rooms.");
        memberPackages.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes.\nAccess to all changing areas including deluxe changing rooms.");
        memberPackages.put("Package 3", "Allowed access to gym at off-peak times. \n €5 fee for all classes. \n No access to deluxe changing rooms.");
        memberPackages.put("WIT", "Allowed access to gym during term time. \n €4 fee for all classes.  \n No access to deluxe changing rooms.");

        String option = mainMenu();
        while (!option.equals("close")) {
            switch (option.toUpperCase()) {
                case "L":
                    System.out.println("You have chosen to Login");
                    System.out.println("To Login as a Member please enter M, to Login as a Trainer please enter T");
                    userType = input.nextLine();

                    while (!userLoggedIn) {
                        //while (!memLoggedIn) {
                        switch (userType.toUpperCase()) {
                            case "M":
                                System.out.println("Please enter your email address");
                                 email = input.nextLine();
                                try {

                                    member = authenticateAsMember(email);//authenticate member
                                    if (member.getEmail() == null) {
                                        System.out.println("You have not been recognised as a member on our system");
                                        System.out.println("If you are registered, please try re-typing your email address,otherwise please return to the main menu and register");
                                    }
                                    System.out.println("Hello " + member.getName());
                                    System.out.println("Testing printing member assessments " +  member.getAssessments().keySet());
                                } catch (Exception e) {
                                    System.out.println("You have not been recognised as a member on our system");
                                    System.out.println("If you are registered, please try re-typing your email address,otherwise please return to the main menu and register");
                                }

                                break;
                            case "T":
                                System.out.println("Please enter your email address");
                                email = input.nextLine();
                                try {

                                    trainer = authenticateAsTrainer(email);//authenticate member
                                    if (trainer.getEmail() == null) {
                                        System.out.println("You have not been recognised as a trainer on our system");
                                        System.out.println("If you are registered, please try re-typing your email address,otherwise please return to the main menu and register");
                                    }
                                    System.out.println("Hello " + trainer.getName());
                                } catch (Exception e) {
                                    System.out.println("You have not been recognised as a trainer on our system");
                                    System.out.println("If you are registered, please try re-typing your email address,otherwise please return to the main menu and register");
                                }

                                break;

                            default:
                                break;
                        }
                    }


                    //here we have options for the logged in member

                    while (memLoggedIn) {

                        String memOp = memMenu();
                        switch (memOp.toUpperCase()) {
                            case "V":
                                System.out.println("You have chose to view your profile:");
                                System.out.println("Please press enter to show your profile:");
                                input.nextLine();
                                try {

                                    System.out.println(member.toString());
                                    System.out.println("Your Package details are as follows:  \n" + memberPackages.get(member.getChosenPackage()));

                                } catch (Exception e) {
                                    System.out.println("Something went wrong");

                                }
                                break;
                            case "U":
                                System.out.println("You have chosen to update your profile:");
                                try {

                                    System.out.println("Do you wish to update your name? Y/N");
                                    String decision = input.nextLine();
                                    switch (decision.toUpperCase()) {
                                        case "Y":
                                            System.out.println("Please enter your new full name:");
                                            String name = input.nextLine();
                                            member.setName(name);
                                            break;
                                        case "N":
                                            break;
                                        default:
                                            break;
                                    }
                                    System.out.println("Do you wish to update your email address? Y/N");


                                    System.out.println("Do you wish to update your name? Y/N");
                                    decision = input.nextLine();
                                    switch (decision.toUpperCase()) {
                                        case "Y":
                                            System.out.println("Please enter your new email address:");
                                            email = input.nextLine();
                                            member.setEmail(email);
                                            break;
                                        case "N":
                                            break;
                                        default:
                                            break;
                                    }




                                    System.out.println("Do you wish to update your gender? Y/N");
                                    decision = input.nextLine();
                                    switch (decision.toUpperCase()) {
                                        case "Y":
                                            System.out.println("Please enter your new gender (M for Male, F for Female or Unspecified for other:");
                                            String gender = input.nextLine();
                                            member.setGender(gender);
                                            break;
                                        case "N":
                                            break;
                                        default:
                                            break;
                                    }
                                    gym.save();
                                } catch (Exception e) {
                                    System.out.println("Something went wrong");

                                }
                                break;
                            case "P":
                                System.out.println("You have chosen to view your progress:");
                                System.out.println("");
                                System.out.println("To view your progress by Weight press 1, to view your progress by waist press 2");
                                String decision = input.nextLine();

                                try {
                                   // member = gym.searchMembersByEmail(email);
                                    switch (decision.toUpperCase()) {
                                        case "1":


                                            //System.out.println("Please confirm your email address:");
                                            //email = input.nextLine();
                                            //System.out.println("You are about to add an assessment for " + gym.searchMembersByEmail(email).getName());
                                            member = gym.searchMembersByEmail(email);
                                            System.out.println("Your weight has progressed in the following way: ");

                                            if (member.getAssessments().isEmpty()) {
                                                System.out.println("You only have a starting weight recorded. Your starting weight is:  " + member.getStartWeight());
                                            } else {
                                                SortedSet<String> result = member.sortedAssessmentDates();
                                                System.out.println("Your most recent weight recorded was on :" + result.last() + " " + member.getAssessments().get(result.last()).getWeight());
                                            }
                                            break;
                                        case "2":
                                            break;
                                        default:
                                            break;
                                    }
                                    gym.save();
                                } catch (Exception e) {


                                }
                                break;
                            case "L":
                                System.out.println("You have logged out, please press enter to see a menu");
                                input.nextLine();
                                userLoggedIn = false;
                                // mainMenu();
                                break;
                            default:
                                //memMenu();
                                break;

                        }
                        System.out.println("Press enter to continue");
                        input.nextLine();
                        //memMenu();
                    }
                    while (trainLoggedIn) {
                        String trainOp = trainMenu();
                        switch (trainOp.toUpperCase()) {
                            case "A"://add member

                                try {
                                    trainerAddMember();
                                    input.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Something went wrong");

                                }
                                break;
                            case "L"://list members
                                System.out.println("You have chosen to list all members:");
                                System.out.println(gym.getMembers().toString());
                                break;
                            case "S"://search member
                                System.out.println("You have chosen to search for a member");
                                System.out.println("Please enter the email address for the member you wish to view:");
                                email = input.nextLine();
                                gym.searchMembersByEmail(email);
                                break;
                            case "O"://sub option for assessments
                                System.out.println("You have chosen to see assessment options");
                                String assessOp = assessMenu();
                                switch (assessOp.toUpperCase()) {
                                    case "A":
                                        System.out.println("You have chosen to add an assessment for a member");
                                        //ask the trainer which member they are adding an assessment for
                                        System.out.println("Please enter the email address of the member:");
                                        email = input.nextLine();
                                        System.out.println("You are about to add an assessment for " + gym.searchMembersByEmail(email).getName());
                                        member = gym.searchMembersByEmail(email);
                                        System.out.println("Comment");
                                        String comment = input.nextLine();
                                        System.out.println("Weight in Kg");
                                        float weight = input.nextFloat();
                                        System.out.println("Thigh in CM");
                                        float thigh = input.nextFloat();
                                        System.out.println("Waist in CM");
                                        float waist = input.nextFloat();
                                        System.out.println(" You have entered " + weight + " " + thigh + " " + waist + " " + comment + " .");
                                        try {
                                            member.assessments.put("15/05/2019", new Assessment(weight, thigh, waist, comment));
                                            gym.save();
                                        } catch (Exception e) {
                                            System.out.println("Something went wrong saving the assessment");
                                        }

                                        System.out.println(member.toString());
                                        break;
                                    case "C":
                                        System.out.println("You have chosen to add a comment on an assessment");
                                        //get the assessment based on the date string from when it was entered
                                        System.out.println("Please enter the email address of the member:");
                                        email = input.nextLine();
                                        member = gym.searchMembersByEmail(email);
                                        //if you know the date or else list all assessments
                                        System.out.println("If you know the assessment you want to comment on press Y,else press N to view all assessments");
                                        String known = input.nextLine();
                                        switch (known.toUpperCase()) {
                                            case "Y":
                                                break;
                                            case "N":
                                                System.out.println("The assessments for " + member.getName() + " are as follows: " + member.assessments.keySet());
                                                // System.out.println("The assessments in order for  " + member.getName() + " are as follows: " + member.sortedAssessmentDates());
                                                break;
                                        }
                                        System.out.println("Please enter the date of the assessment");
                                        String date = input.nextLine();
                                        //date = input.nextLine();
                                        System.out.println("Please enter the comment that you wish to add to this assessment");
                                        comment = input.nextLine();
                                        try {
                                            member.getAssessments().get(date).setComment(comment);
                                            gym.save();
                                        } catch (Exception e) {
                                            System.out.println("Something went wrong adding the comment");
                                        }
                                        break;
                                    default:
                                        break;
                                }//add an option to go back up to the previous menu
                                break;
                            default:
                                //memMenu();
                                break;

                        }
                        System.out.println("Press enter to continue");
                        input.nextLine();
                        //memMenu();
                    }
                    break;
                case "R":
                    System.out.println("You have chosen to Register");
                    System.out.println("To Register as a Member please enter M, to Register as a Trainer please enter T");
                    userType = input.nextLine();
                    while (!registered) {
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
                        registered = false;
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

        registered = false;
        Person personPart = registerUser();

        System.out.println("Please enter your chosen Package");
        String chosenPackage = input.nextLine();
        System.out.println("Please enter your height");
        float height = input.nextFloat();
        System.out.println("Please enter your start weight");
        float startWeight = input.nextFloat();
        System.out.println("All filled in ");
        input.nextLine();
        registered = true;
        member = new Member(personPart.getEmail(), personPart.getName(), personPart.getAddress(),
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


    private void registerTrainer() {
        registered = false;

        Person personPart = registerUser();

        System.out.println("Please enter your speciality");
        String speciality = input.nextLine();
        System.out.println("All filled in ");

        //input.nextLine();
        registered = true;
        trainer = new Trainer(personPart.getEmail(), personPart.getName(), personPart.getAddress(), personPart.getGender(), speciality);
        gym.addPerson(trainer);

        //person= newMember;
        //System.out.println("All filled in ");
        try {
            gym.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Person registerUser() {
        String email = "";
        while (matches) {
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
        Person person = new Person(email, name, address, gender);
        return person;
    }

    private void populateGym() {

        try {
            gym.load();

        } catch (Exception e) {
            System.out.println("Error Loading members.xml file" + e);
        }
    }

    private boolean emailExists(String email) {
        matches = false;
        //all persons who exist
        ArrayList<Person> persons = new ArrayList<Person>();
        persons = gym.getPersons();

        //check if the email already exists
        for (int i = 0; i < persons.size(); i++) {

            if (persons.get(i).getEmail().equals(email)) {
                matches = true;
                emailCorrect = true;
                return matches;
                //break;
            } else {
                matches = false;
                emailCorrect = true;
            }
        }
        return matches;
    }


    private Member authenticateAsMember(String email) {
        userLoggedIn = false;
        memLoggedIn = false;
        Member member = null;
        if (emailExists(email)) {
            //gym.searchMembersByEmail(email);//authenticate member
            member = new Member(gym.searchMembersByEmail(email).getEmail(), gym.searchMembersByEmail(email).getName(), gym.searchMembersByEmail(email).getAddress(), gym.searchMembersByEmail(email).getGender(),
                    gym.searchMembersByEmail(email).getHeight(), gym.searchMembersByEmail(email).getWeight(), gym.searchMembersByEmail(email).getChosenPackage());
            userLoggedIn = true;
            memLoggedIn = true;
        }
        return member;
    }

    private Trainer authenticateAsTrainer(String email) {
        userLoggedIn = false;
        trainLoggedIn = false;
        Trainer trainer = null;
        if (emailExists(email)) {
            //gym.searchMembersByEmail(email);//authenticate member
            trainer = new Trainer(gym.searchTrainersByEmail(email).getEmail(), gym.searchTrainersByEmail(email).getName(), gym.searchTrainersByEmail(email).getAddress(), gym.searchTrainersByEmail(email).getGender(), gym.searchTrainersByEmail(email).getSpeciality());

            userLoggedIn = true;
            trainLoggedIn = true;
        }
        return trainer;
    }


    private Member trainerAddMember() {

        System.out.println("You have chosen to add a member:");
        System.out.println("Please enter the members full name:");
        String memName = input.nextLine();
        System.out.println("Please enter the members email address:");
        String memEmail = input.nextLine();
        System.out.println("Please enter the members address:");
        String memAddress = input.nextLine();
        System.out.println("Please enter the members chosen package");
        String memPackage = input.nextLine();
        System.out.println("Please enter the members gender:");
        String memGender = input.nextLine();
        System.out.println("Please enter the members height in Meters:");
        float memHeight = input.nextFloat();
        System.out.println("Please enter the members weight in Kg:");
        float memWeight = input.nextFloat();

        Member newMember = new Member(memEmail, memName, memAddress, memGender.toUpperCase(), memHeight, memWeight, memPackage);
        gym.addPerson(newMember);
        try {
            gym.save();
        } catch
        (Exception e) {

            System.out.println("Error saving member to system");
        }
        System.out.println("You have created a new member as follows: " + newMember.toString());
        return newMember;
    }
}

