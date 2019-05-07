public class Trainer extends Person  {

    private String speciality;

    public Trainer(String email, String name, String address, String gender, String speciality) {
        super(email, name, address, gender);
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "speciality='" + speciality + '\'' +
                '}' + " " + super.toString();
    }
}
