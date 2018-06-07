import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.StringBuilder;
import java.util.Arrays;

public class GCGraph2{
	private List<String> names;
	// private List<String> edges;
	private int[][] matrix;

	public GCGraph2(){
		names = new ArrayList<>();
		// edges = new ArrayList<>();
	}

	private void addName(String n){
		names.add(n);
		// updateMatrix();
	}
	public void addNames(String n){
		for(String name: n.split(",")){
			names.add(name);	
//			addName
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

		// for(int[] row : matrix){
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

	@Override
	public String toString(){
		return names.toString();
	}
	public static void main(String[] args) {

		GCGraph2 g = new GCGraph2();

		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a comma separated list of Vertex names:");

		// g.addNames(kb.nextLine());
		g.addNames("v1,v2,v3,v4");
		
		System.out.println("Please enter a comma separated list of Edges in {vi, vk} form where vi & vk \n were both previously entered.\n");
		
		// g.addEdges(kb.nextLine());
		g.addEdges("{v1,v2},{v2,v3},{v3,v3},{v1,v2}");
		
		// System.out.println(g);

		System.out.println(g.matrixToString());
		
		g.printEdgeFunction();

		kb.close();
	}
}