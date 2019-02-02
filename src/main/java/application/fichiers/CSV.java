package application.fichiers;


import application.chaines.*;
import application.elements.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author lea
 */
public class CSV {
    public final static String SEP = ";";
    
    static ArrayList<String> lecture(File f) throws FileNotFoundException, IOException{
        
    	BufferedReader reader = null;
        
    	ArrayList<String> res = new ArrayList<String>();
        String[] header = null;
        
        try {
            /**
             * Faire attention au chemin des fichiers et � celui du ou des
             * fichiers que l'utilisateur souhaite cr�er
             */
            reader = new BufferedReader(new FileReader(f));
            String line;
            
            // header du fichier CSV
            res.add(reader.readLine());
//          header = reader.readLine().split(SEP);
            
            // contenu du fichier
            while ((line = reader.readLine()) != null) {
                
//            	String[] dataLigne = line.split(SEP);
                res.add(line);
                
//                for (int i=0; i<dataLigne.length; i++) {
//                    /**
//                     * Ce n'est pas un affichage console qu'il faut faire mais
//                     * un affichage sur interface
//                     */
//                	
//      
//                	data.add(dataLigne[i]);
//                	
//                }
                
                return res;
//                res.put(header.toString(), data);
               // System.out.println(" ");
            }
         
        }catch(FileNotFoundException ex) {
                ex.printStackTrace();
        }finally {
                reader.close();
        }
        
        return res;
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
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	
    	List<String> test = CSV.lecture(new File("elements.csv"));
    	
    	test.forEach(v -> {
    		
//    		System.out.println(k);
    		System.out.println(v);
    	});
	}
}

