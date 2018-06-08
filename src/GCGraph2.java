import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GCGraph2{
	private List<String> names;
	private int[][] matrix;

	public GCGraph2(){
		names = new ArrayList<>();
	}

	public void addNames(String n){
		for(String name: n.split(",")){
			names.add(name);
		}
		updateMatrix();
	}

	public void addEdges(String n){
		String pattern = "\\{[a-zA-Z0-9]+,[a-zA-Z0-9]+\\}";
		Pattern p = Pattern.compile(pattern);

		Matcher matcher = p.matcher(n);
		while(matcher.find()){
			addEdge(n.substring(matcher.start(), matcher.end()));
		}
	}
	private void addEdge(String n){
		String[] s = n.substring(1,n.length()-1).split(",");
		int v1 = names.indexOf(s[0]);
		int v2 = names.indexOf(s[1]);
		if(v1 >= 0 && v2 >= 0){
			matrix[v1][v2] += 1;
			matrix[v2][v1] += 1;
		}

	}

	private void updateMatrix(){
		int n = names.size();
		int[][] newMatrix = new int[n][n];

		if(matrix != null){
			for(int i=0; i<matrix.length; i+=1){
				for(int j=0; j<matrix[i].length; j+=1){
					newMatrix[i][j] = matrix[i][j];
				}
			}
		}

		matrix = newMatrix;

	}

	public String matrixToString(){
		StringBuilder sb = new StringBuilder(" ");

		for(int i=0; i<names.size(); i+=1){
			sb.append(" " + names.get(i));
		}
		sb.append("\n");
		for(int i = 0; i<matrix.length; i+=1){
			sb.append(names.get(i));
			sb.append(Arrays.toString(matrix[i]));
			sb.append("\n");
		}

		return sb.toString();
	}

	public void printEdgeFunction(){
		int edgeCount = 1;
		System.out.println("Edge Endpoint function");
		for(int i=0; i<matrix.length; i+=1){
			for(int j=0; j<(i+1); j+=1){
				for(int k=0; k<matrix[i][j]; k+=1){
					k+= (i == j) ? 1 : 0;
					System.out.println("e" + edgeCount + ": {" + names.get(j) + "," + names.get(i) + "}");
					edgeCount+=1;
				}
			}
		}
	}

	public Map<String, Integer> vertexDegrees(){
		Map<String, Integer> m = new HashMap<>();

		for (int i =0; i<matrix.length; i+=1) {
			for (int j=0; j<matrix[i].length; j+=1) {
				String n = names.get(i);
				Integer v = m.get(n);
				if(v == null) v = 0;
				v+= matrix[i][j];
				m.put(n, v);

				}
			}

			return m;
		}


	public int graphDegree(){
		return vertexDegrees().values().stream().reduce(0, Integer::sum);
	}

	public boolean containsLoop(){
		for(int i=0; i<matrix.length; i+=1){
			if(matrix[i][i] > 0) return true;
		}
		return false;
	}

	public boolean containsIsolatedVertex(){
		return vertexDegrees().values().stream().anyMatch(v -> v == 0);
	}

	public boolean simpleGraph(){

		return !Arrays.stream(matrix).flatMapToInt(Arrays::stream).anyMatch(x -> x > 1);
	}

	@Override
	public String toString(){
		return names.toString();
	}
	public static void main(String[] args) {

		GCGraph2 g = new GCGraph2();

		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a comma separated list of Vertex names:");

		// Uncomment below to take input from std.in
		// I used "v1,v2,v3,v4" for testing.
		// g.addNames(kb.nextLine());

		g.addNames("v1,v2,v3,v4");

		System.out.println("Please enter a comma separated list of Edges in {vi, vk} form where vi & vk \n were both previously entered.\n");

		// Edges can also be taken from std.in
		// g.addEdges(kb.nextLine());
		
		g.addEdges("{v1,v2},{v2,v3},{v3,v1}");

		// System.out.println(g);

		System.out.println(g.matrixToString());
		g.printEdgeFunction();
		System.out.println("Vertex Degress:");
		g.vertexDegrees().forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println(g.vertexDegrees());

		System.out.println("Graph degree: " + g.graphDegree());
		System.out.println("Simple graph? :" + g.simpleGraph());
		System.out.println("Contains isolated vertex?: " + g.containsIsolatedVertex());

		g.addEdges("{v4,v4}");
		System.out.println("Added {v4, v4}.\n" + "Simple graph? :" + g.simpleGraph());
		System.out.println("Contains isolated vertex?: " + g.containsIsolatedVertex());

		kb.close();
	}
}
