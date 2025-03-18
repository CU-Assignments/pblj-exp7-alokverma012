import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.next();
                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();
                    StudentController.addStudent(name, dept, marks);
                    break;
                case 2:
                    StudentView.displayStudents(StudentController.viewStudents());
                    break;
                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    int id = sc.nextInt();
                    System.out.print("Enter New Marks: ");
                    int newMarks = sc.nextInt();
                    StudentController.updateStudent(id, newMarks);
                    break;
                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int delId = sc.nextInt();
                    StudentController.deleteStudent(delId);
                    break;
                case 5:
                    System.out.println("✅ Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
    }
}
