
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class GEQsum{
    public static void solve(){
        
    }
    public static int[] nextGreater(long a[],int n){
        int ng[]=new int[n];
        for(int i=0;i<n;i++)ng[i]=n;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<n;i++){
            while(st.size()>0&&a[st.peek()]<a[i]){
               ng[st.pop()]=i;
            }
            st.push(i);
        }
        return ng;
    }
    
    public static int[] prevGreater(long a[],int n){
        int pg[]=new int[n];
        for(int i=0;i<n;i++)pg[i]=-1;
        Stack<Integer> st=new Stack<>();
        for(int i=n-1;i>=0;i--){
            while(st.size()>0&&a[st.peek()]<a[i]){
                pg[st.pop()]=i;
            }
            st.push(i);
        }
        return pg;
    }
    public static void main(String args[])throws IOException, ParseException{
       Scanner sc=new Scanner(System.in);
       int t=sc.nextInt();
       while(t-->0){
         int n=sc.nextInt();
         long a[]=new long[n];
         for(int i=0;i<n;i++){
             a[i]=sc.nextLong();
         }
         long pre[]=new long[n];
         long suf[]=new long[n];
         pre[0]=a[0];
         suf[n-1]=a[n-1];
         for(int i=1;i<n;i++){
             pre[i]+=pre[i-1]+a[i];
         }
         for(int i=n-2;i>=0;i--){
             suf[i]+=suf[i+1]+a[i];
         }
         SegmentTree preseg=new SegmentTree(pre,n);
         SegmentTree sufseg=new SegmentTree(suf,n);
         int next_greater[]=nextGreater(a,n);
         int prev_greater[]=prevGreater(a,n);
         boolean flag=false;
         for(int i=0;i<n;i++){
             long rg=preseg.getRangeValue(i+1, next_greater[i]-1)-pre[i];
             long lg=sufseg.getRangeValue(prev_greater[i]+1, i-1)-suf[i];
             long res=Math.max(rg,lg);
            // System.out.println(i+" "+next_greater[i]+" "+prev_greater[i]+" "+rg+" "+lg);
             if(res>0){
                 flag=true;
                 break;
             }
         }
         if(flag)System.out.println("NO");
         else
         System.out.println("YES");
       }
       
    }
 
   
}
 
 
class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
 
    public Reader()
    {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
 
    public Reader(String file_name) throws IOException
    {
        din = new DataInputStream(
            new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
 
    public String readLine() throws IOException
    {
        byte[] buf = new byte[1000000]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                if (cnt != 0) {
                    break;
                }
                else {
                    continue;
                }
            }
            buf[cnt++] = (byte)c;
        }
        return new String(buf, 0, cnt);
    }
 
    public int nextInt() throws IOException
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
 
        if (neg)
            return -ret;
        return ret;
    }
 
    public long nextLong() throws IOException
    {
        long ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }
 
    public double nextDouble() throws IOException
    {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
 
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
 
        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }
 
        if (neg)
            return -ret;
        return ret;
    }
 
    private void fillBuffer() throws IOException
    {
        bytesRead = din.read(buffer, bufferPointer = 0,
                             BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }
 
    private byte read() throws IOException
    {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }
 
    public void close() throws IOException
    {
        if (din == null)
            return;
        din.close();
    }
}
class Data implements Comparable<Data>{
    int num;
    public Data(int num){
       this.num=num;
    }
    public int compareTo(Data o){
        return -o.num+num;
    }
    public String toString(){
        return num+" ";
    }
}
 
 
class SegmentTree{
    long tree[];
    long data[];
    int tsize;
    int dsize;
    public static Long ninf=-(long)1e15;
    public SegmentTree(long data[],int n){
        this.tsize=4*n+1;
        this.dsize=n;
        this.data=data;
        this.tree=new long[tsize];
        build(0,n-1,0);
    }
    public SegmentTree(int n,long def){
        this.tsize=4*n+1;
        this.dsize=n;
        this.data=new long[n];
        for(int i=0;i<n;i++){
            data[i]=def;
        }
        this.tree=new long[tsize];
        build(0,n-1,0);
    }
    public void build(int l,int r,int treeindex){
        if(l==r){
            tree[treeindex]=data[l];
            return ;
        }
        int mid=(l+r)/2;
        build(l,mid,2*treeindex+1);
        build(mid+1,r,2*treeindex+2);
        tree[treeindex]=Math.max(tree[2*treeindex+1],tree[2*treeindex+2]);
    }
    public void updateOneImpl(int l,int r,int tind,int dind){
        if(l==r&&l==dind){
            tree[tind]=data[dind];
            return ;
        }
        if(dind>=l&&dind<=r){
             int mid=(l+r)/2;
            updateOneImpl(l,mid,2*tind+1,dind);
            updateOneImpl(mid+1,r,2*tind+2,dind);
       
           tree[tind]=Math.max(tree[2*tind+1],tree[2*tind+2]);
         }
      // System.out.println("In range "+l+" "+r+" "+tree[tind]);
    }
    public void updateOne(int index,long value){
        data[index]=value;
        updateOneImpl(0,dsize-1,0,index);
    }
    public long getRangeValueImpl(int ql,int qr,int tl,int tr,int tind){
          if(tr<ql||qr<tl){
              return ninf;
          }
          if(qr>=tr&&ql<=tl)return tree[tind];
          int mid=(tl+tr)/2;
          long l=getRangeValueImpl(ql, qr, tl, mid, 2*tind+1);
          long r=getRangeValueImpl(ql, qr, mid+1, tr, 2*tind+2);
          //System.out.println("In range "+tl+" "+tr+" "+Math.max(l,r));
          return Math.max(l,r);
    }
    public long getRangeValue(int l,int r){
        if(l>r||l<0||r<0||l>=dsize||r>=dsize)return ninf;
        long temp= getRangeValueImpl(l,r, 0, dsize-1, 0);
      // System.out.println(" Max Value in between "+l+" "+r+" = "+temp);
        return temp;
    }
    public void printData(){
        System.out.println("The Data After updation");
        for(int i=0;i<dsize;i++){
            System.out.print(data[i]+" ");
        }
        System.out.println();
        // System.out.println(" The Tree After Updation");
        // for(int i=0;i<tsize;i++){
        //     System.out.print(tree[i]+" ");
        // }
        // System.out.println();
    }
 
}