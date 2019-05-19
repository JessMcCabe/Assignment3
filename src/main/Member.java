import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;


public class Member extends Person {
    private float height;
    private float weight;
    private float startWeight;
    private String chosenPackage;
    private String gender;
    private String name;

    public Member(String email, String name, String address, String gender, float height, float weight, String chosenPackage) {
        super(email, name, address, gender);//Because Member extends Person
        setHeight(height);
        setStartWeight(weight);
        setChosenPackage(chosenPackage);
    }


    public HashMap<String, Assessment> assessments = new HashMap<String, Assessment>();


    public float getHeight() {
        return height;
    }

    //height must be between 1 and 3 meters
    public void setHeight(float height) {
        if (height >= 1 & height <= 3) {

            this.height = height;
        } else {
            this.height = 0;
        }
    }

    public float getWeight() {
        float retWeight = 0.0f;
        if (!getAssessments().isEmpty()) {
            retWeight = latestAssessment().getWeight();
        } else {
            retWeight = weight;
        }
        return retWeight;
    }


    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    public float getStartWeight() {
        return startWeight;
    }

    //startWeight must be between 35 and 250
    public void setStartWeight(float startWeight) {
        if (startWeight >= 35.0f & startWeight <= 250.0f) {
            this.startWeight = startWeight;
        } else {
            this.startWeight = 35.0f; //set it to a default base weight if it is not within the correct range
        }
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equals("F") || (gender.equals("M"))) {
            this.gender = gender;

        } else {
            this.gender = "Unspecified";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <= 30) {
            this.name = name;

        } else {
            this.name = name.substring(0, 30);//limit the name to 30 length
        }
    }

    public HashMap<String, Assessment> getAssessments() {


        return assessments;
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }




    //Sort the assessment dates in order
    public SortedSet<String> sortedAssessmentDates() {
        //Add all date strings to an ArrayList of type Date
        ArrayList<Date> listKeyAsDate = new ArrayList<>();
        HashMap<String, Assessment> assessment = getAssessments();
        DateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        int i = 0;
        for (String key : assessment.keySet()) {
            try {
                listKeyAsDate.add(i, new SimpleDateFormat("yy/MM/dd").parse(key));
                i++;
            } catch (Exception e) {
                System.out.println("Something went wrong converting string to date");
            }


        }
        //Sort the dates in order
        Collections.sort(listKeyAsDate);
        //Add all the dates to a TreeSet to be returned as a set
        SortedSet<String> set = new TreeSet<String>();
        for (int j = 0; j < listKeyAsDate.size(); j++) {
            set.add(formatter.format(listKeyAsDate.get(j)));
        }
        return set;
        //http://www.java2s.com/Tutorials/Java/Java_Collection/0110__Java_Sorted_Set.htm
    }

    //Use the sorted assessments to return the latest assessment
    public Assessment latestAssessment() {
        Assessment assessment = null;
        if (assessments.isEmpty()) {
            assessment = null;
        } else {
            String latestDt = sortedAssessmentDates().last();
            assessment = assessments.get(latestDt);
        }
        return assessment;
    }

    @Override
    public String toString() {
        return "Member: " + name.toUpperCase() +
                "\n Height:" + "\t" + "\t" + "\t" + getHeight() +
                " \n Start Weight:" + "\t" + "\t" + weight +
                " \n Chosen Package:" + "\t" + getAddress() +
                " \n Gender:" + "\t" + "\t" + "\t" + gender +
                " \n Email Address:" + "\t" + "\t" + super.getEmail() +
                " \n Address:" + "\t" + "\t" + "\t" + super.getAddress() + "\n";
    }
}

