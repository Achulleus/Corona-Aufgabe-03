package uebungMitConsole;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class berechnung {
	
	private static int tage = 14;
	private static int werte = 10;
	private static int gesSumme = 0;
	private static int summe = 0;
	private static String[][] arr = new String[tage][werte];

	public static void main(String[] args) {
		Console con = System.console();
		Random ran = new Random();
		Path file = Paths.get("/Users/philipp/Documents/Htl/3.Klasse/Köllö/measurement.txt");
		byte data[];
		ByteBuffer out;
		boolean filecheck = false;
		
		if(con != null) {
			File measurements = new File("measurments.txt");
		    file = Paths.get("measurments.txt");

		    if(Files.exists(file)) {
		    	filecheck = true;
		    } else {
		        measurements = new File("measurments.txt");
		        filecheck = false;
		    }	
		    
		    Properties props = System.getProperties();
		    
			if(Files.exists(file)){ 
				con.printf(file.toString() +" existiert.");
				}else {
					try {
						Files.createDirectories(file.getParent());
						Files.createFile(file);
						FileWriter writer = new FileWriter("measurement.txt");
						writer.write("Temperaturen in Grad Celsius");
				        writer.write("\n");
				        writer.write("----------------------------");
				        writer.write("\n");
						for(int i = 0; i<tage; i++) {
							for(int j = 0; j<werte; j++) {
								int k = ran.nextInt(16) + 20;
								String s = Integer.toString(k);
								arr[i][j] = s;
							}
						}
						for(int i = 0; i<tage; i++) {
							for(int j = 0; j<werte; j++) {
								int k = Integer.parseInt(arr[i][j]);
								summe = summe + k;
								con.printf(arr[i][j]+",");
								writer.write(arr[i][j]+",");
								if(j == werte -1) {
									con.printf("     Durchschnitt: "+ summe/werte);
									writer.write("     Durchschnitt:"+ summe/werte);
									gesSumme = gesSumme + summe;
									summe = 0;
									System.out.println();
									writer.write("\n");
								}
								if(j == werte-1 && i == tage-1) {
									System.out.println();
									writer.write("\n");
									con.printf("Gesamt-Durchschnitts-Temperatur: "+gesSumme/(werte*tage));
									writer.write("Gesamt-Durchschnitts-Temperatur: "+gesSumme/(werte*tage));
								}								}
						}
						DateFormat dateFormatDays = new SimpleDateFormat("dd/MM/yyyy");
			            DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
			            Date date = new Date(); 
			            writer.write(dateFormatDays.format(date) + " um " + dateFormatTime.format(date));
			            writer.write("\n");
			            writer.write(props.getProperty("os.name"));
			            writer.write(" Version "  + props.getProperty("os.version"));

			            writer.close();
						}catch(IOException x) {
							System.err.println(x);
						}
				}
		}else {
			System.out.println("Keine Konsole vorhanden!");
		}
	}
}
