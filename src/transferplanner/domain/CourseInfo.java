package transferplanner.domain;

import java.util.Objects;

/**
 * A completed class that represents a course information
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 * @version 1.0
 */
public class CourseInfo {

    // fields
    private String name;
    private int credit;
    private int courseID;

    /**
     * Full constructor
     *
     * @param courseID course identity
     * @param name course name
     * @param credit number of credit
     */
    public CourseInfo(int courseID, String name, int credit) {
        this.name = name;
        this.credit = credit;
        this.courseID = courseID;
    }

    /**
     * Gets course name
     *
     * @return name course name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets number of credit for each course
     *
     * @return credit number of credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * Gets course identity
     *
     * @return courseID course identity
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * Sets course identity
     *
     * @param courseID given course identity
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * Display the plan
     *
     * @return name, credit name and credit of course
     */
    @Override
    public String toString() {
        return name + ": " + credit + " credits";
    }

    /**
     * Equals method
     *
     * @param c
     * @return
     */
    public boolean equals(CourseInfo c) {
        if (c.getName().equals(this.name) && c.getCredit() == this.credit) {
            return true;
        }
        return false;
    }

}
