import java.util.*;
import java.io.*;
public class threesum2 {
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
		HashMap<Integer,Integer> inArr = new HashMap<>();
		long[][] check = new long[N][N];
		long[][] ref = new long[N][N];
		int index = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				ref[i][j]=ref[i-1][j];
			}
			for(int j=0; j<i; j++) {
				int val=arr[i]+arr[j];
				if(inArr.containsKey(-val)) {
					if(i>0) {
						ref[i][j]+=check[inArr.get(-val)][i-1]-check[inArr.get(-val)][j];
					}
				}
			}
			for(int key=0; key<index; key++) {
				check[key][i]=check[key][i-1];
			}
			if(!inArr.containsKey(arr[i])) {
				inArr.put(arr[i], index);
				index++;
			}
			check[inArr.get(arr[i])][i]++;
		}
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			long num=0;
			for(int j=a; j<=b; j++) {
				num+=ref[b][j];
			}
			out.println(num);
		}
		out.close();
	}
}
