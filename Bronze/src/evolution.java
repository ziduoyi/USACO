import java.util.*;
import java.io.*;
public class evolution {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("evolution.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("evolution.out")));
		int N=scanner.nextInt();
		Map<String, Integer> map=new HashMap<>();
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
		int a=0;
		for(int i=0; i<N; i++) {
			int k=scanner.nextInt();
			ArrayList<Integer> al=new ArrayList<>();
			for (int j=0; j<k; j++) {
				String s=scanner.next();
				if(map.get(s)==null) {
					map.put(s, a++);
				}
				int x=map.get(s);
				al.add(x);
			}
			Collections.sort(al);
			list.add(al);
		}
		boolean b=recursion(list,a);
		if(b==true)
			out.println("yes");
		else
			out.println("no");
		out.close();
	}
	static boolean recursion(ArrayList<ArrayList<Integer>> arr, int a) {
		int[] count =new int[a];
		for(int i=0; i<arr.size(); i++) {
			ArrayList<Integer>al=arr.get(i);
			for(int j=0; j<arr.get(i).size(); j++) {
				count[al.get(j)]++;
			}
		}
		int s=arr.size();
		for(int i=0; i<s; i++) {
			ArrayList<Integer>al=arr.remove(0);
			boolean b=false;
			for(int j=0; j<al.size(); j++) {
				if(count[al.get(j)]>1) {
					b=true;
					break;
				}
			}
			if(b==false) {
				for(int j=0; j<al.size(); j++) {
					count[al.get(j)]--;
				}
			}
			else {
				arr.add(al);
			}
		}
		if(arr.size()==0)
			return true;
		for(int i=0; i<a ; i++) {
			if(count[i]==0)
				continue;
			ArrayList<ArrayList<Integer>> copy =new ArrayList<>();
			for(int j=0; j<arr.size(); j++) {
				ArrayList<Integer> al=new ArrayList<>();
				ArrayList<Integer> b=arr.get(j);
				for(int k=0; k<b.size(); k++) {
					al.add(b.get(k));
				}
				copy.add(al);
			}
			int l=arr.size();
			boolean[] used =new boolean[l];
			for(int j=0; j<l ; j++) {
				ArrayList<Integer> al= copy.remove(0);
				int contain=Collections.binarySearch(al,i);
				if(contain>-1) {
					used[j]=true;
					al.remove(contain);
				}
				copy.add(al);
			}
			Set<Integer> set1=new HashSet<>();
			Set<Integer> set2=new HashSet<>();
			for(int j=0; j<l; j++) {
				ArrayList<Integer> al=copy.get(j);
				for(int k=0; k<al.size(); k++) {
					if(used[j]==true) {
						set1.add(al.get(k));
					}
					else {
						set2.add(al.get(k));
					}
				}
			}
			int need=set1.size()+set2.size();
			set1.addAll(set2);
			if(set1.size()==need) {
				ArrayList<ArrayList<Integer>> one=new ArrayList<>();
				ArrayList<ArrayList<Integer>> two =new ArrayList<>();
				for(int j=0; j<l; j++) {
					ArrayList<Integer> al=copy.get(j);
					if(used[j]==true) {
						one.add(al);
					}
					else {
						two.add(al);
					}
				}
				if(recursion(one,a)==true&&recursion(two,a)==true)
					return true;
			}
		}
		return false;
	}
}
