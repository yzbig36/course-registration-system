



import java.util.*;
import java.io.*;

class Admin extends User implements Adminmethods, java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ArrayList<Student> studentobject = new ArrayList<Student>();
	
	
	public Admin() {
		super();
		this.username = "Admin";
		this.password = "Admin001";
		
	}
	
	public ArrayList<Student> getstudentobject(){
		return studentobject;
	}
	
	public void setstudentobject(ArrayList<Student> studentobject) {
		Admin.studentobject = studentobject;
	}
	//I will let the admin to input the course information
	public void createcourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a course name.");
		String courseName = in.readLine();
		System.out.println("Enter a course id.");
		String courseID = in.readLine();
		System.out.println("Enter the max students of the course.");
		int maxStudents = Integer.parseInt(in.readLine());
		System.out.println("Enter the current students of the course.");
		int currentStudents = Integer.parseInt(in.readLine());
		System.out.println("Enter the instructor of the course.");
		String instructor = in.readLine();
		System.out.println("Enter the course section of the course.");
		int courseSection = Integer.parseInt(in.readLine());
		System.out.println("Enter the course location.");
		String courseLocation = in.readLine();
		Courses x = new Courses(courseName, courseID, maxStudents, currentStudents, instructor, courseSection, courseLocation);
		Courses.courselist.add(x);
		System.out.println("A new course is added.");
		Courses.seralization();
	}
	
	public void deletecourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a course name.");
		String courseName = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++){
			if(Courses.courselist.get(i).getcoursename() == courseName) {
				Courses.courselist.remove(i);
				System.out.println("The course has been removed.");	
			}
			//we also have to consider the possibility that the course may not be found
			else{
				System.out.println("Sorry, we cannot find this course. Please try again.");
			    Admin admin = new Admin();
			    admin.deletecourse();
			    Courses.seralization();
			}
		}
    }
	
	//we can let admin change instructor and course location
	public void editcourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 1 if you want to change the instructor, and 2 if you want to change the course location.");
		int result = Integer.parseInt(in.readLine());
		System.out.println("Enter the course name you want to edit.");
		String courseName = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++) {
		   if(result == 1) {
			  System.out.println("Enter a new instructor.");
			  if(Courses.courselist.get(i).getcoursename() == courseName) {
			     Courses.courselist.get(i).setinstructor(in.readLine());
			     System.out.println("You edited successfully.");
			     Courses.seralization();
			  }
		   
		}  
		   else if(result == 2) {
			   System.out.println("Enter a new course location.");
			   if(Courses.courselist.get(i).getcoursename() == courseName) {
			      Courses.courselist.get(i).setcourselocation(in.readLine());
			      System.out.println("You edited sucessfully.");
			      Courses.seralization();
			   }
		   }
		}
	}
	
	public void displaycourse() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the course ID.");
		String courseID = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++) {
			if(Courses.courselist.get(i).getcourseid() == courseID) {
				Courses.courselist.get(i).printallinfo();
				
			}
		}
	}
	
	public void registerforstudent() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter the first name");
		String firstName = in.readLine();
		System.out.println("enter the last name");
		String lastName = in.readLine();
		Student student1 = new Student(firstName, lastName);
		studentobject.add(student1);
		System.out.println("Student is registered.");
		Courses.seralization();
	}
	
	public void ViewAllCourses() {
		for(int i = 0; i < Courses.courselist.size(); i++) {
			Courses.courselist.get(i).printallinfo();
		}
	}
	
	public ArrayList<Courses> ViewFullCourses(){
		ArrayList<Courses> fullcourses = new ArrayList<Courses>();
		for(int i = 0; i < Courses.courselist.size(); i++) {
			if(Courses.courselist.get(i).getmaxstudents() == Courses.courselist.get(i).getcurrentstudents()) {
				Courses.courselist.get(i).printallinfo();
				fullcourses.add(Courses.courselist.get(i));
				
			}
		}
		return fullcourses;
	}
	
	public void FileForFullCourses() throws IOException{
		java.io.File file = new java.io.File("fullcourses.txt");
		PrintWriter output = new PrintWriter(file);
		for(int i = 0; i < ViewFullCourses().size(); i++) {
			String result = ViewFullCourses().get(i).printallinfo();
			output.print(result);
			output.println();
			
			output.close();
			
		}
		
	}
	
	public void ViewRegisterStudents() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter coursename");
		String courseName = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++) {
			if(Courses.courselist.get(i).getcoursename() == courseName) {
				for(int j = 0; j < Courses.registerstudent.size(); i++) {
					String firstname = Courses.courselist.get(i).getregisterstudent().get(j).getfirstname();
					String lastname = Courses.courselist.get(i).getregisterstudent().get(j).getlastname();
					System.out.println("Student's name is " + firstname + lastname);
				}
			}
		}
	}
	
	public void ViewRegisterCourses() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter first name.");
		String firstname = in.readLine();
		System.out.println("enter the last name");
		String lastname = in.readLine();
		for(int i = 0; i < Courses.courselist.size(); i++){
			for(int j = 0; j < Courses.registerstudent.size();j++) {
				if(Courses.courselist.get(i).getregisterstudent().get(j).getfirstname() == firstname
						&& Courses.courselist.get(i).getregisterstudent().get(j).getlastname() == lastname) {
					String coursename = Courses.courselist.get(i).getcoursename();
					System.out.println("The courses are " + coursename);
				}
			}
				
		}
		
		
	}
	
	public void SortCourses() {
		for(int i = 0; i < Courses.courselist.size(); i++) {
			for(int j = i + 1; j < Courses.courselist.size(); j++) {
				if(Courses.courselist.get(i).getcurrentstudents() < Courses.courselist.get(j).getcurrentstudents()) {
					Courses temp = Courses.courselist.get(i);
					Courses.courselist.set(j, temp);
					Courses.courselist.set(i, Courses.courselist.get(j));
					Courses.seralization();
				}
			}
		}
		for(int i = 0; i < Courses.courselist.size(); i++) {
			Courses.courselist.get(i).printallinfo();
			
		}
	}
	
	
	

	

}
