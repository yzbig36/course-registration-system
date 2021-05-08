



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Student extends User implements Studentmethods, java.io.Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Student(String firstname, String lastname) {
		super(firstname, lastname);
	}

	public void viewallcourses() {
		for(int i = 0; i < Courses.courselist.size(); i++) {
			Courses.courselist.get(i).printallinfo();
		}
	}
	
	public void viewnotfullcourses() {
		for(int i = 0; i < Courses.courselist.size(); i++) {
			if(Courses.courselist.get(i).getmaxstudents() != Courses.courselist.get(i).getcurrentstudents()) {
				Courses.courselist.get(i).printallinfo();
			
			}
		}
	}
	//in this method we can let students themselves to enter the course information
	public void registercourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Plase enter the course name.");
		String CourseName = in.readLine();
		System.out.println("Please enter the course section.");
		int CourseSection = Integer.parseInt(in.readLine());
		System.out.println("Please enter the first name.");
		String FirstName = in.readLine();
		System.out.println("Please enter the last name.");
		String LastName = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++) {
			if(Courses.courselist.get(i).getcoursename() == CourseName && Courses.courselist.get(i).getcoursesection() == CourseSection) {
				Student student1 = new Student(FirstName, LastName);
				Courses.courselist.get(i).getregisterstudent().add(student1);
				System.out.println("This student was added.");
			}
		}
		
	}
	
	public void withdrawcourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Plase enter the course name.");
		String CourseName = in.readLine();
		System.out.println("Please enter the first name.");
		String FirstName = in.readLine();
		System.out.println("Please enter the last name.");
		String LastName = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++) {
			if(Courses.courselist.get(i).getcoursename() == CourseName) {
				Student student2 = new Student(FirstName, LastName);
				Courses.courselist.get(i).getregisterstudent().remove(student2);
				System.out.println("This student was removed.");
			}
		}
	}
	
	public void viewregistercourses() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the first name.");
		String FirstName = in.readLine();
		System.out.println("Please enter the last name.");
		String LastName = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++) {
			for(int j = 0; j < Courses.registerstudent.size(); j++) {
				if(Courses.courselist.get(i).getregisterstudent().get(j).getfirstname() == FirstName
					&& Courses.courselist.get(i).getregisterstudent().get(j).getlastname() == LastName) {
					Courses.courselist.get(i).printallinfo();
				}
			}
			
		}
	}
	
	
}