import java.util.*;
import java.io.*;
public class time {
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("time.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] cityworth = new int[N];
		int maxVal = 0;
		st = new StringTokenizer(f.readLine());
		for(int i=0; i<N; i++) {
			cityworth[i]=Integer.parseInt(st.nextToken().trim());
			maxVal = Math.max(maxVal, cityworth[i]);
		}
		HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!map.containsKey(b-1)) {
				map.put(b-1, new HashSet<Integer>());
			}
			map.get(b-1).add(a-1);
		}
		int[] max = new int[N];
		int[] temp = new int[N];
		int maxEarning = 0;
		for(int i=1; i<501; i++) {
			int t = C*i*i;
			for(int j=0; j<N; j++) {
				if(map.containsKey(j)) {
					int mx=0;
					boolean accessible = false;
					for(int x: map.get(j)) {
						if(max[x]>0||x==0)
							accessible=true;
						mx = Math.max(mx, max[x]);
					}
					if(accessible) {
						temp[j]=mx+cityworth[j];
						if(j==0)
						maxEarning=Math.max(maxEarning, temp[j]-t);
					}
				}
			}
			max=temp;
			temp=new int[N];
			if(maxVal<=C*(2*i+1)) {
				out.println(maxEarning);
				out.close();
				System.exit(0);
			}
		}
	}
}
