import java.util.*;
import java.io.*;
public class cowmbat_sol {
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
			for(int i=0; i<M; i++) {
				for(int j=0; j<M; j++) {
					int ne = dist[i][k]+dist[k][j];
					if(ne<dist[i][j]) {
						dist[i][j]=ne;
					}
				}
			}
		}
		//min cost of complete sequence for first i of them
		int[] minDP = new int[N+1];
		//min cost for complete sequence ending in letter j, first i letters, ending sequence length l (-K)
		int[][] dp = new int[N+1][M];
		
		int[][] ps = new int[N][M];
		int[][] cst = new int[N][M];
		for(int i=0; i<N;i++) {
			for(int j=0; j<M; j++) {
				cst[i][j]=dist[S.charAt(i)-'a'][j];
				if(i==0) {
					ps[i][j]=cst[i][j];
					continue;
				}
				ps[i][j]=ps[i-1][j]+cst[i][j];
			}
		}
		minDP[K]=ps[K-1][0];
		for(int i=0; i<M; i++) {
			dp[K][i]=ps[K-1][i];
			minDP[K]=Math.min(minDP[K], dp[K][i]);
		}
		for(int i=K+1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				dp[i][j]=dp[i-1][j]+cst[i-1][j];
				if(i>=2*K) {
					dp[i][j]=Math.min(dp[i][j], minDP[i-K]+ps[i-1][j]-ps[i-1-K][j]);
				}
			}
			minDP[i]=dp[i][0];
			for(int j=1; j<M; j++) {
				minDP[i]=Math.min(minDP[i], dp[i][j]);
			}
		}
		
//		for(int i=K; i<Math.min(K+K, N+1); i++) {
//			//j=letter of ending sequence
//			for(int j=0; j<M; j++) {
//				int add = 0;
//				//k used to compute cost of converting suffix sequence
//				for(int k=0; k<i; k++) {
//					add+=dist[S.charAt(i-k-1)-'a'][j];
//				}
//				if(dp[i][i-K]==0||dp[i][i-K]>add) {
//					dp[i][i-K]=add;
//					if(minDP[i]==0 || minDP[i]>add)
//						minDP[i]=add;
//				}
//			}
//		}
//		
//		//l=length of last sequence-K
//		for(int l=0; l<K; l++) {
//			//i=end of sequence being looked at
//			for(int i=K+K+l; i<N+1; i++) {
//				//j=letter of ending sequence
//				for(int j=0; j<M; j++) {
//					int add = 0;
//					//k used to compute cost of converting suffix sequence
//					for(int k=0; k<K+l; k++) {
//						add+=dist[S.charAt(i-k-1)-'a'][j];
//					}
//					if(dp[i][l]==0||dp[i][l]>add) {
//						dp[i][l]=add+minDP[i-K-l];
//						if(minDP[i]==0 || minDP[i]>dp[i][l])
//							minDP[i]=dp[i][l];
//					}
//				}
//			}
//		}
		int min = minDP[N];
		out.println(min);
		out.close();
		f.close();
	}
}
