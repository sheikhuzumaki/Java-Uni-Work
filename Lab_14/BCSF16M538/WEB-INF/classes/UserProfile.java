public class UserProfile {
	private String name, dob, email, phone, password;
	private int semester;

	UserProfile(String name, String phone, String email, String password, String dob, int semester) {
		this.name = name;
		this.dob = dob;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.semester = semester;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public int getSemester() {
		return semester;
	}

	public String getPhone() {
		return phone;
	}

	public String toString() {
		String s = "<p>Name: " + this.name + "</p><p>Email: " + this.email + "</p><p>Phone: " + this.phone + "</p>";
		if (this.dob != null)
			s += "<p>Semester: " + this.semester + "</p><p>Date Of Birth: " + this.dob + "</p>";
		return s;
	}
}