import java.util.*;
import java.io.*;
public class threesum {
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("threesum.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		HashMap<Integer,HashSet<Integer>> inArr = new HashMap<>();
		int[][] ref = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				ref[i][j]=ref[i-1][j];
			}
			for(int j=0; j<i; j++) {
				int val=arr[i]+arr[j];
				if(inArr.containsKey(-val)) {
					for(int k:inArr.get(-val)) {
						if(k>j) {
							ref[i][j]++;
						}
					}
				}
			}
			if(!inArr.containsKey(arr[i]))
				inArr.put(arr[i], new HashSet<>());
			inArr.get(arr[i]).add(i);
		}
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int num=0;
			for(int j=a; j<=b; j++) {
				num+=ref[b][j];
			}
			out.println(num);
		}
		out.close();
	}
}
