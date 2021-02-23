package com.mega.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Utils {
	
	
	
	public static final String PATH = "./data";
	
	
	private Utils() { 
		
	}
	
	public static void serialize(String name,Object obj) { 
		
			try {
				FileOutputStream fos = new FileOutputStream(PATH+"/"+name);
				try {
					ObjectOutputStream serializer = new ObjectOutputStream(fos);
					
					serializer.writeObject(obj);
					
					serializer.close();

					System.out.println("Joueur Enregistré !");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	public static Object deserialize(String name) { 
		
		try {
			FileInputStream fis = new FileInputStream(PATH+"/"+name);
			try {
				ObjectInputStream deserializer = new ObjectInputStream(fis);
				
					Object o = (deserializer.readObject());
					
					if(o instanceof HashMap) { 
						return o;
					}
				System.out.println("Joueurs Chargés !");
				deserializer.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
}
	
	
}
