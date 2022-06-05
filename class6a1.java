import java.io.*;
import java.util.*;
public class class6a1 {
	public static void main(String[] args) throws IOException {
		int cnt = 0;
		for(int a = 0; a<6; a++) {
			for(int b = 0; b<6; b++) {
				for(int c = 0; c<6; c++) {
					for(int d = 0; d<6; d++) {
						for(int e = 0; e<6; e++) {
							for(int f = 0; f<6; f++) {
								int[] arr= new int[] {a,b,c,d,e,f};
								boolean z = false;
								for(int i=0; i<6; i++)
									for(int j=0; j<6; j++)
										if(arr[i]==arr[j]&&i!=j) {
											z = true;
											break;
										}
								if(z) break;
								z = false;
								for(int i=0; i<4; i++)
									if(arr[i]<arr[i+1]&&arr[i+1]<arr[i+2]) {
										z= true;
										break;
									}
								if(!z)
									cnt++;
							}
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
}