package transferplanner.dataAccess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import transferplanner.domain.CourseInfo;
import transferplanner.domain.Courses;

/**
 * This is the class that create a graph based on the course, and a sort to
 * create an individual plan.
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 */
public class Planner {

    private Courses recs = new Courses();
    private DAG graph = new DAG();
    public ArrayList<CourseInfo> finalPlan;
    public ArrayList<CourseInfo> c = recs.retrieveFile();
    private ArrayList<Edge> prereqs = new ArrayList<Edge>();

    /**
     * This is the graph of all courses.
     */
    public void generalPlan() {
        //create a general plan which start with ENGL 099 and MATH 087.

        //ENGL 099 and its higher courses.
        for (int i = 0; i < 2; i++) {
            prereqs.add(new Edge(c.get(i).getCourseID(), c.get(i + 1).getCourseID()));
        }
        for (int i = 12; i <= 15; i++) {
            prereqs.add(new Edge(c.get(1).getCourseID(), c.get(i).getCourseID()));
        }
        //MATH 087 and its higher courses.
        for (int i = 16; i < 19; i++) {
            prereqs.add(new Edge(c.get(i).getCourseID(), c.get(i + 1).getCourseID()));
        }
        prereqs.add(new Edge(c.get(20).getCourseID(), c.get(21).getCourseID()));
        for (int i = 19; i < 26; i++) {
            prereqs.add(new Edge(c.get(i).getCourseID(), c.get(i + 1).getCourseID()));
        }
        //PHYS& 221 and its higher classes.
        prereqs.add(new Edge(c.get(1).getCourseID(), c.get(27).getCourseID()));
        prereqs.add(new Edge(c.get(16).getCourseID(), c.get(27).getCourseID()));
        for (int i = 27; i < 29; i++) {
            prereqs.add(new Edge(c.get(i).getCourseID(), c.get(i + 1).getCourseID()));
        }
        //CS 115 and its higher classes.
        for (int i = 30; i < 33; i++) {
            prereqs.add(new Edge(c.get(i).getCourseID(), c.get(i + 1).getCourseID()));
        }
        //Electives
        for (int i = 34; i < 36; i++) {
            prereqs.add(new Edge(c.get(i).getCourseID(), c.get(i + 1).getCourseID()));
        }
        graph = new DAG(c, prereqs);
    }

    /**
     * Reconstruct the graph if the student has already taken or placed courses.
     *
     * @param taken a list of taken courses.
     * @param placed a list of placed courses.
     */
    private void refineGraph(ArrayList<CourseInfo> taken, ArrayList<CourseInfo> placed) {
        for (int i = 0; i < taken.size(); i++) {
            if (graph.getVertices().contains(taken.get(i))) {
                int index = graph.getVertices().indexOf(taken.get(i));
                refine(graph.getVertex(index), prereqs);
            }
        }
        for (int i = 0; i < placed.size(); i++) {
            if (graph.getVertices().contains(placed.get(i))) {
                int index = graph.getVertices().indexOf(placed.get(i));
                refine1(graph.getVertex(index), prereqs);
            }
        }
        for (int i = 0; i < c.size(); i++) {
            for (int b = 0; b < prereqs.size(); b++) {
                if (prereqs.get(b).u == c.get(i).getCourseID()) {
                    prereqs.get(b).u = i;
                } else if (prereqs.get(b).v == c.get(i).getCourseID()) {
                    prereqs.get(b).v = i;
                }
            }
            c.get(i).setCourseID(i);
        }

        graph = new DAG(c, prereqs);
    }

