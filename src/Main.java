import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        students.put("S001", new Student("Ali", 3.8, 20));
        students.put("S002", new Student("Bek", 3.5, 19));
        students.put("S003", new Student("Dana", 3.8, 21));
        students.put("S004", new Student("Elena", 3.2, 22));
        students.put("S005", new Student("Farid", 3.9, 20));
        students.put("S006", new Student("Gul", 3.5, 18));

        System.out.println("=== Task 1: All students ===");
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        String findId = "S003";
        System.out.println("\nFind by ID " + findId + ": " + students.get(findId));

        String removeId = "S004";
        students.remove(removeId);
        System.out.println("Removed student with ID: " + removeId);

        String updateId = "S002";
        Student studentToUpdate = students.get(updateId);
        if (studentToUpdate != null) {
            studentToUpdate.setGpa(3.7);
            System.out.println("Updated GPA for " + updateId + ": " + studentToUpdate);
        }

        // ====================== SORTING (IMPORTANT) ======================
        List<Student> sortedStudents = new ArrayList<>(students.values());

        System.out.println("\nSorted by GPA (Comparable, ascending):");
        sortedStudents.sort(null);
        for (Student student : sortedStudents) {
            System.out.println(student);
        }

        System.out.println("\nSorted by name (Comparator, ascending):");
        sortedStudents.sort(Comparator.comparing(Student::getName));
        for (Student student : sortedStudents) {
            System.out.println(student);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        List<Student> topStudents = new ArrayList<>(students.values());
        topStudents.sort(Comparator.comparing(Student::getGpa).reversed());
        int topK = Math.min(3, topStudents.size());
        for (int i = 0; i < topK; i++) {
            System.out.println((i + 1) + ". " + topStudents.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        HashMap<Double, List<String>> gpaGroups = new HashMap<>();
        for (Student student : students.values()) {
            gpaGroups.computeIfAbsent(student.getGpa(), key -> new ArrayList<>()).add(student.getName());
        }
        for (Map.Entry<Double, List<String>> entry : gpaGroups.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("GPA " + entry.getKey() + " -> " + String.join(", ", entry.getValue()));
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        Course javaCourse = new Course("Java OOP");
        Course databasesCourse = new Course("Databases");
        Course algorithmsCourse = new Course("Algorithms");

        courseMap.put(javaCourse, new ArrayList<>(List.of(students.get("S001"), students.get("S003"), students.get("S005"))));
        courseMap.put(databasesCourse, new ArrayList<>(List.of(students.get("S002"), students.get("S006"))));
        courseMap.put(algorithmsCourse, new ArrayList<>(List.of(students.get("S001"), students.get("S002"))));

        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Student student : entry.getValue()) {
                System.out.println("  - " + student);
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        Comparator<Student> byGpaDescThenName = Comparator
                .comparing(Student::getGpa)
                .reversed()
                .thenComparing(Student::getName);

        List<Student> challengeSort = new ArrayList<>(students.values());
        challengeSort.sort(byGpaDescThenName);
        for (Student student : challengeSort) {
            System.out.println(student);
        }
    }
}



