import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class GymAPI {
    private ArrayList<Person> persons;
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;
    private ArrayList<PremiumMember> premiumMembers;
    private ArrayList<StudentMember> studentMembers;

    private static GymUtility gymUtility = new GymUtility();

    public GymAPI() {
        this.persons = new ArrayList<>();
        this.members = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.premiumMembers = new ArrayList<>();
        this.studentMembers = new ArrayList<>();


    }

    public ArrayList<Member> getMembers() {

        return members;


    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public ArrayList<Person> getPersons() {
        splitPersons();
        return persons;

    }


    /*Iterate over Persons and add Trainers to the trainers ArrayList and all member types to the members ArrayList
        this way all member types (students, premium and normal members are counted as all 'members'
    */
    public void splitPersons() {

        for (Person person : persons) {
            if (person instanceof Member) {
                members.add((Member) person);
            } else if (person instanceof Trainer) {
                trainers.add((Trainer) person);
            } else if (person instanceof PremiumMember) {
                members.add((PremiumMember) person);
            } else if (person instanceof StudentMember) {
                members.add((StudentMember) person);
            }
        }
    }

    /*Iterate over the Members and add Student Members to the studentMembers ArrayList and Premium Members to the premiumMembers ArrayList
     */
    public ArrayList<PremiumMember> getPremiumMembers() {
        for (Member member : members) {

            if (member instanceof PremiumMember) {
                premiumMembers.add((PremiumMember) member);
            }

        }

        return premiumMembers;
    }


    public ArrayList<StudentMember> getStudentMembers() {

        for (Member member : members) {

            if (member instanceof StudentMember) {
                studentMembers.add((StudentMember) member);
            }

        }
        return studentMembers;


    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public int numberOfMembers() {
        if (members.size() != 0) {
            return members.size();
        } else {
            return 0;
        }
    }

    public int numberOfTrainerss() {
        if (trainers.size() != 0) {
            return trainers.size();
        } else {
            return 0;
        }
    }

    public boolean isValidMemberIndex(int index) {

        if (index <= members.size() - 1 & !members.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidTrainerIndex(int index) {
        if (index <= trainers.size() - 1 & !trainers.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //Pass in the email address and search for the member in the Members ArrayList
    public Member searchMembersByEmail(String email) {
        Member member = null;
        ArrayList<Member> members = members = getMembers();

        //check if the email already exists
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getEmail().equals(email)) {
                member = members.get(i);
            }
        }
        return member;


    }

    //Pass in the name and search for the member in the Members ArrayList
    public ArrayList<String> searchMembersByName(String name) {
        ArrayList<String> member = new ArrayList<>();
        ArrayList<Member> members = getMembers();
        //check if the email already exists
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().contains(name)) {
                member.add(members.get(i).getName());
            }
        }
        return member;
    }

    //Pass in the email address and search for the trainer in the Trainers ArrayList
    public Trainer searchTrainersByEmail(String email) {
        Trainer trainer = null;
        ArrayList<Trainer> trainers = getTrainers();
        //check if the email already exists
        for (int i = 0; i < trainers.size(); i++) {
            if (trainers.get(i).getEmail().equals(email)) {
                trainer = trainers.get(i);
            }
        }
        return trainer;
    }

    //Pass in the name and search for the trainer in the Trainers ArrayList
    public ArrayList<String> searchTrainersByName(String name) {
        ArrayList<String> trainer = new ArrayList<>();
        ArrayList<Trainer> trainers = getTrainers();
        //check if the email already exists
        for (int i = 0; i < trainers.size(); i++) {
            if (trainers.get(i).getName().contains(name)) {
                trainer.add(trainers.get(i).getName());
            }
        }
        return trainer;
    }


    //List all members who meet the criteria of ideal weight
    public ArrayList<Member> listMembersWithIdealWeight() {
        ArrayList<Member> members = getMembers();
        ArrayList<Member> membersWithIdealWeight = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            if (!members.get(i).getAssessments().isEmpty()) {
                if (gymUtility.isIdealBodyWeight(members.get(i), members.get(i).latestAssessment())) {
                    membersWithIdealWeight.add(members.get(i));
                }
            }
        }
        return membersWithIdealWeight;// depending on ideal or not
    }

    //List Members by specific BMI category (even if the category listed only partially matches the String
    public ArrayList<Member> listMembersBySpecificBMICategory(String category) {

        ArrayList<Member> members = getMembers();
        ArrayList<Member> membersByBMI = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            double BMI = gymUtility.calculateBMI(members.get(i), members.get(i).latestAssessment());
            if (gymUtility.determineBMICategory(BMI).toUpperCase().contains(category.toUpperCase())) {
                membersByBMI.add(members.get(i));
            }
        }
        return membersByBMI;
    }

    //List members details in both imperial and metric units
    public String listMemberDetailsImperialAndMetric() {
        String memberImperialAndMetric = "";
        ArrayList<Member> members = getMembers();
        if (members.size() > 0) {
            //for all members , get their weight and height and name and add it to the string members, separate each member with a new line
            for (Member member : members) {
                //get the metric for and build the string
                memberImperialAndMetric = memberImperialAndMetric + member.getName() + ": " + (int) (float) Math.round(member.getWeight()) + " kg (" + (int) (kgToLb(member.getWeight())) + " lbs)" +
                        Float.toString((float) Math.round(member.getHeight() * 10) / 10) + " metres (" + (int) (float) Math.round(mtToInch(member.getHeight())) + " inches)" + " \n"; //convert float to string!
            }
        } else if (members.size() <= 0) {
            memberImperialAndMetric = "No registered members";
        }
        return memberImperialAndMetric;
    }

    //Convert Kg to pounds
    public float kgToLb(float kg) {
        float lb = 0.0f;
        //1 kg = 2.20462262185 lb
        float mult = 2.20462262185f;
        lb = kg * mult;

        return (float) Math.round(lb);
    }

    //Convert meters to inches
    public float mtToInch(float mt) {
        float inch = 0.0f;
        //1 m is equivalent to 1.0936 yards, or 39.370 inches
        float mult = 39.370f;
        inch = mt * mult;
        return (float) Math.round(inch * 10) / 10; //Control number of decimal places
    }


    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        // ------------------ PREVENT SECURITY WARNINGS---------------------------
        Class<?>[] classes = new Class[]{Person.class, Member.class, Trainer.class, Assessment.class, StudentMember.class, PremiumMember.class}; // The Classes we are reading in
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -----------------------------------------------------------------------
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
        persons = (ArrayList<Person>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
        out.writeObject(persons);
        out.close();
    }

}
