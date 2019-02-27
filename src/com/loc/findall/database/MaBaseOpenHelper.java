package com.loc.findall.database;

import java.io.File;

import utilitaire.Utilitaire;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseOpenHelper extends SQLiteOpenHelper {
	private static final String REQUETE_CREATION_TABLE = "create table "+Constance.TABLE_PLACES+"("+Constance.COLONE_TABLE_PLACES_ID+" integer not null primary key autoincrement,"+Constance.COLONE_TABLE_PLACES_NOM+" text not null);";
	//private static final String REQUETE_CREATION_BASE="create database "+Constance.DATABASE_NAME+";";
	private static final String REQUETE_INSERTION_LESPLACES="insert into "+Constance.TABLE_PLACES+"("+Constance.COLONE_TABLE_PLACES_NOM+") values('Yaounde'),('Douala'),('Maroua');";
	private Context co;
	public MaBaseOpenHelper(Context context, String nom,
			CursorFactory cursorfactory, int version) {
		super(context, nom, cursorfactory, version);
		co=context;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Log.i(this.toString(),"Base en creation");
		//db.execSQL(REQUETE_CREATION_BASE);
		//db.execSQL(REQUETE_CREATION_TABLE);
		//db.execSQL(REQUETE_INSERTION_LESPLACES);
		//Log.i(this.toString(),"Base prete");
		File f=null;
		try{
			f=new Utilitaire().inputStreamToFile(co.getResources().getAssets().open("Cameroun.sqlite"));
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		db.openDatabase(f.getAbsolutePath(), null,android.database.sqlite.SQLiteDatabase.OPEN_READONLY);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Dans notre cas, nous supprimons la base et les données pour en
		// créer une nouvelle ensuite. Vous pouvez créer une logique de mise
		// à jour propre à votre base permettant de garder les données à la
		// place.
		db.execSQL("drop table  " + Constance.TABLE_PLACES+ ";");
		// Création de la nouvelle structure.
		onCreate(db);
	}
}