import java.io.*;
import java.util.*;
class Trienode {
	boolean isword = false;
	Trienode[] nodes = new Trienode[26];
	void add(String str, int i) {
		if(i>=str.length()) {
			isword= true;
			return;
		}
		int x = str.charAt(i) - 'a';
		if(nodes[x]==null)
			nodes[x] = new Trienode();
		nodes[x].add(str, i+1);
	}
	
	void add(String str, Trienode root) {
		for(int i=0; i<str.length(); i++) {
			int x = str.charAt(i)-'a';
			if(root.nodes[x]==null)
				root.nodes[x] = new Trienode();
			root = root.nodes[x];
		}
		root.isword=true;
	}
	
	boolean search(String str, Trienode root) {
		for(int i=0; i<str.length(); i++) {
			int x = str.charAt(i)-'a';
			if(root.nodes[x]==null)
				return false;
			root = root.nodes[x];
		}
		return root.isword;
	}
	static ArrayList<Integer>[] edges;
	static boolean[] visit;
	@SuppressWarnings("unchecked")
	boolean isFirst(String str, Trienode root) {
		edges = new ArrayList[26];
		visit = new boolean[26];
		for(int i=0; i<26; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<str.length(); i++) {
			if(root.isword==true)
				return false;
			int x = str.charAt(i)-'a';
			for(int j =0; j<26; j++) {
				if(root.nodes[j]==null||j==x)
					continue;
				edges[x].add(j);
			}
			root = root.nodes[x];
		}
		for(int i=0; i<26; i++) {
			if(visit[i]==true)
				continue;
			if(isCycle(i, new boolean[26])==true)
				return false;
		}
		return true;
	}
	boolean isCycle(int curr, boolean[] been) {
		ArrayList<Integer> al = edges[curr];
		int s = al.size();
		for(int j=0; j<s; j++) {
			int tar = al.get(j);
			if(visit[tar]==true)
				continue;
			if(been[tar]==true)
				return true;
			been[tar]=true;
			if(isCycle(tar, been)==true)
				return true;
		}
		visit[curr]=true;
		return false;
	}
}

public class first {
	static String[] input;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Trienode root = new Trienode();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		input = new String[N];
		int count =0;
		for(int i=0; i<N; i++) {
			input[i] = br.readLine();
			root.add(input[i],root);
		}
		boolean[] ans = new boolean[N];
		for(int i=0; i<N; i++) {
			ans[i] = root.isFirst(input[i],root);
			if(ans[i]==true)
				count++;
		}
		System.out.println(count);
		for(int i=0; i<N; i++) {
			if(ans[i]==true)
				System.out.println(input[i]);
		}

 	}

}
