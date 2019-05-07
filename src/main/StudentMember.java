public class StudentMember extends Member  {

    private int studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address, String gender, float height, float startWeight, String chosenPackage, int studentId, String collegeName) {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;

    }

    @Override
    public String toString() {
        return "StudentMember{" +
                "studentId=" + studentId +
                ", collegeName='" + collegeName + '\'' +
                '}' + " " +  super.toString();
    }
}
