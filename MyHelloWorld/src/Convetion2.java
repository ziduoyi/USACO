import java.io.*;
import java.util.*;

public class Convetion2 {

	@SuppressWarnings("resource")
	public static void main(String[] args)throws IOException {

		long t1=System.currentTimeMillis();
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		int num=Integer.parseInt(br.readLine());
	
		int[][] arr=new int[num][3];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
			arr[i][2]=i;
		}
		Arrays.sort(arr,(a,b)->{if(a[0]!=b[0]) 
				return (int) (a[0]-b[0]);
				else return (int) (a[2]-b[2]);
		});
	
		long wait=0;
		long endTime=0;
		//TreeSet<int[]> queue = new TreeSet<int[]>((a,b)->(a[2]-b[2]));
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->(a[2]-b[2]));
	
		int[] cur=null;
		int pos=0;
		while(pos<num || !queue.isEmpty()) {
			if(queue.isEmpty()){
				cur=arr[pos++];
				endTime = cur[0];
			}else
				//cur=queue.pollFirst();
				cur=queue.poll();
	
			wait=Math.max(wait, endTime-cur[0]);
			endTime += cur[1];
				
			while(pos<num){
				if(arr[pos][0]>=endTime) break;
				queue.add(arr[pos++]);
			}
		}
		out.println(wait);
		out.close();
		System.out.println((System.currentTimeMillis()-t1) + "ms");
	}
}
/*
// TreeSet
	public static void main(String[] args)throws IOException {
		
 		long t1=System.currentTimeMillis();
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		int num=Integer.parseInt(br.readLine());
		
		TreeSet<int[]> cows = new TreeSet<int[]>((a,b)->{
			if(a[0]!=b[0]) 
				return (a[0]-b[0]);
			else 
				return (a[2]-b[2]);
		});

		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int[] arr = new int[3];
			arr[0]=Integer.parseInt(st.nextToken());
			arr[1]=Integer.parseInt(st.nextToken());
			arr[2]=i;
			cows.add(arr);
		}

		long wait=0;
		long endTime=0;
		TreeSet<int[]> queue = new TreeSet<int[]>((a,b)->(a[2]-b[2]));

		int[] cur=null;
		while(!cows.isEmpty() || !queue.isEmpty()) {
			if(queue.isEmpty()){
				cur=cows.pollFirst();
				endTime = cur[0];
			}else
				cur=queue.pollFirst();

			wait=Math.max(wait, endTime-cur[0]);
			endTime += cur[1];
				
			while(!cows.isEmpty()){
				int[] arr = cows.first();
				if(arr[0]>=endTime) break;
				cows.pollFirst();
				queue.add(arr);
			}
		}
		out.println(wait);
		out.close();
		System.out.println((System.currentTimeMillis()-t1) + "ms");
	}
*/

/*
 *
//Prioirity Queue
public static void main(String[] args)throws IOException {
	
//	PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("convention2.in")));
//	out1.println(100000);
//	Random rd = new Random();
//	for(int i=0;i<100000;i++){
//		out1.print(rd.nextInt(10000));
//		out1.print("  ");
//		out1.println(rd.nextInt(1000000000));
//	}
//	out1.close();
//	if(rd!=null)return;
	
	long t1=System.currentTimeMillis();
	// TODO Auto-generated method stub
	BufferedReader br=new BufferedReader(new FileReader("convention2.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
	int num=Integer.parseInt(br.readLine());
	PriorityQueue<int[]> cows = new PriorityQueue<int[]>((a,b)->{
		if(a[0]!=b[0]) 
			return (a[0]-b[0]);
		else 
			return (a[2]-b[2]);
	});
	for(int i=0; i<num; i++) {
		StringTokenizer st=new StringTokenizer(br.readLine());
		int[] arr = new int[3];
		arr[0]=Integer.parseInt(st.nextToken());
		arr[1]=Integer.parseInt(st.nextToken());
		arr[2]=i;
		cows.add(arr);
	}
	
//	int[][] arr=new int[num][3];
//	for(int i=0; i<num; i++) {
//		StringTokenizer st=new StringTokenizer(br.readLine());
//		arr[i][0]=Integer.parseInt(st.nextToken());
//		arr[i][1]=Integer.parseInt(st.nextToken());
//		arr[i][2]=i;
//	}
//	Arrays.sort(arr,(a,b)->{if(a[0]!=b[0]) 
//			return (int) (a[0]-b[0]);
//			else return (int) (a[2]-b[2]);
//	});
	
	long wait=0;
	long endTime=0;
	PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->(a[2]-b[2]));

	int[] cur=null;
	while(!cows.isEmpty() || !queue.isEmpty()) {
		if(queue.isEmpty()){
			cur=cows.poll();
			endTime = cur[0];
		}else
			cur=queue.poll();

		wait=Math.max(wait, endTime-cur[0]);
		endTime += cur[1];
			
		while(!cows.isEmpty()){
			int[] arr = cows.peek();
			if(arr[0]>=endTime) break;
			cows.poll();
			queue.add(arr);
		}
	}
	out.println(wait);
	out.close();
	System.out.println((System.currentTimeMillis()-t1) + "ms");
}*/


/*
public static void main(String[] args)throws IOException {

	long t1=System.currentTimeMillis();
// TODO Auto-generated method stub
BufferedReader br=new BufferedReader(new FileReader("convention2.in"));
PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
int num=Integer.parseInt(br.readLine());

int[][] arr=new int[num][3];
for(int i=0; i<num; i++) {
	StringTokenizer st=new StringTokenizer(br.readLine());
	arr[i][0]=Integer.parseInt(st.nextToken());
	arr[i][1]=Integer.parseInt(st.nextToken());
	arr[i][2]=i;
}
Arrays.sort(arr,(a,b)->{if(a[0]!=b[0]) 
		return (int) (a[0]-b[0]);
		else return (int) (a[2]-b[2]);
});

long wait=0;
long endTime=0;
//TreeSet<int[]> queue = new TreeSet<int[]>((a,b)->(a[2]-b[2]));
PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->(a[2]-b[2]));

int[] cur=null;
int pos=0;
while(pos<num || !queue.isEmpty()) {
	if(queue.isEmpty()){
		cur=arr[pos++];
		endTime = cur[0];
	}else
		cur=queue.poll();//cur=queue.pollFirst();

	wait=Math.max(wait, endTime-cur[0]);
	endTime += cur[1];
		
	while(pos<num){
		if(arr[pos][0]>=endTime) break;
		queue.add(arr[pos++]);
	}
}
out.println(wait);
out.close();
System.out.println((System.currentTimeMillis()-t1) + "ms");
}
*/