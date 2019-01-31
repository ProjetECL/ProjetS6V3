package application.fichiers;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

public class CSVV {

	/**
	 * 
	 * @param path
	 * @return CSVParser object
	 * @throws Exception
	 */
	public static CSVParser lecture(String path) {
		
		CSVParser reader = null;
		
		try {
			reader = CSVParser.parse(
					
					new File(path), 
					Charset.defaultCharset(), 
					CSVFormat.DEFAULT.withHeader());
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return reader;
	}
	
	/**
	 * 
	 * @param path
	 * @return CSVPrinter object
	 * @throws Exception
	 */
	public static CSVPrinter ecriture(String path) {
		
		CSVPrinter printer = null;
		
		try {
			
			printer = new CSVPrinter(new FileWriter(path), CSVFormat.EXCEL);
			
		 } catch (IOException ex) {
			 
		     ex.printStackTrace();
		 }
		
		return printer;
	}
	
//	public static void main(String[] args) {
//	
//		CSVV.lecture("elements.csv").forEach(csvRecord -> {
//			
//			System.out.println(csvRecord.toString());
//			System.out.println(csvRecord.toMap().get("code"));
//		});
//	}
}