import java.util.*;
import java.io.*;
public class exercise {
	public static void main(String[] args) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("exercise.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int countMod=M-1;
		ArrayList<HashMap<Long,Long>> dp=new ArrayList<>();
		HashMap<Long,Long> hash0=new HashMap<>();
		hash0.put((long)1, (long)1);
		dp.add(hash0);
		HashMap<Long,Long> hash1=new HashMap<>();
		hash1.put((long)1, (long)1);
		dp.add(hash1);
		int[] fact=new int[N];
		fact[0]=1;
		for(int i=1; i<=N-1; i++) {
			fact[i]=(fact[i-1]*i)%countMod;
		}
		for(int i=2; i<=N; i++) {
			HashMap<Long,Long> hashI=new HashMap<>();
			long nCk=1;
			for(int a=0; a<=i-1; a++) {
				HashMap<Long,Long> ref=dp.get(i-1-a);
				for(long k:ref.keySet()) {
					long length=lcm(k,(long)a+1,(long)(countMod));
					if(!hashI.containsKey(length)) {
						hashI.put(length, ((nCk*ref.get(k))%countMod*fact[a])%countMod);
					}
					else
						hashI.put(length, (hashI.get(length)+((nCk*ref.get(k))%countMod*fact[a]))%countMod);
				}
				nCk=(nCk*(i-1-a))/(a+1)%countMod;
			}
			dp.add(hashI);
		}
		HashMap<Long,Long> end=dp.get(N);
		long val=1;
		for(long k:end.keySet()) {
			val=(val*pow(k%M,end.get(k),M))%M;
		}
		out.println(val);
		out.close();
	}
	static long pow(long base, long power, int M) {
		long res = 1;
	   
	    if (base == 0) return 0; // In case x is divisible by p; 
	  
	    while (power > 0)  
	    {  
	        // If y is odd, multiply x with result  
	        if (power%2==1)  
	            res = (res*base) % M;  
	  
	        // y must be even now  
	        power = power>>1; // y = y/2  
	        base = (base*base) % M;  
	    }  
	    return res;  
	}
	static long lcm(long number1, long number2,long countMod) {
		long smallerValue=Math.min(number1, number2);
		long biggerValue=Math.max(number1, number2);
		return (smallerValue/gcd(smallerValue,biggerValue)*biggerValue)%countMod;
	}
	static long gcd(long smallerValue, long biggerValue) {
		if(smallerValue==1) {
			return 1;
		}
	    if (smallerValue==0) {
	        return biggerValue;
	    } else {
	        return gcd(biggerValue % smallerValue, smallerValue);
	    }
	}
}
