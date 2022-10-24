package main;
import java.lang.Math;

public class RandomOTP {
	public static String getNOTP(int n) {
		String opt = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) { 
			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index = (int)(opt.length() * Math.random()); 
			sb.append(opt.charAt(index)); 
		} 
		return sb.toString();
	}
}
