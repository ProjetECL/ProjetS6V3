package application.fichiers;


import application.chaines.*;
import application.elements.*;
import java.io.*;
import java.io.File;
/**
 *
 * @author lea
 */
public class CSV {
    public final static String SEP = ";";
    
    public void lecture(File f) throws FileNotFoundException, IOException{
        BufferedReader reader = null;
        try {
            /**
             * Faire attention au chemin des fichiers et à celui du ou des
             * fichiers que l'utilisateur souhaite créer
             */
            reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dataLigne = line.split(SEP);
                for (int i=0; i<dataLigne.length; i++) {
                    /**
                     * Ce n'est pas un affichage console qu'il faut faire mais
                     * un affichage sur interface
                     */
                    System.out.print(dataLigne[i] + " ");
                }
                System.out.println(" ");
            }
        }catch(FileNotFoundException ex) {
                ex.printStackTrace();
        }finally {
                reader.close();
        }
    }
    
    public void ecriture(File f, Element e) throws IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("/CSV/chaines.csv"), "utf-8"));
            writer.newLine();
            writer.write(e.toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            writer.close();
        }
        
    }
    public void ecriture(File f, ChProduction cp)throws IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("/CSV/chaines.csv"), "utf-8"));
            writer.newLine();
            writer.write(cp.toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            writer.close();
        }
    }
}

