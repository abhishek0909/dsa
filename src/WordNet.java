import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WordNet{

    private final Digraph digraph;
    private final Map<String,Integer> nouns;
    private final Map<Integer,String> vertexSynsetMap;

    public static void main(String[] args){
        WordNet net = new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(net.isNoun("cotyloid_cavity"));
    }

    public String sap(String nounA, String nounB){
        if(Objects.isNull(nounA) || Objects.isNull(nounB))
            throw new IllegalArgumentException("One of the arguments is null");
        SAP sap = new SAP(digraph);
        String synset = null;
        if(isNoun(nounA) && isNoun(nounB)){
            int parent = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
            if(parent != -1) synset = vertexSynsetMap.get(parent);
        }
        return synset;
    }

    public int distance(String nounA, String nounB){
        if(Objects.isNull(nounA) || Objects.isNull(nounB))
            throw new IllegalArgumentException("One of the arguments is null");

        int distance = -1;
        SAP sap = new SAP(digraph);
        if(isNoun(nounA) && isNoun(nounB)){
           distance  = sap.length(nouns.get(nounA), nouns.get(nounB));
        }
        return distance;
    }


    public WordNet(String synsets, String hypernyms){
        int totalVertices = getNoLines(hypernyms);
        digraph = new Digraph(totalVertices);
        readHypernyms(hypernyms); //creates graph
        int nounCount = digraph.V();
        vertexSynsetMap = new HashMap<>(digraph.V());
        DirectedCycle cycles = new DirectedCycle(digraph);
        if(cycles.hasCycle())
            throw new IllegalArgumentException("Graph has cycles");
        nouns = new HashMap<>(nounCount);
        readSynsetsFile(synsets);
    }

    private void readSynsetsFile(String filename){
        In in = new In(filename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] split = line.split(",");
            int vertex = Integer.parseInt(split[0]);
            vertexSynsetMap.put(vertex,split[1]);
            String[] strings = split[1].split(" ");
            for(String str : strings){
                nouns.put(str,vertex);
            }
        }
    }

    public boolean isNoun(String word){
       return nouns.containsKey(word);
    }

    public Iterable<String> nouns(){
        return nouns.keySet();
    }


    private void readHypernyms(String filename) {

        In in = new In(filename);
            while (in.hasNextLine()) {
                String line = in.readLine();
                String[] split = line.split(",");
                for(int i = 1; i< split.length; i++){
                    digraph.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[i]));
                }
            }
    }

    private int getNoLines(String filename){
        In in = new In(filename);
        int noOfLines = 0;
        while (in.hasNextLine()) {
            in.readLine();
            noOfLines++;
        }
        return noOfLines;
    }

}
