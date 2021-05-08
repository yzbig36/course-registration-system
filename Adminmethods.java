

import java.io.*;
import java.util.ArrayList;


public interface Adminmethods {
	public void createcourse() throws IOException;
	public void deletecourse() throws IOException;
	public void editcourse() throws IOException;
	public void displaycourse() throws IOException;
	public void registerforstudent() throws IOException;
	public void ViewAllCourses();
	public ArrayList<Courses> ViewFullCourses();
	public void FileForFullCourses() throws IOException;
	public void ViewRegisterStudents() throws IOException;
	public void ViewRegisterCourses() throws IOException;
	public void SortCourses();
	
	

}
