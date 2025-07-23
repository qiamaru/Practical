package com.example.demoMirul.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Data {

	private Data() {
	// Prevent instantiation
	}

	private static final Map<Long, String> dataStore = new ConcurrentHashMap<>();

	public static Map<Long, String> getDataStore() {
	    return dataStore;
	}
	
	private static final AtomicLong idCounter = new AtomicLong();
	public static AtomicLong getIdCounter() {
	    return idCounter;
	}
	
}