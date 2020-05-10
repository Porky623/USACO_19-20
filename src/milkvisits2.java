import java.util.*;
import java.io.*;
public class milkvisits2 {
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
		precomputeSparseMatrix(parent,N,N);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int lca = lca(depth,tree,parent,N,a,b);
			boolean work = false;
			if(cows[lca-1]==c) {
				out.print(1);
				continue;
			}
			int cur=a;
			while(cur!=lca) {
				if(cows[cur-1]==c)
					work=true;
				cur=parent[cur][0];
			}
			if(work) {
				out.print(1);
				continue;
			}
			cur=b;
			while(cur!=lca) {
				if(cows[cur-1]==c)
					work=true;
				cur=parent[cur][0];
			}
			if(work)
				out.print(1);
			else
				out.print(0);
		}
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
	static void precomputeSparseMatrix(int[][] parent, int N, int n) 
	{ 
	    for (int i=1; i<N; i++) 
	    { 
	        for (int node = 1; node <= n; node++) 
	        { 
	            if (parent[node][i-1] != -1) 
	                parent[node][i] = 
	                    parent[parent[node][i-1]][i-1]; 
	        } 
	    } 
	} 
	static int lca(int[] depth, ArrayList<ArrayList<Integer>> tree, int[][] parent, int N, int u, int v) 
	{ 
	    if (depth[v] < depth[u]) {
	    	int temp=u;
	    	u=v;
	    	v=temp;
	    }
	  
	    int diff = depth[v] - depth[u]; 
	  
	    for (int i=0; i<N; i++) 
	        if (((diff>>>i)&1)==1) 
	            v = parent[v][i]; 
	  
	    if (u == v) 
	        return u; 
	  
	    for (int i=N-1; i>=0; i--) 
	        if (parent[u][i] != parent[v][i]) 
	        { 
	            u = parent[u][i]; 
	            v = parent[v][i]; 
	        } 
	  
	    return parent[u][0]; 
	} 
}