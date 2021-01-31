package com.ex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Usaco {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new File("milkorder.in"));
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int K = scanner.nextInt();
		int[] cow = new int[N+1];
		int[] order = new int[M];
		
		for(int i=0;i<M;i++)
			order[i]=scanner.nextInt();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));		
		for(int i=0;i<K;i++){
			int num = scanner.nextInt();
			int pos =scanner.nextInt();
			cow[pos] = num;
			if(num==1){
				out.println(cow[1]);
				out.close();
				return;
			}
		}

		int pos1=-1;
		for(int i=0;i<M;i++){
			if(order[i]==1){
				pos1=i;
				break;
			}
		}
		if(pos1>-1){
			for(int i=0;i<M;i++){
				for(int j=1;j<=N;j++){
					if(order[i] == cow[j]){
						if(pos1<i){
							for(int x=1;x<=N;x++){
								if(cow[x]==0) pos1--;
								if(pos1<0){
									out.println(x);
									out.close();
									return;
								}
							}
						}else{
							for(int x=j+1;x<=N;x++){
								if(cow[x]==0) {
									if(pos1>i+1)
										pos1--;
									else{
										out.println(x);
										out.close();
										return;
									}
								}
							}
						}
					}
				}
			}
		}else{
			for(int i=0;i<M;i++){
				for(int j=1;j<=N;j++){
					if(order[i] == cow[j]){
						int y=i;
						for(int x=j-1;x>0;x--){
							if(cow[x]==0){
								if(y>0)
									cow[x]=order[--y];
								else{
									out.println(x);
									out.close();
									return;
								}
							}
						}
					}
				}
			}
		}
		
		for(int i=1;i<=N;i++){
			if(cow[i]==0){
				out.println(i);
				break;
			}
		}
		
		out.close();
	}

}
