package com.ex;

import java.io.BufferedWriter;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

@SuppressWarnings("unused")
public class Geeks {
	private String testName = "Geeks Base";
	
	public void testCase1(){
		try {
			Class<?> gtc = Class.forName("com.ex.GeeksTest");
			GeeksTest gt = (GeeksTest)gtc.newInstance();
			Class<? extends GeeksTest> cl = gt.getClass();
			
			@SuppressWarnings("rawtypes")
			Class[] parameters = new Class[] { };
			Method getInstance = gtc.getMethod("getInstance", parameters); 
			
			Method test = gtc.getMethod("test", parameters);
			Object arguments[] = {};
			Object obj = getInstance.invoke(null, null);
			test.invoke(obj, null);
			//Class<?> cacheClass = Class.forName("com.hp.toms.common.helper."+cacheClassName+"Helper");
			//Class[] parameters = new Class[] { Map.class, BufferedWriter.class };
			//Method method  = cacheClass.getMethod(methodName, parameters);
			//Object arguments[] = {cacheMap,out};
			//method.invoke(null, (Object[])arguments);
			
			System.out.println("--- testCase 1 over");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void testCase2(){
		try {

			
			System.out.println("--- testCase 2 start...");
			GeeksTest gt1 = new GeeksTest("n1","1");
			GeeksTest gt2 = new GeeksTest("n2","2");
			
			System.out.println(gt1);
			System.out.println(gt2);
			
			
			GeeksTest gt3 = gt1;
			gt1=gt2;
			gt2=gt3;
			System.out.println(gt1);
			System.out.println(gt2);
			System.out.println("--- testCase 2 over");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void testCase3(){
		System.out.println("--- testCase 3 start...");
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("key 1", "value 1");
		
		List<String> list1 = new ArrayList<String>();
		list1.add("element 1");
		list1.add("element 2");
		list1.add("element 3");
		
		Set<String> set1 = new HashSet<String>();
		set1.add("set element #1");
		//Set setA = new EnumSet();
		//Set setB = new HashSet();
		//Set setC = new LinkedHashSet();
		//Set setD = new TreeSet();
		
		ArrayDeque<String> ad1 = new ArrayDeque<String>();
		ad1.add("deque element #1");
		
		Vector<String> vc1 = new Vector<String>();
		vc1.add("vector #1");
		
		Iterator<String> iter = list1.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		Iterator<String> iter2 = list1.listIterator();
		while(iter2.hasNext()){
			String elm = iter2.next();
			System.out.println(" --- remove : " + elm);
			iter2.remove();
		}
	}
	
}
