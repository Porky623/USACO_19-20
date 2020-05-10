import java.util.*;
import java.io.*;
public class boards {
	private static int dist(int x1, int y1, int x2, int y2) {
		return y2-y1+x2-x1;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("boards.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[][] paths = new int[P+2][4];
		for(int i=1; i<=P; i++) {
			st = new StringTokenizer(f.readLine());
			paths[i][0]=Integer.parseInt(st.nextToken());
			paths[i][1]=Integer.parseInt(st.nextToken());
			paths[i][2]=Integer.parseInt(st.nextToken());
			paths[i][3]=Integer.parseInt(st.nextToken());
		}
		paths[P+1][0]=N;
		paths[P+1][1]=N;
		java.util.Arrays.sort(paths, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		    	if(a[0]==b[0])
		    		return Integer.compare(a[1], b[1]);
		        return Integer.compare(a[0], b[0]);
		    }
		});
		TreeMap<Integer,HashSet<Integer>> tree = new TreeMap<>();
		int[] dp = new int[P+2];
		for(int i=0; i<P+2; i++) {
			int temp = -1;
			Integer x = tree.lowerKey(paths[i][1]+1);
			while(x!=null) {
				for(int j:tree.get((int)x)) {
					if(paths[i][1]>=paths[j][3] && paths[i][0]>=paths[j][2]) {
						int val=dp[j]+dist(paths[j][2],paths[j][3],paths[i][0],paths[i][1]);
						if(temp<0)
							temp=val;
						else
							temp=Math.min(temp, val);
					}
				}
				x=tree.lowerKey(x);
			}
			if(!tree.containsKey(paths[i][1])) {
				tree.put(paths[i][1], new HashSet<Integer>());
			}
			tree.get(paths[i][1]).add(i);
			if(temp>=0)
				dp[i]=temp;
		}
		out.println(dp[P+1]);
		out.close();
	}
}