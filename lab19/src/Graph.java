import java.util.*;

public class Graph {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;
    int[] distTo;
    int[] edgeTo;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        this.vertexCount = numVertices;
    }

    /* Adds a directed Edge (V1, V2) to the graph. That is, adds an edge
       in ONE directions, from v1 to v2. */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /* Adds an undirected Edge (V1, V2) to the graph. That is, adds an edge
       in BOTH directions, from v1 to v2 and from v2 to v1. */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /* Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        for(Edge e : adjLists[v1]) {
            if(e.to == v2) {
                e.weight = weight;
                return;
            }
        }
        adjLists[v1].add(new Edge(v1, v2, weight));
    }

    /* Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        for(Edge e1 : adjLists[v1]) {
            if(e1.to == v2) {
                e1.weight = weight;
                for(Edge e2 : adjLists[v2]) {
                    if(e2.to == v1) {
                        e2.weight = weight;
                        return;
                    }
                }
            }
        }
        adjLists[v1].add(new Edge(v1, v2, weight));
        adjLists[v2].add(new Edge(v2, v1, weight));
    }

    /* Returns true if there exists an Edge from vertex FROM to vertex TO.
       Returns false otherwise. */
    public boolean isAdjacent(int from, int to) {
        for(Edge e : adjLists[from]) {
            if(e.to == to)
                return true;
        }
        return false;
    }

    /* Returns a list of all the vertices u such that the Edge (V, u)
       exists in the graph. */
    public List<Integer> neighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for(Edge e : adjLists[v]) {
            neighbors.add(e.to);
        }
        return neighbors;
    }
    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        int count = 0;
        for(List<Edge> vertices : adjLists) {
            for(Edge e : vertices) {
                if(e.to == v) {
                    count++;
                }
            }
        }
        return count;
    }

    /* Returns a list of the vertices that lie on the shortest path from start to stop. 
    If no such path exists, you should return an empty list. If START == STOP, returns a List with START. */
    public ArrayList<Integer> shortestPath(int start, int stop) {
        Comparator<Integer> vc = new VertexComparator();
        PriorityQueue<Integer> fringe = new PriorityQueue<>(vc);
        Set<Integer> visited = new HashSet<>();
        distTo = new int[vertexCount];
        edgeTo = new int[vertexCount];

        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        fringe.add(start);

        while(!fringe.isEmpty()) {
            int curr = fringe.poll();
            if(visited.contains(curr))
                continue;
            visited.add(curr);
            if(curr == stop) {
                break;
            }
            for(Edge e : adjLists[curr]) {
                if(visited.contains(e.to)) {
                    continue;
                }
                int pathLength = distTo[curr] + e.weight;
                if(distTo[e.to] == Integer.MAX_VALUE || pathLength < distTo[e.to]) {
                    distTo[e.to] = pathLength;
                    edgeTo[e.to] = curr;
                    fringe.add(e.to);
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();

        if(distTo[stop] == Integer.MAX_VALUE) {
            return path;
        }

        int next = stop;
        path.add(next);

        while(next != start) {
            next = edgeTo[next];
            path.add(next);
        }

        Collections.reverse(path);
        return path;
    }

    private class VertexComparator implements Comparator<Integer> {
        public int compare(Integer v1, Integer v2) {
            return distTo[v1] - distTo[v2];
        }
    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

        public int to() {
            return this.to;
        }

    }

}