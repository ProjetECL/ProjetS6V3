package application.fichiers;


import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;

import application.chaines.ChProduction;
import application.elements.Element;
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


	static void ecriture(File f, Element e) throws IOException{
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("chaines.csv"), "utf-8"));
			writer.newLine();
			writer.write(e.toString());
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			writer.close();
		}

	}
	static void ecriture(File f, ChProduction cp)throws IOException{
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("chaines.csv"), "utf-8"));
			writer.newLine();
			writer.write(cp.toString());
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			writer.close();
		}
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