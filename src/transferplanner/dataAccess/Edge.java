package transferplanner.dataAccess;

/**
 * This class represents the edge.
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 */
public class Edge {

    public int u; // Starting vertex of the edge
    public int v; // Ending vertex of the edge

    /**
     * Construct an edge.
     *
     * @param u starting vertex of the edge
     * @param v ending vertex of the edge
     */
    public Edge(int u, int v) {
        this.v = v;
        this.u = u;
    }

    /**
     * Check to see if this edge is equal to the other.
     * @param o the other edge.
     * @return true if they are equal, false if not.
     */
    @Override
    public boolean equals(Object o) {
        return u == ((Edge) o).u && v == ((Edge) o).v;
    }

}
