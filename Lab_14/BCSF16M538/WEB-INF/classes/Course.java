public class Course {
    private String name;
    private int semester, course_id;

    Course(int course_id, String name, int semester) {
        this.course_id = course_id;
        this.name = name;
        this.semester = semester;
    }

    public int getCourseId() {
        return course_id;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public void setCourseId(int course_id) {
        this.course_id = course_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}