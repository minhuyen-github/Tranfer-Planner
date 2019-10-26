package transferplanner.domain;

/**
 * A completed class that represent student information
 * 
 * @author Uyen Hoang
 * @author Phuong Tran
 * @version 1.0
 */
public class Student {
    // fields
    private String name;
    private int studentID;
    
    /**
     * Full constructor
     * 
     * @param id student ID
     * @param name student name
     */
    public Student(int id, String name) {
        this.name = name;
        studentID = id;
    }

    /**
     * Gets student name
     * @return name student name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets student ID
     * 
     * @return studentID student ID
     */
    public int getStudentID() {
        return studentID;
    }
    
}
