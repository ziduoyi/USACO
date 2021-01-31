import java.io.*;
import java.util.*;
public class class7leetcode {

	public static void main(String[] args)throws IOException { 
//		ArrayList<String> a = new ArrayList<>();
//		a.add("java");
//		a.add("nodejs");
//		ArrayList<String > b = new ArrayList<>();
//		b.add("nodejs");
//		b.add("reactjs");
//		list.add(a);
//		list.add(b);
		System.out.println(jobScheduling(new int[] {1,3,3,3,5,5,13,5}, new int[] {17,14,8,11,14,7,17,9}, new int[] {9,3,7,18,2,17,4,6}));
	}
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] arr = new int[profit.length][3];
        for(int i=0;i<profit.length; i++)arr[i][0] = startTime[i];
        for(int i=0;i<profit.length; i++)arr[i][1] = endTime[i];
        for(int i=0;i<profit.length; i++)arr[i][2] = profit[i];
        Arrays.sort(arr,(a,b) ->a[1]-b[1]);
        for(int i=0;i<profit.length; i++)startTime[i] = arr[i][0];
        for(int i=0;i<profit.length; i++)endTime[i] = arr[i][1];
        for(int i=0;i<profit.length; i++)profit[i] = arr[i][2];
        int[] dp = new int[profit.length];
        for(int i=0; i<profit.length; i++) {
        	dp[0]= Math.max(dp[0], profit[i]);
        	if(endTime[i]!=endTime[i+1])
        		break;
        }
        for(int i=1; i<profit.length; i++){
            for(int j=i-1; j>-1; j--){
                if(endTime[j]<=startTime[i]) {
                    dp[i]= Math.max(dp[i], dp[j] + profit[i]);
                    break;
                }
                dp[i]=Math.max(dp[i], profit[i]);
            }
        }
        int max =0;
        for(int i=0; i<profit.length; i++) 
        	max = Math.max(max, dp[i]);
        for(int i=0; i<profit.length; i++)
        	max = Math.max(max, profit[i]);
        return max;
    }
    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        HashMap<String, Integer>map = new HashMap<>();
        int[] dp = new int[(int) Math.pow(2,req_skills.length)];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0; i<req_skills.length; i++)
            map.put(req_skills[i], (int)Math.pow(2,i));
        int[] arr = new int[people.size()];
        for(int i=0; i<people.size(); i++) {
        	for(int j=0; j<people.get(i).size(); j++) {
        		int add = map.get(people.get(i).get(j));
        		arr[i]+=add;
        	}
        }
        ArrayList<Integer>[] stuff = new ArrayList[dp.length];
        for(int i=0; i<dp.length; i++)
        	stuff[i] = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
        	for(int j=0; j< dp.length; j++) {
        		if(dp[j]==Integer.MAX_VALUE)
        			continue;
        		int k = j | arr[i];
        		if(k>=dp.length)
        			continue;
        		if(dp[j]+1<dp[k]) {
        			dp[k] =dp[j] +1;
        			ArrayList<Integer> al =new ArrayList<>(stuff[j].size()) ;
        			al.addAll(stuff[j]);
        			al.add(i);
        			stuff[k] = al;
        		}
        	}
        }
        ArrayList<Integer> al =stuff[dp.length-1];
        Collections.sort(al);
        int[] ans = new int[al.size()];
        for(int i=0; i<al.size(); i++)
        	ans[i] = al.get(i);
        return ans;
    }
	/*
    public static int dieSimulator(int n, int[] rollMax) {
        long[][] dp = new long[n][6];
        for(int i=0; i<6; i++){
            dp[0][i] = 1;
        }
        long mod = 1000000007;
        for(int i=1; i<n;i++){
            for(int j=0; j<6; j++){
                if(i<rollMax[j]){
                    for(int k =0; k<6; k++){
                        dp[i][j] = (dp[i][j] + dp[i-1][k])%mod;
                    }
                }
                else{
                    for(int k =0; k<6; k++){
                        if(k == j){
                            dp[i][j] = (dp[i][j] + dp[i-1][j] - dp[i-rollMax[j]][j] + mod) % mod;
                        }
                        else{
                            dp[i][j] = (dp[i][j] + dp[i-1][k]) %mod;
                        }
                    }
                }
            }
        }
        return (int)((dp[n-1][0] + dp[n-1][1] + dp[n-1][2] + dp[n-1][3] + dp[n-1][4] + dp[n-1][5])%mod);
    }
    */
		/*String str="()))((";
		char[] arr=str.toCharArray();
		Stack<Character> stack=new Stack<>();
		for(int i=0; i<arr.length; i++) {
			stack.push(arr[i]);
		}
		int need=0;
		int r=0;
		while(!stack.isEmpty()) {
			char c=stack.pop();
			if(c=='('&&r==0) {
				need++;
				continue;
			}
			if(c=='(') {
				r--;
				continue;
			}
			if(c==')') {
				r++;
			}
		}
		need+=r;
		System.out.println(need);
		int[] nums=new int[] {1,1,1};
		int k=2;
		int[] tol=new int[nums.length+1];
		for(int i=1; i<nums.length+1; i++) {
			tol[i]=tol[i-1]+nums[i-1];	
		}
		int ways=0;
		for(int i=1; i<tol.length; i++) {
			for(int j=0; j<i; j++) {
				if(tol[i]-tol[j]==k)
					ways++;
			}
		}
		System.out.println(ways);
		String A="this apple is sweet";
		String B="this apple is sour";
		Map<String, Integer> map1=new HashMap<>();
		ArrayList<String> list=new ArrayList<>();
		String[] arra=A.split(" ");
		String[] arrb=B.split(" ");
		for(int i=0; i<arra.length; i++) {
			map1.put(arra[i],map1.getOrDefault(arra[i],0)+1);
		}
		for(int i=0; i<arrb.length; i++) {
			map1.put(arrb[i],map1.getOrDefault(arrb[i],0)+1);
		}
		Set<String> set=map1.keySet();
		for(String s: set) {
			if(map1.get(s)==1)
				list.add(s);
		}
		String[] ans=new String[list.size()];
		for(int i=0; i<list.size(); i++) {
			ans[i]=list.get(i);
		}
		
		String A="this apple is sweet";
		String B="this apple is sour";
		ArrayList<String> list=new ArrayList<>();
		String[] arra=A.split(" ");
		String[] arrb=B.split(" ");
		for(int i=0; i<arra.length; i++) {
			boolean b=true;
			for(int j=0; j<arrb.length; j++) {
				if(arra[i].equals(arrb[j])) {
					b=false;
					break;
				}
			}
			if(b==false)
				continue;
			for(int j=0; j<arra.length; j++) {
				if(i==j)
					continue;
				if(arra[i].equals(arra[j])) {
					b=false;
					break;
				}
			}
			if(b==false)
				continue;
			list.add(arra[i]);
		}
		for(int i=0; i<arrb.length; i++) {
			boolean b=true;
			for(int j=0; j<arra.length; j++) {
				if(arrb[i].equals(arra[j])) {
					b=false;
					break;
				}
			}
			if(b==false)
				continue;
			for(int j=0; j<arrb.length; j++) {
				if(i==j)
					continue;
				if(arrb[i].equals(arrb[j])) {
					b=false;
					break;
				}
			}
			if(b==false)
				continue;
			list.add(arrb[i]);
		}
		int h=0;

		
		int k=4;
		int[] nums=new int[] {1,12,-5,-6,50,3};
        double ave=0;
        for(int i=0; i<nums.length-k+1; i++){
            double a=0;
            for(int j=i; j<k+1; j++){
                a+=nums[j];
            }
            a/=k;
            if(a>ave)
                ave=a;
        }
        System.out.println(ave);
		int[] asteroids=new int[] {-2,-1,1,2};
		Stack<Integer> stack=new Stack<>();
		Stack<Integer> ans=new Stack<>();
        int[] arr=new int[asteroids.length];
        int z=0;
		for(int i=0; i<asteroids.length; i++) {
			if(asteroids[i]>0) {
				stack.push(asteroids[i]);
				continue;
			}
			else {
				boolean broken=false;
				while(stack.size()!=0) {
					int a=stack.pop();
					if(a<Math.abs(asteroids[i])){
						continue;
					}
					if(a==Math.abs(asteroids[i])){
						broken=true;
						break;
					}
					if(a>Math.abs(asteroids[i])){
						stack.push(a);
						broken=true;
						break;
					}
				}
				if(stack.size()==0&&broken==false) {
                    arr[z]=asteroids[i];
                    z++;
				}
			}
		}
		int b=stack.size();
		for(int i=0; i<b; i++) {
			ans.push(stack.pop());
		}
		int a=ans.size();
        for(int i=z; i<a+z; i++){
            arr[i]=ans.pop();
        }
		int h=0;
		*/
		/*String S= "xywrrmp";
		String T= "xywrrmu#p";
		Stack<Character> stack1=new Stack<>();
		Stack<Character> stack2=new Stack<>();
		for(int i=0; i<S.length(); i++) {
			if(S.charAt(i)=='#') {
				if(stack1.size()==0)
					continue;
				stack1.pop();
			}
			else {
				stack1.push(S.charAt(i));
			}
		}
		for(int i=0; i<T.length(); i++) {
			if(T.charAt(i)=='#') {
				if(stack2.size()==0)
					continue;
				stack2.pop();
			}
			else {
				stack2.push(T.charAt(i));
			}
		}
		boolean same=true;
		while(stack1.size()!=0&&stack2.size()!=0) {
			char a=stack1.pop();
			char b=stack2.pop();
			if(a!=b) {
				same=false;
				break;
			}
		}
		if(stack1.size()!=stack2.size()) {
			same=false;
		}
		System.out.print(same);
		*/
