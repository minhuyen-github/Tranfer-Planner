package transferplanner.business;

import java.util.ArrayList;
import transferplanner.dataAccess.Planner;
import transferplanner.domain.CourseInfo;

/**
 * A completed business class used by the GUI to work with data
 * 
 * @author Uyen Hoang
 * @author Phuong Tran
 * @version 1.0
 */
public class PlannerManage {
    // field
    private Planner plan;

    /**
     * Full constructor
     */
    public PlannerManage() {
        plan = new Planner();
    }

    /**
     * Adjusts plan after adding course placed and course taken
     * 
     * @param alreadyTaken course taken
     * @param placed course placed
     */
    public void modifyPlan(ArrayList<CourseInfo> alreadyTaken, ArrayList<CourseInfo> placed) {
        // call the generalPlan method
        plan.generalPlan();
        // call the topologicalSort method
        plan.topologicalSort(alreadyTaken, placed);
    }

    /**
     * Gets plan
     * 
     * @return plan student plan
     */
    public ArrayList<CourseInfo> getPlan() {
        return plan.plan();
    }
    
    /**
     * Gets all courses
     * @return plan in total
     */
    public ArrayList<CourseInfo> getAllCourses() {
        return plan.c;
    }
}
