package transferplanner.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A completed class that retrieve the course information
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 * @version 1.0
 */
public class Courses {

    /**
     * Get the courses from the text file.
     *
     * @return the list of courses.
     */
    public ArrayList<CourseInfo> retrieveFile() {
        // field
        ArrayList<CourseInfo> courses = new ArrayList<>();
        try {
            // read the file from the external file
            File file = new File("src/transferplanner/domain/Courses.txt");
            Scanner scanner = new Scanner(file);
            int count = 0;
            // use while loop to take input
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                CourseInfo c;
                // add course with 3 credits
                if (input.equals("HLTH 100") || input.equals("HLTH 150")
                        || input.equals("ENGR 100")) {
                    c = new CourseInfo(count, input, 3);
                    courses.add(c);
                } else {
                    // add course with 5 credits
                    c = new CourseInfo(count, input, 5);
                    courses.add(c);
                }
                // keep track 
                count++;
            }
        } catch (FileNotFoundException ex) {
            // send out the message if can not find or read the file
            System.out.println("Cannot find/read this file");
        }
        return courses;
    }
}
