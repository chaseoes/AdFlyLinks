package me.chaseoes.adflylinks;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class AdFlyShortener {
	
	String url;
	
	public AdFlyShortener(String u) {
			url = u;
	}
	
	public String shorten() {
		try {
			InputStream i = new URL(url).openStream();
			Scanner scan = new Scanner(i);
			String s = scan.nextLine();
			scan.close();
			return s;
		} catch (Exception e) {
			return null;
		}
	}

}
