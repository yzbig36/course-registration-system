

import java.io.*;
import java.util.*;

public class Courses implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String coursename;
	String courseid;
	int maxstudents;
	int currentstudents;
	String instructor;
	int coursesection;
	String courselocation;
	static ArrayList<Student> registerstudent;
	static ArrayList<Courses> courselist;
	
	Courses(String coursename, String courseid, int maxstudents, int currentstudents,
			String instructor, int coursesection, String courselocation){
		this.coursename = coursename;
		this.courseid = courseid;
		this.maxstudents = maxstudents;
		this.currentstudents = currentstudents;
		this.instructor = instructor;
		this.coursesection = coursesection;
		this.courselocation = courselocation;
	}
	
	public void setcoursename(String coursename) {
		this.coursename = coursename;
	}
	
	public String getcoursename() {
		return coursename;
	}
	
	public void setcourseid(String courseid) {
		this.courseid = courseid;
		
	}
	
	public String getcourseid() {
		return courseid;
	}
	
	public int getmaxstudents() {
		return maxstudents;
	
	}
	
	public void setmaxstudents(int maxstudents) {
		this.maxstudents = maxstudents;
	}
	
	public int getcurrentstudents() {
		return currentstudents;
	}
	
	public void setcurrentstudents(int currentstudents) {
		this.currentstudents = currentstudents;
	}
	
	public String getinstructor() {
		return instructor;
	}
	
	public void setinstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public int getcoursesection() {
		return coursesection;
	}
	
	public void setcoursesection(int coursesection) {
		this.coursesection = coursesection;
	}
	
	public String getcourselocation() {
		return courselocation;
	}
	
	public void setcourselocation(String courselocation) {
		this.courselocation = courselocation;
	}
	
	public void setregisterstudent(ArrayList<Student> registerstudent) {
		Courses.registerstudent = registerstudent;
	}
	
	public ArrayList<Student> getregisterstudent(){
		return registerstudent;
	}
	
	public void setcourselist(ArrayList<Courses> courselist) {
		Courses.courselist = courselist;
	}
	
	public ArrayList<Courses> getcourselist(){
		return courselist;
	}
	
	
	public String printallinfo() {
		System.out.println("Course: " + coursename + "\n" + "Course id: " + courseid + "\n" + "Maximum # of students: "
				+ maxstudents + "\n" + "Current of students: " + currentstudents + "\n" + "Instructor: " + instructor + 
				"\n" + "Course section: " + coursesection + "\n" + "Location: "
				+ courselocation);
		String result = "Course: " + coursename + "\n" + "Course id: " + courseid + "\n" + "Maximum # of students: "
				+ maxstudents + "\n" + "Current of students: " + currentstudents + "\n" + "Instructor: " + instructor + 
				"\n" + "Course section: " + coursesection + "\n" + "Location: "
				+ courselocation;
		return result;
		
	}
	
	//we use this method to seralize object
	public static void seralization() {
		try {
			FileOutputStream fos = new FileOutputStream("MyUniversityCourses.csv");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(courselist);
			oos.close();
			fos.close();
		}
		
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	//we use this method to deseralize object
	@SuppressWarnings("unchecked")
	public static void deseralization() {
		try {
			FileInputStream fis = new FileInputStream("MyUniversityCourses.csv");
			ObjectInputStream ois = new ObjectInputStream(fis);
			courselist = (ArrayList<Courses>)ois.readObject();
			ois.close();
			fis.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch(ClassNotFoundException c) {
			System.out.println("Class not found.");
			c.printStackTrace();
			return;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		Admin admin1 = new Admin();
		
		deseralization();
		String fileName = "Users/wendell/Desktop/MyUniversityCourses.csv";
	
		File file = new File(fileName);
		@SuppressWarnings("resource")
		String input = new Scanner(file).useDelimiter("\\A").next();

		StringTokenizer strTokens = new StringTokenizer(input, ",\n");

		int count = 0;
		
		while (strTokens.hasMoreTokens()) {
			if (count > 7) {
				String courseName = strTokens.nextToken();
				String courseID = strTokens.nextToken();
				String test = strTokens.nextToken();
				String trimmedTest = test.replace(" ", "");
				int maxStudents = Integer.parseInt(trimmedTest);
				String test1 = strTokens.nextToken();
				String trimmedTest1 = test1.replace(" ", "");
				int currentStudents = Integer.parseInt(trimmedTest1);
				strTokens.nextToken();
				String instructorName = strTokens.nextToken();
				String test2 = strTokens.nextToken();
				String trimmedTest2 = test2.replace(" ", "");
				int courseSection = Integer.parseInt(trimmedTest2);
				String courseLocation = strTokens.nextToken();

				// creates a course list from the elements found
				Courses c = new Courses(courseName, courseID, maxStudents, currentStudents, instructorName,
						courseSection, courseLocation);
				courselist.add(c);
				count++;
			} else {
				strTokens.nextToken();
				count++;
			}
		}
		
				
				
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Welcome to the system!");
				System.out.println("enter '1' to login as an admin.");
				System.out.println("enter '2' to login as a student.");
				System.out.println("enter '3' to exit.");
				String result = in.readLine();
				
				while(result != "1" && result != "2" && result != "3") {
					System.out.println("Error! Please try again.");
					System.out.println("enter '1' to login as an admin.");
					System.out.println("enter '2' to login as a student.");
					System.out.println("enter '3' to exit.");
					result = in.readLine();
				}
				
				if(result == "1") {
					System.out.println("enter the admin's name.");
					String adminName = in.readLine();
					System.out.println("enter the admin's password.");
					String adminPassword = in.readLine();
					
					while(adminName != "admin" || adminPassword != "admin001") {
						System.out.println("Error! Please try again!");
						System.out.println("enter the admin's name.");
						adminName = in.readLine();
						System.out.println("enter the admin's password.");
						adminPassword = in.readLine();
					}
					
					System.out.println("You have logged in as an admin.");
					System.out.println("enter '1' if you want to see reports.");
					System.out.println("enter '2' if you want to see course management.");
					System.out.println("enter '3' if you want to exit.");
					String result1 = in.readLine();
					
					while(result1 != "1" && result1 != "2" && result1 != "3") {
						System.out.println("Error! Please try again.");
						System.out.println("enter '1' if you want to see reports.");
						System.out.println("enter '2' if you want to see course management.");
						System.out.println("enter '3' if you want to exit.");
						result1 = in.readLine();
					}
					
					if(result1 == "1") {
						System.out.println("Welcome to reports.");
						System.out.println("enter '1' if you want to view all courses.");
						System.out.println("enter '2' if you want to view all full courses.");
						System.out.println("enter '3' if you want to write a file for full courses.");
						System.out.println("enter '4' if you want to view the names of students register in a specific course.");
						System.out.println("enter '5' if you want to view the list of courses student registered.");
						System.out.println("enter '6' if you want to sort courses.");
						System.out.println("enter '7' if you want to exit");
						String result2 = in.readLine();
						
						if(result2 == "1") {
							admin1.ViewAllCourses();
						}
						else if(result2 == "2") {
							admin1.ViewFullCourses();
						}
						else if(result2 == "3") {
							admin1.FileForFullCourses();
						}
						else if(result2 == "4") {
							admin1.ViewRegisterStudents();
						}
						else if(result2 == "5") {
							admin1.ViewRegisterCourses();
						}
						else if(result2 == "6") {
							admin1.SortCourses();
						}
						else {
							System.out.println("Thank you for visiting");
							seralization();
						}				
					}
					else if(result1 == "2") {
						System.out.println("Welcome to course management.");
						System.out.println("enter '1' if you want to create a new course.");
						System.out.println("enter '2' if you want to delete a course.");
						System.out.println("enter '3' if you want to edit a course.");
						System.out.println("enter '4' if you want to display information for a course.");
						System.out.println("enter '5' if you want to register a student.");
						System.out.println("enter '6' if you want to exit.");
						String result2 = in.readLine();
						
						if (result2 == "1") {
							admin1.createcourse();
						}
						else if(result2 == "2") {
							admin1.deletecourse();
						}
						else if(result2 == "3") {
							admin1.editcourse();
						}
						else if(result2 == "4") {
							admin1.displaycourse();
						}
						else if(result2 == "5") {
							admin1.registerforstudent();
						}
						else {
							System.out.println("Thank you for visiting.");
							seralization();
						}
				}
				else if(result == "2") {
					System.out.println("enter your first name.");
					String firstName = in.readLine();
					System.out.println("enter your last name.");
					String lastName = in.readLine();
					Student students = new Student(firstName, lastName);
					
					//error situation
					for(int i = 0; i < Admin.studentobject.size(); i++) {
						if((Admin.studentobject.get(i).getfirstname() != firstName)
							&& (Admin.studentobject.get(i).getlastname() != lastName)
							&& (i == Admin.studentobject.size()-1)) {
							System.out.println("You are not on the student list.");
						}
						else {
							break;
						}					
					}
					if((students.getusername() == null) || (students.getpassword() == null)) {
						System.out.println("set up your all usernames and passwords.");
						System.out.println("set your username.");
						String userName = in.readLine();
						System.out.println("set your password.");
						String password = in.readLine();
						for(int i = 0; i < Admin.studentobject.size(); i++) {
						  if((Admin.studentobject.get(i).getfirstname() == firstName)
								&& (Admin.studentobject.get(i).getlastname() == lastName)){
							  Admin.studentobject.get(i).setusername(userName);
							  Admin.studentobject.get(i).setpassword(password);
							  System.out.println("You have set your username and password.");
						  }
							
						}
						
					}
					
					System.out.println("enter your username.");
					String Username = in.readLine();
					System.out.println("enter your password.");
					String Password = in.readLine();
					boolean judgement = false;
					while(judgement) {
					  students.setusername(Username);
					  students.setpassword(Password);
					    if(Admin.studentobject.contains(students)) {
						  judgement = true;
						
					    }
					    else {
						  System.out.println("Please try again.");
					    }
				    }
				
					System.out.println("You have logged in as as a student.");
					System.out.println("enter '1' if you want to view all courses.");
					System.out.println("enter '2' if you want to view all courses that are not full.");
					System.out.println("enter '3' if you want to register on a course.");
					System.out.println("enter '4' if you want to withdraw from a course.");
					System.out.println("enter '5' if you want to view all courses that current student registered in");
					System.out.println("enter '6' if you want to exit.");
					String result3 = in.readLine();
					
					if(result3 == "1") {
						students.viewallcourses();
					}
					else if(result3 == "2") {
						students.viewnotfullcourses();
					}
					else if(result3 == "3") {
						students.registercourse();
					}
					else if(result3 == "4"){
						students.withdrawcourse();
					}
					else if(result3 == "5") {
						students.viewregistercourses();
					}
					else {
						System.out.println("Thank you for visiting.");
						seralization();
					}
						
						
				  }
					
			    }
				else {
					System.out.println("Thank you for visiting.");
					seralization();
				}
				
				
				
		
	}
	

}