/*		String[] ops =new String[] {"5","2","C","D","+"};
		Stack<Integer> stack=new Stack<>();
		int score=0;
		for(int i=0; i<ops.length; i++) {
			if(ops[i]!="+"&&ops[i]!="D"&&ops[i]!="C") {
				int num=Integer.parseInt(ops[i]);
				stack.push(num);
				score+=num;
				continue;
			}
			if(ops[i]=="+") {
				if(stack.size()==0)
					continue;
				int a=stack.pop();
				score+=a;
				if(stack.size()==0) {
					stack.push(a);
					stack.push(a);
					continue;
				}
				int b=stack.pop();
				score+=b;
				stack.push(b);
				stack.push(a);
				stack.push(a+b);
			}
			if(ops[i]=="C") {
				if(stack.size()!=0) {
					int n=stack.pop();
					score-=n;
				}
			}
			if(ops[i]=="D") {
				if(stack.size()!=0) {
					int c=stack.pop();
					stack.push(c);
					c*=2;
					score+=c;
					stack.push(c);
				}
			}
		}
		return;
		*/
		// TODO Auto-generated method stub
/*		Scanner scanner=new Scanner(new File("shuffle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		int num=scanner.nextInt();
		int[] arr=new int[num];
		int[] val=new int[num];
		int ans=0;
		for(int i=0; i<num; i++) {
			arr[i]=scanner.nextInt()-1;
			val[arr[i]]+=1;
		}
		for(int i=0; i<num; i++) {

				int save=i;
				int pos=arr[arr[save]];
				int a=0;
				while(a!=num) {
					save=arr[save];
					pos=arr[arr[save]];
					val[save]--;
					a++;
					if(val[save]<1) {
						continue;
					}
					else {
						break;
					}
				}
			
		}
		for(int i=0; i<num; i++) {
			if(val[i]>0) {
				ans++;
			}
		}
		out.print(ans);
		out.close();
		*/
	

}
