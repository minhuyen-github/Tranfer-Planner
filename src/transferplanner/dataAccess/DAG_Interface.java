package transferplanner.dataAccess;

import java.util.ArrayList;
import java.util.List;
import transferplanner.domain.CourseInfo;

/**
 * This is an interface for the graph.
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 */
public interface DAG_Interface {

    /**
     * Return the number of vertices in the graph.
     *
     * @return the size of the graph.
     */
    public int getSize();

    /**
     * Return the vertices in the graph.
     *
     * @return the list of vertices.
     */
    public List<CourseInfo> getVertices();

    /**
     * Return the object for the specified vertex.
     *
     * @param index the index of the vertex.
     * @return the vertex at that index.
     */
    public CourseInfo getVertex(int index);

    /**
     * Return the index for the specified vertex object.
     *
     * @param v the specified vertex object.
     * @return the index of the vertex
     */
    public int getIndex(CourseInfo v);

    /**
     * Return the neighbors of the specified vertex.
     *
     * @param index the specified vertex
     * @return the list of neighbors.
     */
    public ArrayList<Integer> getAdjacentVertices(int index);

    /**
     * Return the degree for a specified vertex.
     *
     * @param v the specified vertex
     * @return the degree
     */
    public int getDegree(int v);

    /**
     * Add a vertex to the graph.
     *
     * @param vertex the vertex to be added
     * @return true if a vertex was added, false if not.
     */
    public boolean addVertex(CourseInfo vertex);

    /**
     * Add an edge to the graph.
     *
     * @param u the starting vertex.
     * @param v the ending vertex.
     * @return true if an edge was added, false if not.
     */
    public boolean addEdge(int u, int v);
}
