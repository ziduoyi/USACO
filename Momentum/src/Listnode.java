import java.io.*;
import java.util.*;
public class Listnode {
	public class ListNode {
		int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Listnode ln = new Listnode();
		ln.test();
		
		ln.test();
		
		
	}
	public void test() {
		//0->1->2->3->4
		ListNode head = new ListNode(0);
		ListNode node = head;
		for(int i=1;i<5;i++) {
			node.next = new ListNode(i);
			node=node.next;
		}
		System.out.println(this.numComponents(head, new int[] {0, 3, 1, 4}));
	}
	
    public int numComponents(ListNode head, int[] G) {
    	int len=0;
    	ListNode node=head;
    	while(node!=null) {
    		len++;
    		node=node.next;
    	}
    	node=head;
    	int[] arr= new int[len];
    	for(int i=0; i<len; i++) {
    		arr[i]=-1;
    	}
    	for(int i=0; i<len; i++){
    		int a=node.val;
    		for(int j=0; j<G.length; j++) {
    			if(a==G[j]) {
    				arr[i]=a;
    				break;
    			}
    		}
    		node=node.next;
    	}
    	int parts=0;
    	int size=0;
    	for(int i=0; i<len; i++) {
    		if(arr[i]==-1) {
    			if(size>0) {
    				parts++;
    				size=0;
    			}
    			else
    				continue;
    		}
    		else {
    			if(i==len-1)
    				parts++;
    			else
    				size++;
    		}
    	}
		return parts;
        
    }
}
