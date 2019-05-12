
// THIS CODE IS INCOMPLETE

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Member extends Person {
    private float height;
    private float weight;
    private float startWeight;
    private String chosenPackage;
    private String gender;
    private String name;

    public Member(String email, String name, String address,
                  String gender, float height, float weight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setWeight(weight);
        setChosenPackage(chosenPackage);
    }


    public HashMap<String,Assessment> assessments = new HashMap<String,Assessment>();


    public float getHeight() {
        return height;
    }

    //height must be between 1 and 3
    public void setHeight(float height) {
        if (height >= 1 & height <= 3) {
            if (height % 2 != 0) {
                this.height = Math.round(height);
            } else {
                this.height = height;
            }
        } else this.height = 0;
    }

    public float getWeight() {
        return weight;
    }
    //startWeight must be bewteen 35 and 250
    public void setWeight(float weight) {
        if (weight >= 35 & weight <= 250) {
            if (weight % 2 != 0) {
                this.weight = Math.round(weight);
            } else {
                this.weight = weight;
            }
        }
        else this.weight =0;
    }

    public float getStartWeight() {
        return startWeight;
    }
    //startWeight must be bewteen 35 and 250
    public void setStartWeight(float startWeight) {
        if (startWeight >= 35 & startWeight <= 250) {
            if (startWeight % 2 != 0) {
                this.startWeight = Math.round(startWeight);
            } else {
                this.startWeight = startWeight;
            }
        }
        else this.startWeight =0;
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


       //get the Key (which is the dates, from the assessments)


        return null;

       //http://www.java2s.com/Tutorials/Java/Java_Collection/0110__Java_Sorted_Set.htm
       //TO_DO
   }

   public Assessment latestAssessment (){
        int i=0;//for resolving error in test only -
       HashMap<String,Assessment> assessments = new HashMap<>();
       return  assessments.get(i);

       //TO_DO

   }

    @Override
    public String toString() {
        return "Member: " + name.toUpperCase() +
                "\n Height:" + "\t"+"\t" +"\t" + height +
                " \n Start Weight:" + "\t"+"\t" +weight +
                " \n Chosen Package:" + "\t" +chosenPackage +
                " \n Gender:" + "\t"+"\t"+"\t" +gender +
                " \n Email Address:" + "\t"+"\t" +super.getEmail() +
                 " \n Address:" + "\t"+"\t" +"\t"+super.getAddress() + "\n";
    }
}

