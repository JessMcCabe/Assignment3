public class GymUtility {

    private static float maleBaseWeight = 50f;
    private static float femaleBaseWeight = 45.5f;
    private static float additionalWeight = 2.3f;
    private static double inchesOver;
    private static float weightAllowed;


    public static double calculateBMI(Member member, Assessment assessment){
        //weight in kg over height (in cm squared)

        double heightM = member.getHeight(); /// 3.28f; //this is the height in meters
        return assessment.getWeight()/(heightM*heightM);

    }

    public static String determineBMICategory(double bmiValue){
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
        if (member.getGender().toUpperCase().equals("M")) {//if the member is male
            if (heightToFeet(member.getHeight()) <= 5) {

                if (Math.abs(assessment.getWeight() -50) <= 0.3) {
                    ideal = true;
                }
            } else if (heightToFeet(member.getHeight()) > 5) {

                inchesOver = (heightToFeet(member.getHeight()) - 5) * 12;
                weightAllowed = (maleBaseWeight + ((float) inchesOver * additionalWeight));
                if (Math.abs(assessment.getWeight() - weightAllowed) <=0.3) {//https://stackoverflow.com/questions/10264313/java-if-statement-a-is-equal-to-b-plus-or-minus-2

                    ideal = true;
                }
            }


        } else if (member.getGender().toUpperCase().equals("F") | (member.getGender().toUpperCase().equals("UNSPECIFIED"))) {//if the member is female
            if (heightToFeet(member.getHeight()) <= 5) {

                if (Math.abs(assessment.getWeight() -45.5) <=0.3) {
                    ideal = true;
                }
            } else if (heightToFeet(member.getHeight()) > 5) {

                inchesOver = (heightToFeet(member.getHeight()) - 5) * 12;
                weightAllowed = (femaleBaseWeight + ((float) inchesOver * additionalWeight));
                if (Math.abs(assessment.getWeight() - weightAllowed) <=0.3) {

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
