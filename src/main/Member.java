
// THIS CODE IS INCOMPLETE

import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Member extends Person {
    private float height;
    //private static float assessWeight;
    private  float weight;
    private float startWeight;
    private String chosenPackage;
    private String gender;
    private String name;

    public Member(String email, String name, String address,
                  String gender, float height, float weight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(weight);
        setChosenPackage(chosenPackage);
    }


    public HashMap<String,Assessment> assessments = new HashMap<String,Assessment>();


    public float getHeight() {
        return height;
    }

    //height must be between 1 and 3
    public void setHeight(float height) {
        if (height >= 1 & height <= 3) {

                this.height = height;
            }

        else {
            this.height = 0;
        }
    }

    public float getWeight() {
        float retWeight = 0.0f;
        if (!getAssessments().isEmpty()) {
            retWeight = latestAssessment().weight;
            //return weight;
        }
        else {
            retWeight = weight;
        }
        return  retWeight;

    }



    public  void setWeight() {
        this.weight = weight;
    }
    public float getStartWeight() {
        return startWeight;
    }
    //startWeight must be bewteen 35 and 250
    public void setStartWeight(float startWeight) {
        if (startWeight >= 35.0f & startWeight <= 250.0f) {
                this.startWeight = startWeight;
            }
        else {
            this.startWeight =35.0f;
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
        if (name.length()<=30) {
            this.name = name;

        } else {
            this.name = name.substring(0,30);
        }
    }

    public HashMap<String,Assessment> getAssessments() {


        return assessments;
    }


   public SortedSet<String> sortedAssessmentDates() {

        ArrayList<Date> listKeyAsDate = new ArrayList<>();
        HashMap<String, Assessment> assessment = getAssessments();
       DateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        int i =0;
       for ( String key : assessment.keySet() ) {
            try {
                listKeyAsDate.add(i, new SimpleDateFormat("yy/MM/dd").parse(key));
                i++;
            }
            catch (Exception e){
                System.out.println("Something went wrong converting string to date");
            }



       }
       Collections.sort(listKeyAsDate);
       SortedSet<String> set = new TreeSet<String>();
       for(int j = 0; j<listKeyAsDate.size(); j++){
           set.add(formatter.format(listKeyAsDate.get(j)).toString());
       }




        return set;

       //http://www.java2s.com/Tutorials/Java/Java_Collection/0110__Java_Sorted_Set.htm

   }

   public Assessment latestAssessment (){
        Assessment assessment = null;


       if(assessments.isEmpty()) {
           assessment = null;
       }
       else
       {
           String latestDt = sortedAssessmentDates().last();
           assessment = assessments.get(latestDt);
       }
      return assessment;

   }

    @Override
    public String toString() {
        return "Member: " + name.toUpperCase() +
                "\n Height:" + "\t"+"\t" +"\t" + height +
                " \n Start Weight:" + "\t"+"\t" +weight +
                " \n Chosen Package:" + "\t" +chosenPackage +
                " \n Gender:" + "\t"+"\t"+"\t" +gender + //this needs to print male or female depending of F or M
                " \n Email Address:" + "\t"+"\t" +super.getEmail() +
                 " \n Address:" + "\t"+"\t" +"\t"+super.getAddress() + "\n";
    }
}

