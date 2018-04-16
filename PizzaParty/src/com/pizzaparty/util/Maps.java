package com.pizzaparty.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.pizzaparty.geocoding.GeocodeResponse;

/**
 * Class for interacting with the Google Maps Geocode API
 * @author adambowker
 *
 */
public class Maps {
	public static final String KEY_PATH = "maps_api_key.txt";
	public static final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
	
	private String address;
	private String key;
	private String json;
	
	public Maps(String address) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(KEY_PATH));
		 this.key = br.readLine();
		this.address = address.replaceAll(" ", "+");
	}
	
	public GeocodeResponse getGeocode() {
		String call = BASE_URL + "address=" + this.address + "&key=" + this.key;
		
		try {
			Scanner reader = new Scanner(new URL(call).openStream(), "UTF-8");
			reader.useDelimiter("\\A");
			this.json = reader.next();
			reader.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson g = new Gson();
		return g.fromJson(this.json, GeocodeResponse.class);
	}
	
}
