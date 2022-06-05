import java.io.*;
import java.util.*;


public class StuckInRut {

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); //# of cows
		
		char[] dir = new char[N];
		int[] X = new int[N];
		int[] Y = new int[N];
		int[] distance = new int[N];
		
		//read the values
		for(int i=0; i<N; i++) {
			dir[i] = in.next().charAt(0); //'N' or 'E'
			X[i] = in.nextInt();
			Y[i] = in.nextInt();
			distance[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0; i<N; i++) {
			if(dir[i] != 'E')
				continue;
			for(int j=0; j<N; j++) {
				if(dir[j] != 'N')
					continue;
				
				if(Y[j]<Y[i] && X[j]>X[i]) {
					int dx = X[j] - X[i];
					int dy = Y[i] - Y[j];
					
					if(dx==dy)
						; //do nothing
					
					else if(dx<dy) { //cow i potentially blocks cow j
						boolean blocki = false;
						for(int k=0; k<N; k++) {
							if(X[k]<X[j] && Y[k]<Y[i] && X[k]>X[i] && dir[k]=='N') {
								if(distance[k]>=Y[i]-Y[k] && Y[i]-Y[k]<X[k]-X[i]) {
									//distance[i] = Math.min(Y[k]-Y[i], distance[i]);
									blocki=true;
								}
							}
						}
						if(!blocki) {
							distance[j] = Math.min(distance[j], dy);
						}
					}
					else { //dx>dy cow j potentially blocks cow i check if any cow k blocks cow j
						boolean blockj = false;
						for(int k=0; k<N; k++) {
							if(Y[k]<Y[i] && Y[k]>Y[j] && X[k]<X[j] && dir[k]=='E') {
								if(distance[k]>=Y[j]-Y[k] && Y[j]-Y[k]<X[k]-X[j]) {
									//distance[j] = Math.min(Y[k]-Y[j], distance[j]);
									blockj=true;
								}
							}
						}
						if(!blockj) {
							distance[i] = Math.min(distance[i], dx);
						}
						
					}
						
				}
			}
		}
		for(int i=0; i<distance.length; i++) {
			if(distance[i]!=2147483647)
				System.out.println(distance[i]);
			else
				System.out.println("Infinity");
		}

	}

}
