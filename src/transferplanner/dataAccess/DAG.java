package transferplanner.dataAccess;

import java.util.ArrayList;
import java.util.List;
import transferplanner.domain.CourseInfo;

/**
 * This is the class which construct a graph.
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 */
public class DAG implements DAG_Interface {

    protected List<CourseInfo> vertices = new ArrayList<>(); // Store vertices
    protected List<List<Edge>> adjacent = new ArrayList<>(); // Adjacency lists

    /**
     * Construct an empty graph.
     */
    public DAG() {
    }

    /**
     * Construct a graph with a list of vertices, and a list of edge objects.
     *
     * @param vertices list of vertices
     * @param edges list of edge objects
     */
    public DAG(List<CourseInfo> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }

        createAdjacencyLists(edges, vertices.size());
    }

    /**
     * Create a list for neighbors of each vertex.
     *
     * @param edges the edge object
     * @param numberOfVertices the total number of vertices
     */
    private void createAdjacencyLists(
            List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    @Override
    /**
     * Return the number of vertices in the graph.
     *
     * @return the size of the graph.
     */
    public int getSize() {
        return vertices.size();
    }

    @Override
    /**
     * Return the vertices in the graph.
     *
     * @return the list of vertices.
     */
    public List<CourseInfo> getVertices() {
        return vertices;
    }

    @Override
    /**
     * Return the object for the specified vertex.
     *
     * @param index the index of the vertex.
     * @return the vertex at that index.
     */
    public CourseInfo getVertex(int index) {
        return vertices.get(index);
    }

    /**
     * Clear the graph.
     */
    public void clear() {
        vertices.clear();
        adjacent.clear();
    }

    @Override
    /**
     * Add a vertex to the graph.
     *
     * @param vertex the vertex to be added
     * @return true if a vertex was added, false if not.
     */
    public boolean addVertex(CourseInfo vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacent.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add an edge to the graph.
     *
     * @param e the edge object.
     * @return true if an edge was added, false if not.
     */
    protected boolean addEdge(Edge e) {
        if (!adjacent.get(e.u).contains(e)) {
            adjacent.get(e.u).add(e);
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Add an edge to the graph.
     *
     * @param u the starting vertex.
     * @param v the ending vertex.
     * @return true if an edge was added, false if not.
     */
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    @Override
    /**
     * Return the index for the specified vertex object.
     *
     * @param v the specified vertex object.
     * @return the index of the vertex.
     */
    public int getIndex(CourseInfo v) {
        return vertices.indexOf(v);
    }

    /**
     * Return the neighbors of the specified vertex.
     *
     * @param index the specified vertex
     * @return the list of neighbors.
     */
    @Override
    public ArrayList<Integer> getAdjacentVertices(int index) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Edge e : adjacent.get(index)) {
            result.add(e.v);
        }
        return result;
    }

    @Override
    /**
     * Return the degree for a specified vertex.
     *
     * @param v the specified vertex
     * @return the degree
     */
    public int getDegree(int v) {
        return adjacent.get(v).size();
    }

}
