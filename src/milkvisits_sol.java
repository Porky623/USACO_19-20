import java.util.*;
import java.io.*;
public class milkvisits_sol {
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] cows = new int[N];
		for(int i=0; i<N; i++) {
			cows[i]=Integer.parseInt(st.nextToken());
		}
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			tree.add(new ArrayList<>());
		}
		int[] depth = new int[N+1];
		int[][] parent = new int[N+1][N+1];
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		depth[0]=0;
		dfs(depth,tree,parent,1,0);
		out.close();
	}
	static void dfs(int[] depth, ArrayList<ArrayList<Integer>> tree, int[][] parent, int cur, int prev) {
		depth[cur] = depth[prev] + 1; 
	    parent[cur][0] = prev; 
	    for (int i=0; i<tree.get(cur).size(); i++) 
	    { 
	        if (tree.get(cur).get(i) != prev) 
	            dfs(depth,tree,parent,tree.get(cur).get(i), cur); 
	    } 
	}
}