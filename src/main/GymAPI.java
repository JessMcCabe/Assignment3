import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// THIS CODE IS INCOMPLETE

public class GymAPI {
    private ArrayList<Person> persons;
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    private static float maleBaseWeight = 50f;
    private static float femaleBaseWeight = 45.5f;
    private static float additionalWeight = 2.3f;
    private static double inchesOver;
    private static float weightAllowed;

private static GymUtility gymUtility = new GymUtility();

    public GymAPI() {
        this.persons = new ArrayList<Person>();
        this.members = new ArrayList<Member>();
        this.trainers = new ArrayList<Trainer>();

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

    public void splitPersons(){

        for (Person person : persons) {
            if (person instanceof Member) {
                members.add((Member)person);
            } else if (person instanceof Trainer) {
                trainers.add((Trainer)person);
            }
        }
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
       if(members.size() !=0){
           return members.size();
       }
       else {
           return 0;
       }
    }

    public int numberOfTrainerss() {
        if(trainers.size() !=0){
            return trainers.size();
        }
        else {
            return 0;
        }
    }

    public boolean isValidMemberIndex(int index) {

        if (index <= members.size()-1 &!members.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidTrainerIndex(int index) {
        if (index <= trainers.size()-1 & !trainers.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    public Member searchMembersByEmail(String email){
        Member member = null;
        ArrayList<Member> members = new ArrayList<Member>();
        members = getMembers();

        //check if the email already exists
        for(int i = 0; i<members.size(); i ++){

            if(members.get(i).getEmail().equals(email)){

                member = members.get(i);
                //break;
            }

        }
        return member;


}

    public ArrayList<String> searchMembersByName(String name){
        ArrayList<String> member = new ArrayList<>();
        ArrayList<Member> members = members = getMembers();

        //check if the email already exists
        for(int i = 0; i<members.size(); i ++){

            if(members.get(i).getName().contains(name)){

                member.add(members.get(i).getName());
                //break;
            }

        }
        return member;


    }

    public Trainer searchTrainersByEmail(String email){
        Trainer trainer = null;
        ArrayList<Trainer> trainers = trainers = getTrainers();

        //check if the email already exists
        for(int i = 0; i<trainers.size(); i ++){
            if(trainers.get(i).getEmail().equals(email)){
                trainer = trainers.get(i);
            }
        }
        return trainer;

    }

    public ArrayList<String> searchTrainersByName(String name){
        ArrayList<String> trainer = new ArrayList<>();
        ArrayList<Trainer> trainers = trainers = getTrainers();

        //check if the email already exists
        for(int i = 0; i<trainers.size(); i ++){

            if(trainers.get(i).getName().contains(name)){

                trainer.add(trainers.get(i).getName());
                //break;
            }

        }
        return trainer;


    }

    public ArrayList<Member> listMembersWithIdealWeight() {
        ArrayList<Member> members = getMembers();
        ArrayList<Member> membersWithIdealWeight = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
        if(gymUtility.isIdealBodyWeight(members.get(i),members.get(i).latestAssessment())) {
            membersWithIdealWeight.add(members.get(i));
        }

        }

        return membersWithIdealWeight;// depending on ideal or not

    }


    public ArrayList<Member>  listMembersBySpecificBMICategory(String category){

        ArrayList<Member> members = new ArrayList<>();
        members = getMembers();
        return members;
        //TO-DO
    }


    public String listMemberDetailsImperialAndMetric(){
        String member = "";
        ArrayList<Member> members = new ArrayList<>();
        members = getMembers();
        return member;
        //TO-DO
    }


    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        // ------------------ PREVENT SECURITY WARNINGS---------------------------
        Class<?>[] classes = new Class[] { Person.class,Member.class,Trainer.class }; // The Classes we are reading in
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -----------------------------------------------------------------------
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
        persons = (ArrayList<Person>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
        out.writeObject(persons);
        out.close();
    }

}
