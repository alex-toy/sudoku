package gui;

import java.net.URL;

import javax.swing.ImageIcon;

public class Utils {

	
	
	public static String getFileExtension(String name) {

		int pointIndex = name.lastIndexOf(".");
		
		if(pointIndex == -1) {
			return null;
		}
		if(pointIndex == name.length()-1) {
			return null;
		}
		return name.substring(pointIndex+1, name.length());
	}
	
	
	
	public ImageIcon createIcon(String path) {
			ImageIcon icon;
			URL url = getClass().getResource(path);
			if(url == null){
		      System.out.println("the url is actually " + url);
		      System.err.println("unable to load image: " + path);
		      return null;
		   }
		   else {
		      icon = new ImageIcon(url);
		      return icon;
		   }
	}
	
	
}








