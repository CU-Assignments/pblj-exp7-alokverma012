import java.util.List;

public class StudentView {
    public static void displayStudents(List<Student> students) {
        System.out.println("\n--- Student List ---");
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentID() + ", Name: " + student.getName() +
                    ", Department: " + student.getDepartment() + ", Marks: " + student.getMarks());
        }
    }
}
