import java.util.*;
import java.io.*;
public class cowmbat2 {
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("cowmbat.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String S = f.readLine().trim();
		int[][] dist = new int[M][M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=0; j<M; j++) {
				dist[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int k=0; k<M; k++) {
			boolean changed=false;
			for(int i=0; i<M; i++) {
				for(int j=0; j<M; j++) {
					int ne = dist[i][k]+dist[k][j];
					if(ne<dist[i][j]) {
						dist[i][j]=ne;
						changed=true;
					}
				}
			}
		}
		out.println(getMin(dist,S,N,M,K,0,N-1));
		out.close();
		f.close();
	}
	//start and end inclusive
	static int getMin(int[][] dist,String S, int N, int M, int K, int start, int end) {
		if(end<start)
			return 0;
		if(end-start+1<K)
			return -1;
		int ret=-1;
		if(end-start+1<2*K) {
			int min=-1;
			for(int let = 0; let<M; let++) {
				int sum = 0;
				for(int i=start; i<=end; i++) {
					sum+=dist[S.charAt(i)-'a'][let];
				}
				if(min<0||min>sum)
					min=sum;
			}
			return min;
		}
		int middle = (start+end)/2;
		for(int newStart = middle-K/2; newStart<middle-K/2+K; newStart++) {
			if(newStart<start||newStart+1>end)
				return -1;
			int a = getMin(dist,S,N,M,K,start,newStart);
			if(a<0)
				continue;
			int b = getMin(dist,S,N,M,K,newStart+1,end);
			if(b<0)
				continue;
			int min=a+b;
			if(ret<0||ret>min)
				ret=min;
		}
		return ret;
	}
}
