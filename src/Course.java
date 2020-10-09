import java.util.ArrayList;

public class Course implements Comparable<Course>{
    int CourseId;
    ArrayList<Course> courses = new ArrayList<>();
    int slotNo;
    int Degree;
    int NoOfstudents;

    public Course(int courseId, int noOfstudents) {
        CourseId = courseId;
        NoOfstudents = noOfstudents;
        Degree=0;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", courses=" + courses.size() +
                ", slotNo=" + slotNo +
                ", Degree=" + Degree +
                '}';
    }




    @Override
    public int compareTo(Course o ) {
        if(this.Degree < o.Degree) return 1;
        else if(this.Degree > o.Degree ) return -1;
        else  return 0;
    }
}