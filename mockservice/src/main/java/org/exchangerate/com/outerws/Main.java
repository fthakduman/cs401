package org.exchangerate.com.outerws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;


public class Main {
	public static void main(String args []) {
		JSONTokener tokener = null;
		try {
			tokener = new JSONTokener(new FileReader("ypkrediconfig.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        JSONObject object = new JSONObject(tokener);

	     
	        ProcessData data = new ProcessData(object);
	       JSONObject o = data.getMockRates();
	        
	
}
}