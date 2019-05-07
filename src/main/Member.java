
// THIS CODE IS INCOMPLETE

public class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;
    private String gender;
    private String name;

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
    }

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


    @Override
    public String toString() {
        return "Member{" +
                "height=" + height +
                ", startWeight=" + startWeight +
                ", chosenPackage='" + chosenPackage + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                '}' + " " + super.toString();
    }
}

