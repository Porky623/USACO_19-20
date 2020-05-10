import java.util.*;

import java.io.*;
public class pump {
	//BFS
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("pump.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<Integer,Double> best = new HashMap<>();
		HashMap<Integer,HashMap<Integer,Integer>> flow = new HashMap<>();
		HashMap<Integer,HashMap<Integer,Integer>> cost = new HashMap<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(f.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int F = Integer.parseInt(st.nextToken());
			if(!flow.containsKey(a)) {
				flow.put(a, new HashMap<>());
				cost.put(a, new HashMap<>());
			}
			if(!flow.containsKey(b)) {
				flow.put(b, new HashMap<>());
				cost.put(b, new HashMap<>());
			}
			flow.get(a).put(b, F);
			cost.get(a).put(b, C);
			flow.get(b).put(a, F);
			cost.get(b).put(a, C);
		}
		Queue<pNode> q = new LinkedList<>();
		q.add(new pNode(1,1001,0));
		while(!q.isEmpty()) {
			pNode cur = q.poll();
			double val;
			if(cur.totalCost>0) {
				val=(double)cur.totalFlow/(double)cur.totalCost;
				if(!best.containsKey(cur.pos)||best.get(cur.pos)<val) {
					best.put(cur.pos, val);
				}
			}
			if(flow.containsKey(cur.pos)) {
				HashMap<Integer,Integer> curFlow = flow.get(cur.pos);
				HashMap<Integer,Integer> curCost = cost.get(cur.pos);
				for(int adj:curFlow.keySet()) {
					int newFlow =Math.min(cur.totalFlow,curFlow.get(adj));
					int newCost = cur.totalCost+curCost.get(adj);
					if(best.containsKey(adj)) {
						if(best.get(adj)<(double)newFlow/(double)newCost)
							q.add(new pNode(adj,newFlow,newCost));
					}
					else {
						q.add(new pNode(adj,newFlow,newCost));
					}
				}
			}
		}
		out.println(""+(int)(1000000.*best.get(N)));
		out.close();
		f.close();
	}
	static class pNode{
		public int pos,totalFlow,totalCost;
		public pNode(int p, int f, int c) {
			this.pos=p;
			this.totalCost=c;
			this.totalFlow=f;
		}
	}
}
