import java.util.*;
import java.io.*;
public class cowmbat3 {
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("cowmbat.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String S = f.readLine().trim();
		int[][] dist = new int[M][M];
		//Letter, First Position
		int[][] C = new int[M][N];
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
		for(int j=0; j<M; j++) {
			C[j][N-1]=dist[S.charAt(N-1)-'a'][j];
		}
		for(int i=N-2; i>=0; i--) {
			for(int j=0; j<M; j++) {
				C[j][i]=C[j][i+1]+dist[S.charAt(i)-'a'][j];
			}
		}
		int[] bestC = new int[N];
		for(int i=0; i<N;i++) {
			for(int j=0; j<M; j++) {
				if(bestC[i]==0||bestC[i]>C[j][i])
					bestC[i]=C[j][i];
			}
		}
		int[][] dp = new int[N][N];
		for(int i=0; i<N; i++) {
			dp[0][i]=bestC[i];
		}
		for(int i=1; i<N; i++) {
			compute(bestC, i,dp,S,N,M,K,0,N-1,0,N-1);
		}
		out.println(dp[N-1][N-1]);
		out.close();
		f.close();
	}
	//start and end inclusive
	static void compute(int[] C, int i, int[][] dp, String S, int N, int M, int K, int l, int r, int optl, int optr) {
		if(l>r)
			return;
		int mid = (l+r)/2;
		int best1 = -1;
		int best2 = -1;
		for(int k=optl; k<=Math.min(mid,optr); k++) {
			if(r-k+1<K)
				continue;
			int val = dp[i-1][k]+C[k]-C[mid];
			if(best1<0||best1>val||best1==val&&best2>k) {
				best1=val;
				best2=k;
			}
		}
		dp[i][mid]=best1;
		int opt = best2;
		
		compute(C,i,dp,S,N,M,K,l,mid-1,optl,opt);
		compute(C,i,dp,S,N,M,K,mid+1,r,opt,optr);
	}
}
