/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilitaire;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

/**
 *
 * @author daniel
 */
public class Utilitaire {

	
    
    /* permet l'auto completion depuis une liste d'element contenu dans le combo c, en se basant sur la chaine ch*/
   

   //actver les champs de text
    
   
   //desactive text field
   
    //vide text field
   
    //active button
    

   //desactive text field
   

   public  String convertStreamToString(InputStream is) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    while ((line = reader.readLine()) != null) {
      sb.append(line + "\n");
    }
    is.close();
    return sb.toString();
  }


public  File inputStreamToFile(InputStream is) {

    try {


	// write the inputStream to a FileOutputStream
	OutputStream out = new FileOutputStream(new File("fic"));

	int read = 0;
	byte[] bytes = new byte[1024];

	while ((read = is.read(bytes)) != -1) {
		out.write(bytes, 0, read);
	}

	is.close();
	out.flush();
	out.close();

	System.out.println("New file created!");
    } catch (IOException e) {
	System.out.println(e.getMessage());
    }
    return new File("fic");
  }

 public  String delete(String dir)
  // create a new directory or delete the contents of an existing one
  {
    File dirF = new File(dir);
    if (dirF.isDirectory()) {

      for (File f : dirF.listFiles())
        deleteFile(f);
    }
    return dirF.getAbsolutePath();
  }

   private  void deleteFile(File f)
  {
    if (f.isFile()) {
      boolean deleted = f.delete();
    if(deleted)
        System.out.println("  deleted: "+ f.getName() );
   
    }
  }
   
  /* methodes recursives de chargement d'arbre */
  /* Cette methode doit etre appeler apres l'initialisation de la classe Theme et de sa methode listeTheme*/
   
  
   
   
  
   /*fin  methodes recursives de chargement d'arbre */
   
   /* chargement de table */
   //charger une vector avec un fichier properties
  
   
  
   //methode qui extrait la date de l'arbre du planning
   public String extraitDate(String date){
       int index=date.indexOf("(");
       return date.substring(0, index);
       
   }
   public String extraiHeureDeb(String heure){
        int index=heure.indexOf("-");
        return heure.substring(0, index);   
   }
   public String extraiHeureFin(String heure){
        int index=heure.indexOf("-");
        return heure.substring(index+1, heure.indexOf("("));   
   }
     
   public String extraiNomProc(String Nom){
        int index=Nom.indexOf("(");
        return Nom.substring(0, index);   
   }
   
  
  }
