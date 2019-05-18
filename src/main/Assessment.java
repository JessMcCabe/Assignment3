import java.util.Date;

public class Assessment {
    public float weight;
    public float thigh;
    public float waist;
    public String comment;
    public Trainer trainer;


    public Assessment(float weight,float thigh,float waist,String comment)
    {
       this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
      //  Member.setAssessWeight(weight);




    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;

    }



    public double getThigh() {
        return thigh;
    }

    public void setThigh(float thigh) {
        this.thigh = thigh;

    }


    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

}
