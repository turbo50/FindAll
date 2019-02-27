package com.loc.findall.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DataBaseHelper_2 extends SQLiteOpenHelper{
		    //The Android's default system path of your application database.
		    private static String DB_PATH = "/data/data/com.loc.findall/databases/";
		    //private static String DB_PATH = "context.getApplicationInfo().dataDir/databases/";
		    // Data Base Name.
		    private static final String DATABASE_NAME = "Cameroun.sqlite";
		    // Data Base Version.
		    private static final int DATABASE_VERSION = 1;
		    // Table Names of Data Base.
		    static final String TABLE_Name1 = "tablename1";
		    static final String TABLE_Name2 = "tablename2";
		    static final String TABLE_Name3 = "tablename3";
		    static final String TABLE_Name4 = "tablename4";
		    public Context context;
		    static SQLiteDatabase sqliteDataBase;
		    
		    
		    public DataBaseHelper_2(Context context) {
				super(context, DATABASE_NAME, null ,DATABASE_VERSION);
				this.context = context;
		    }
		    
		    @Override
		    public void onCreate(SQLiteDatabase arg0) {
		    }
		    
		    //check if the database exists
		    public void createDataBase() throws IOException{
				boolean databaseExist = checkDataBase();
				if(databaseExist){
				// Do Nothing.
					System.out.println("La base existe deja");
				}else{
					this.getWritableDatabase();
					copyDataBase();
					// TODO Auto-generated catch block
					System.out.println("La base a ete cree");
				}
		    }// end if else dbExist
		    // end createDataBase().
		    
		    public boolean checkDataBase(){
				File databaseFile = new File(DB_PATH + DATABASE_NAME);
				return databaseFile.exists();
		    }
		    
		    private void copyDataBase() throws IOException{
				//Open your local db as the input stream
				InputStream myInput = context.getAssets().open(DATABASE_NAME);
				// Path to the just created empty db
				String outFileName = DB_PATH + DATABASE_NAME;
				//Open the empty db as the output stream
				OutputStream myOutput = new FileOutputStream(outFileName);
				//transfer bytes from the input file to the output file
				byte[] buffer = new byte[1024];
				int length;
				while ((length = myInput.read(buffer))>0){
					myOutput.write(buffer, 0, length);
				}
				//Close the streams
				myOutput.flush();
				myOutput.close();
				myInput.close();
		    }
		    
		    /**
		    * This method opens the data base connection.
		    * First it create the path up till data base of the device.
		    * Then create connection with data base.
		    */
		    public void openDataBase() throws SQLException{
				//Open the database
				try {
					createDataBase();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String myPath = DB_PATH + DATABASE_NAME;
				sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		    }
		    
		    /**
		    * This Method is used to close the data base connection.
		    */
		    @Override
		    public synchronized void close() {
				if(sqliteDataBase != null)
					sqliteDataBase.close();
				super.close();
		    }
		    
		    //declare methods to fetch data
		    /**
		     * 
		     * @param sqlRequette requêtte sql à executer
		     * @param t tableau des paramètres de la clause where
		     * @return renvoie un curseur 
		     */
		    public Cursor executeRequette_Select(String sqlRequette,String[] t){
				return sqliteDataBase.rawQuery( sqlRequette, t);
		    }
		    
		    public String executeRequette(String sqlRequette,String[] t){
				Cursor c= sqliteDataBase.rawQuery( sqlRequette, t);
				String rep=null;
				if(c.getCount()>0){
		    		c.moveToFirst();
		    		do{
		    			rep=c.getString(0);
		    		}while(c.moveToNext());
		    	}
				return rep;
		    		
		    }
		    
		    //charge un spinner depuis une base de donnees
		    
		    public void chargeSpinner(Spinner spinner,String sqlRequette,Context context){
		    	
		    	Cursor c=executeRequette_Select(sqlRequette, null); ArrayList<String> arl=new ArrayList<String>();
		    	if(c.getCount()>0){
		    		c.moveToFirst();
		    		do{
		    			arl.add(c.getString(0));
		    		}while(c.moveToNext());
		    		
		    		ArrayAdapter<String> aspnMusketeers =new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,arl);
					aspnMusketeers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinner.setAdapter(aspnMusketeers);
		    	}
		    	
				
			}
		    
		    /**
		     * 
		     * @param sqlRequette
		     * @return String[]
		     * Cette methode retourne un tableau contenant la liste, resultat de la requette "sqlRequette"
		     */
		    
		    public String[] getTableau_De_(String sqlRequette){
		    	String[] tab;
		    	Cursor c=executeRequette_Select(sqlRequette, null);
		    	tab=new String[c.getCount()]; int i=0;
				if(c.getCount()>0){
				    c.moveToFirst();
				    do{
				    	tab[i]=c.getString(0);
				    	i++;
				    }while(c.moveToNext());
				}
				return tab;
			}
		    
		    @Override
		    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    // TODO Auto-generated method stub
		    }
		    
			public static SQLiteDatabase getSqliteDataBase() {
				return sqliteDataBase;
			}
			public static void setSqliteDataBase(SQLiteDatabase sqliteDataBase) {
				DataBaseHelper_2.sqliteDataBase = sqliteDataBase;
			}
		    
    }