package mega.utils;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils implements Serializable{
	
	
	
	public static String DATA_PATH = "./data";
	public static boolean SAVE_DIRECTORY_SETTED = false;
	public static Random RANDOM = new Random();
	
	private Utils() { 
		
	}
	
	
	public static void serialize(String name,Object obj) { 
		
			try {
				FileOutputStream fos = new FileOutputStream(DATA_PATH+"/"+name);
				try {
					ObjectOutputStream serializer = new ObjectOutputStream(fos);
					
					serializer.writeObject(obj);
					
					serializer.close();

					//System.out.println("Joueur Enregistr� !");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	public static Object deserialize(String name) { 
		
		try {
			FileInputStream fis = new FileInputStream(DATA_PATH+"/"+name);
			try {
				ObjectInputStream deserializer = new ObjectInputStream(fis);
				
					Object o = (deserializer.readObject());
					
					deserializer.close();
					fis.close();
					
					
					if(o instanceof HashMap) { 
						return o;
					}
					
					//System.out.println("Joueurs Charg�s !");
					
					return o;
					
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
	
	
	public static void vueTester(JPanel panel) { 
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.add(panel);
		window.pack();
	}
	
	
	public static String dateToString(String format,Date date) { 
		SimpleDateFormat sdf;
		if(format!=null) { 
		 sdf = new SimpleDateFormat(format);
		}
		else {
			sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		}
		return sdf.format(date);
	}
	
	
	public static void pleinEcran(JFrame window) { 
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gc = g.getDefaultScreenDevice();
		GraphicsConfiguration gcc = gc.getDefaultConfiguration();
		gc.setFullScreenWindow(window);
	}
	
	public static void debugConsole(String location,String message) { 
		 System.out.println(location);
		 System.out.println(message);
	}
	
	
	public static BufferedImage loadIMGTest(String path) { 
		try {
			return ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage loadIMG(String path) { 
		try {
			return ImageIO.read(Utils.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static boolean FileExists(String path) { 
		return new File(DATA_PATH+"/"+path).exists();
	}
	
}
