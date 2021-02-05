import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    Connection con;

    public UserDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/sms?serverTimezone=Asia/Karachi", "root",
                    "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean insertCousrse(String name, int semester) {
        try {
            PreparedStatement ps = this.con.prepareStatement("INSERT INTO course (name, semester) VALUES (?,?)");
            ps.setString(1, name);
            ps.setInt(2, semester);
            if (ps.executeUpdate() > 0)
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean assignCourse(int course_id, String email) {
        try {
            PreparedStatement ps = this.con
                    .prepareStatement("INSERT INTO course_assignment (course_id, email) VALUES (?, ?)");
            ps.setInt(1, course_id);
            ps.setString(2, email);
            if (ps.executeUpdate() > 0)
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean insertUser(UserProfile user, int user_type) {
        try {
            PreparedStatement ps = this.con
                    .prepareStatement("INSERT INTO authentication(email, password, user_type) VALUES (?, ?,?)");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user_type);
            int result = ps.executeUpdate();
            if (result > 0) {
                ps = this.con.prepareStatement(
                        "INSERT INTO profile (email, name, dob, semester, phone_no) VALUES (?, ?, ?, ?,?)");
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getName());
                ps.setString(3, user.getDob());
                ps.setInt(4, user.getSemester());
                ps.setString(5, user.getPhone());
                if (ps.executeUpdate() > 0)
                    return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean authenticate(String email, String password) {
        try {
            PreparedStatement ps = this.con
                    .prepareStatement("SELECT * FROM authentication WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public UserProfile getUserProfile(String email) {
        UserProfile user = null;
        try {
            PreparedStatement ps = this.con.prepareStatement(
                    "SELECT * FROM authentication a, profile p WHERE a.email = p.email AND a.email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserProfile(rs.getString("name"), rs.getString("phone_no"), rs.getString("email"),
                        rs.getString("password"), rs.getString("dob"), rs.getInt("semester"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public int getUserType(String email) {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT * FROM authentication WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_type");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public ArrayList<Course> getCoursesInSemester(int semester) {
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT * FROM course WHERE semester = ?");
            ps.setInt(1, semester);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        } catch (Exception e) {
            System.out.println(e);
        }
        return courses;
    }

    public ArrayList<Course> getCoursesOfTeacher(String email) {
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            PreparedStatement ps = this.con.prepareStatement(
                    "SELECT * FROM course c, course_assignment ca WHERE c.course_id = ca.course_id AND ca.email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        } catch (Exception e) {
            System.out.println(e);
        }
        return courses;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT * FROM course c");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        } catch (Exception e) {
            System.out.println(e);
        }
        return courses;
    }

    public ArrayList<UserProfile> getTeachers() {
        ArrayList<UserProfile> teachers = new ArrayList<UserProfile>();
        try {
            PreparedStatement ps = this.con.prepareStatement(
                    "SELECT * FROM authentication a, profile p WHERE a.email = p.email AND user_type = 2");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                teachers.add(new UserProfile(rs.getString("name"), rs.getString("phone_no"), rs.getString("email"),
                        rs.getString("password"), rs.getString("dob"), rs.getInt("semester")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return teachers;
    }
}
