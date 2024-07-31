
public class Main {
    public static void main(String[] args) {
        Student student = new Student("John Doe", 1, "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        controller.updateView();
        controller.setStudentName("Jane Smith");
        controller.setStudentId(2);
        controller.setStudentGrade("B");
        controller.updateView();
    }
}

