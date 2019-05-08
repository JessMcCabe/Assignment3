public class GymUtility {

    private static float maleBaseWeight = 50f;
    private static float femaleBaseWeight = 45.5f;
    private static float additionalWeight = 2.3f;
    private static double inchesOver;
    private static float weightAllowed;


    public static double calculateBMI(Member member, Assessment assessment){
        //weight in kg over height (in cm squared)

        double heightM = member.getHeight() / 3.28f; //this is the height in meters
        return Math.round((assessment.getWeight()/(heightM*heightM)*100)/100);

    }

    public static String determineBMICategory(float bmiValue){
        String category = "";


        if (bmiValue <16){
            category = "SEVERELY UNDERWEIGHT";
        }
        else  if (bmiValue >=16 & bmiValue <18.5 ){
            category = "UNDERWEIGHT";
        }
        else  if (bmiValue >=18.5 & bmiValue <25 ){
            category = "NORMAL";
        }
        else  if (bmiValue >=25 & bmiValue <30 ){
            category = "OVERWEIGHT";
        }
        else  if (bmiValue >=30 & bmiValue <35 ){
            category = "MODERATELY OBESE";
        }
        else  if (bmiValue >=35 ){
            category = "SEVERELY OBESE";
        }

        return category;



    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        boolean ideal = false;
        if (member.getGender().toUpperCase().equals("MALE")) {//if the member is male
            if (heightToFeet(member.getHeight()) <= 5) {

                if (assessment.getWeight() <= 52 & assessment.getWeight() >= 48) {
                    ideal = true;
                }
            } else if (heightToFeet(member.getHeight()) > 5) {

                inchesOver = (heightToFeet(member.getHeight()) - 5) * 10;
                weightAllowed = (maleBaseWeight + ((float) inchesOver * additionalWeight));
                if (assessment.getWeight() <= weightAllowed) {

                    ideal = true;
                }
            }


        } else if (member.getGender().toUpperCase().equals("FEMALE")) {//if the member is female
            if (heightToFeet(member.getHeight()) <= 5) {

                if (assessment.getWeight() <= 47.5 & assessment.getWeight() >= 43.5) {
                    ideal = true;
                }
            } else if (heightToFeet(member.getHeight()) > 5) {

                inchesOver = (heightToFeet(member.getHeight()) - 5) * 10;
                weightAllowed = (femaleBaseWeight + ((float) inchesOver * additionalWeight));
                if (assessment.getWeight() <= (weightAllowed + 2) & assessment.getWeight() >= (weightAllowed - 2)) {

                    ideal = true;
                }
            }
        }
        return ideal;// depending on ideal or not
    }


    public static float heightToFeet(float height){

        return height * 3.28f;
    }
}
