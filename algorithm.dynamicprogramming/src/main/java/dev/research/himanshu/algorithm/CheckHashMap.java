package dev.research.himanshu.algorithm;

import java.util.HashMap;
import java.util.Map;

public class CheckHashMap {
	public static void main(String[] args) {
		Map<City, String> map = new HashMap<>();
		map.put(new City("hello"),"this");
		map.put(new City("hello"),"that");
		map.put(new City("other"),"dasdasd");
		
		System.out.println(map.size());
	}
}

class City {
	private String name;
	
	public City(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.name == ((City)obj).name;
	}
	
	@Override
	public int hashCode() {
		return -1;
	}
}
