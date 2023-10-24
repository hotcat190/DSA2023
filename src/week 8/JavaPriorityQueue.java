import java.util.*;

public class JavaPriorityQueue {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}

class Priorities {
    PriorityQueue<Student> pq = new PriorityQueue<>();
    List<Student> getStudents(List<String> events) {
        for (String event : events) {
            if (event.equals("SERVED")) {
                if (!pq.isEmpty()) pq.remove();
            } else if (event.startsWith("ENTER")) {
                String[] input = event.split(" ");
                String name = input[1];
                double cgpa = Double.parseDouble(input[2]);
                int id = Integer.parseInt(input[3]);
                pq.add(new Student(id, name, cgpa));
            }
        }
        List<Student> returnList = new ArrayList<>();
        while (!pq.isEmpty()) {
            returnList.add(pq.remove());
        }
        return returnList;
    }
}

class Student implements Comparable<Student> {
    private final int id;
    private final String name;
    private final double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public int compareTo(Student o) {
        if (this.cgpa > o.cgpa) return -1;
        else if (this.cgpa < o.cgpa) return 1;
        else if (this.name.compareTo(o.name) < 0) return -1;
        else if (this.name.compareTo(o.name) > 0) return 1;
        else if (this.id < o.id) return -1;
        else if (this.id > o.id) return 1;
        return 0;
    }
}


