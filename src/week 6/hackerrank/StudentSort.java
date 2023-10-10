import java.util.*;

class Student implements Comparable<Student> {
    private int id;
    private String fname;
    private double cgpa;
    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public double getCgpa() {
        return cgpa;
    }

    @Override
    public int compareTo(Student other) {
        if (this.cgpa == other.cgpa &&
                this.fname.equals(other.fname) &&
                this.id == other.id) {
            return 0;
        }

        if (this.cgpa > other.cgpa) {
            return -1;
        } else if (this.cgpa < other.cgpa) {
            return 1;
        }

        int fnameCmp = this.fname.compareTo(other.fname);
        if (fnameCmp > 0) {
            return 1;
        } else if (fnameCmp < 0) {
            return -1;
        }

        if (this.id > other.id) {
            return 1;
        } else {
            return -1;
        }
    }
}

//Complete the code
public class StudentSort
{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<Student>();
        while(testCases>0){
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        Collections.sort(studentList);

        for(Student st: studentList){
            System.out.println(st.getFname());
        }
    }
}



