package com.ex;

public class GeeksTest {
	private String name = null;
	
	private String value = null;

	public GeeksTest(){
	}
	
	public GeeksTest(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void test(){
		System.out.println("  GeeksTest test is done ...");
	}
	
	public static GeeksTest getInstance(){
		return new GeeksTest();
	}
	
	public String toString(){
		return "name:" + name + " value:"+ value;
	}
}