    /**
     * Reconstruct the graph if the student has already taken courses.
     *
     * @param vert the specified courses
     * @param pre the list of prerequisites
     */
    protected void refine(CourseInfo vert, ArrayList<Edge> pre) {
        for (int i = 0; i < pre.size(); i++) {
            CourseInfo v = null;
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).getCourseID() == pre.get(i).u) {
                    v = c.get(j);
                }
            }
            if (pre.get(i).v == vert.getCourseID()) {
                pre.remove(i);
                c.remove(vert);
                if (c.contains(v)) {
                    c.remove(v);
                }
                for (int a = 0; a < c.size(); a++) {
                    for (int b = 0; b < pre.size(); b++) {
                        if (pre.get(b).u == c.get(i).getCourseID()) {
                            pre.get(b).u = i;
                        } else if (pre.get(b).v == c.get(i).getCourseID()) {
                            pre.get(b).v = i;
                        }
                    }
                    c.get(i).setCourseID(i);
                }
                refine(v, pre);
            }
        }
        if (c.contains(vert)) {
            c.remove(vert);
        }
    }

    /**
     * Reconstruct the graph if the student has already placed courses.
     *
     * @param vert the specified courses
     * @param pre the list of prerequisites
     */
    protected void refine1(CourseInfo vert, ArrayList<Edge> pre) {
        for (int i = 0; i < pre.size(); i++) {
            CourseInfo v = null;
            for (int j = 0; j < c.size(); j++) {
                int u = c.get(j).getCourseID();
                if (u == pre.get(i).u) {
                    v = c.get(j);
                }
            }
            if (pre.get(i).v == vert.getCourseID()) {
                pre.remove(i);
                if (c.contains(v)) {
                    c.remove(v);
                }
                for (int a = 0; a < c.size(); a++) {
                    for (int b = 0; b < pre.size(); b++) {
                        if (pre.get(b).u == c.get(i).getCourseID()) {
                            pre.get(b).u = i;
                        } else if (pre.get(b).v == c.get(i).getCourseID()) {
                            pre.get(b).v = i;
                        }
                    }
                    c.get(i).setCourseID(i);
                }
                refine1(v, pre);
            }
        }
        if (c.contains(vert)) {
            c.remove(vert);
        }
    }

    /**
     * Sort the graph.
     *
     * @param taken a list of taken courses.
     * @param placed a list of placed courses.
     */
    public void topologicalSort(ArrayList<CourseInfo> taken, ArrayList<CourseInfo> placed) {
        //sort the list using the courses' ID to create an individual plan.
        if (taken.size() > 0 || placed.size() > 0) {
            refineGraph(taken, placed);
        }

        //create an array to store indegrees of all vertices.
        int indegree[] = new int[graph.getSize()];

        //calculate the degree of each vertex.        
        for (int i = 0; i < indegree.length; i++) {
            ArrayList<Integer> t = graph.getAdjacentVertices(i);
            for (int degree : t) {
                indegree[degree]++;
            }
        }

        //create a queue and enqueue all vertices with indegree 0.
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        //keep track of the visited vertices.
        int visited = 0;

        //create a list to store topological ordering of the graph.
        ArrayList<Integer> sorted = new ArrayList<>();

        while (!queue.isEmpty()) {
            //extract front of queue and add it to the list. 
            int i = queue.poll();
            sorted.add(i);

            //iterate through all its adjacent vertices of dequeued vertex u and 
            //decrease their in-degree by 1.
            for (int j = 0; j < graph.getSize(); j++) {
                ArrayList<Integer> t = graph.getAdjacentVertices(j);
                for (int u : t) {
                    // If in-degree becomes zero, add it to queue. 
                    if (--indegree[u] == 0) {
                        queue.offer(u);
                    }
                }
            }
            visited++;
        }

        //check if the graph exists a cycle.
        if (visited++ != (graph.getSize())) {
            //- taken.size())) {
            System.out.println("There is an error!");
        }

        //get the course using their sorted ID.
        ArrayList<CourseInfo> p = new ArrayList<>();
        for (int i = 0; i < sorted.size(); i++) {
            int index = sorted.get(i);
            p.add(c.get(index));
        }
        finalPlan = p;
    }

    /**
     * Return the size.
     *
     * @return the size.
     */
    public int size() {
        return graph.getSize();
    }

    /**
     * Get the specified course.
     *
     * @param index the position of the course in the list.
     * @return the course.
     */
    public CourseInfo get(int index) {
        return finalPlan.get(index);
    }

    /**
     * Return the sorted plan.
     *
     * @return the sorted plan.
     */
    public ArrayList<CourseInfo> plan() {
        return finalPlan;
    }
}
