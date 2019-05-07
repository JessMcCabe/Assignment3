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
        return persons;
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
