package com.wes.webflux.nicetry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingletonInstance {
	
	
	private  static SingletonInstance instance  = new SingletonInstance();
	
	SingletonInstance() {}
	
	public static SingletonInstance getInstance() {
		if(instance == null) {
			instance = new SingletonInstance();
		}
		
		return instance;
	}
	 	
		
	
	}
	
	

