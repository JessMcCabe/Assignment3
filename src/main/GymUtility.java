public class GymUtility {

    private static float maleBaseWeight = 50f;
    private static float femaleBaseWeight = 45.5f;
    private static float additionalWeight = 2.3f;
    private static double inchesOver;
    private static float weightAllowed;


    //BMI calculated : weight in kg over height (in cm squared)
    public static double calculateBMI(Member member, Assessment assessment) {
        double heightM = member.getHeight(); /// 3.28f; //this is the height in meters
        return assessment.getWeight() / (heightM * heightM);

    }

    //based on the BMI, get the BMI category
    public static String determineBMICategory(double bmiValue) {
        String category = "";


        if (bmiValue < 16) {
            category = "SEVERELY UNDERWEIGHT";
        } else if (bmiValue >= 16 & bmiValue < 18.5) {
            category = "UNDERWEIGHT";
        } else if (bmiValue >= 18.5 & bmiValue < 25) {
            category = "NORMAL";
        } else if (bmiValue >= 25 & bmiValue < 30) {
            category = "OVERWEIGHT";
        } else if (bmiValue >= 30 & bmiValue < 35) {
            category = "MODERATELY OBESE";
        } else if (bmiValue >= 35) {
            category = "SEVERELY OBESE";
        }

        return category;


    }

    /* check if the member is the ideal body weight
        For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
        For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
        Note: if no gender is specified, return the result of the female calculation.
        Note: if the member is 5 feet or less, return 50kg for male and 45.5kg for female.
        */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        boolean ideal = false;
        if (member.getGender().toUpperCase().equals("M")) {//if the member is male
            if (heightToFeet(member.getHeight()) <= 5) {
                if (Math.abs(assessment.getWeight() - 50) <= 0.3) {//This allows us to be plus or minus 0.3kg
                    ideal = true;
                }
            } else if (heightToFeet(member.getHeight()) > 5) {
                inchesOver = (heightToFeet(member.getHeight()) - 5) * 12;
                weightAllowed = (maleBaseWeight + ((float) inchesOver * additionalWeight));
                if (Math.abs(assessment.getWeight() - weightAllowed) <= 0.3) {//https://stackoverflow.com/questions/10264313/java-if-statement-a-is-equal-to-b-plus-or-minus-2
                    ideal = true;
                }
            }
        } else if (member.getGender().toUpperCase().equals("F") | (member.getGender().toUpperCase().equals("UNSPECIFIED"))) {//if the member is female
            if (heightToFeet(member.getHeight()) <= 5) {
                if (Math.abs(assessment.getWeight() - 45.5) <= 0.3) {
                    ideal = true;
                }
            } else if (heightToFeet(member.getHeight()) > 5) {
                inchesOver = (heightToFeet(member.getHeight()) - 5) * 12;
                weightAllowed = (femaleBaseWeight + ((float) inchesOver * additionalWeight));
                if (Math.abs(assessment.getWeight() - weightAllowed) <= 0.3) {
                    ideal = true;
                }
            }
        }
        return ideal;// depending on ideal or not
    }

    //Convert height (which we take in in meters) to feet (3.28 feet in a meter)
    public static float heightToFeet(float height) {

        return height * 3.28f;
    }
}
