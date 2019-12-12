import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private final Digraph graph;

    public SAP(Digraph graph){
        this.graph = graph;
    }

    public int ancestor(int v, int w){
        BreadthFirstDirectedPaths pathsFromV = new BreadthFirstDirectedPaths(graph,v);
        BreadthFirstDirectedPaths pathsFromW = new BreadthFirstDirectedPaths(graph,w);
        int shortestPath = Integer.MAX_VALUE;
        int commonAncestor = -1;
        for( int vertex =0 ; vertex < graph.V(); vertex++){
            if(pathsFromV.hasPathTo(vertex) && pathsFromW.hasPathTo(vertex)){
                int dist = pathsFromV.distTo(vertex) + pathsFromW.distTo(vertex);
                if(dist < shortestPath){
                    shortestPath = dist;
                    commonAncestor = vertex;
                }
            }
        }
        return commonAncestor;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        BreadthFirstDirectedPaths pathsFromV = new BreadthFirstDirectedPaths(graph,v);
        BreadthFirstDirectedPaths pathsFromW = new BreadthFirstDirectedPaths(graph,w);
        int shortestPath = Integer.MAX_VALUE;
        int commonAncestor = -1;
        for( int vertex =0 ; vertex < graph.V(); vertex++){
            if(pathsFromV.hasPathTo(vertex) && pathsFromW.hasPathTo(vertex)){
                int dist = pathsFromV.distTo(vertex) + pathsFromW.distTo(vertex);
                if(dist < shortestPath){
                    shortestPath = dist;
                    commonAncestor = vertex;
                }
            }
        }
        return commonAncestor;
    }

    public int length(int v, int w){
            BreadthFirstDirectedPaths pathsFromV = new BreadthFirstDirectedPaths(graph,v);
            BreadthFirstDirectedPaths pathsFromW = new BreadthFirstDirectedPaths(graph,w);
            int shortestPath = Integer.MAX_VALUE;
            boolean hasCommonAncestor = false;
            for( int vertex =0 ; vertex < graph.V(); vertex++){
                if(pathsFromV.hasPathTo(vertex) && pathsFromW.hasPathTo(vertex)){
                    int dist = pathsFromV.distTo(vertex) + pathsFromW.distTo(vertex);
                    if(dist < shortestPath){
                        shortestPath = dist;
                        hasCommonAncestor = true;
                    }
                }
            }
            return hasCommonAncestor ? shortestPath :-1;
        }

    public int length(Iterable<Integer> v, Iterable<Integer> w){
        BreadthFirstDirectedPaths pathsFromV = new BreadthFirstDirectedPaths(graph,v);
        BreadthFirstDirectedPaths pathsFromW = new BreadthFirstDirectedPaths(graph,w);
        int shortestPath = Integer.MAX_VALUE;
        boolean hasCommonAncestor = false;
        for( int vertex =0 ; vertex < graph.V(); vertex++){
            if(pathsFromV.hasPathTo(vertex) && pathsFromW.hasPathTo(vertex)){
                int dist = pathsFromV.distTo(vertex) + pathsFromW.distTo(vertex);
                if(dist < shortestPath){
                    shortestPath = dist;
                    hasCommonAncestor = true;
                }
            }
        }
        return hasCommonAncestor ? shortestPath :-1;

    }


    public static void main(String[] args) {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
