package com.loc.findall.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LesPlaces_DAO {

	private SQLiteDatabase maBaseDonnees;
	private DataBaseHelper_2 baseHelper;
	public LesPlaces_DAO(Context ctx) {
		baseHelper = new DataBaseHelper_2(ctx);
	}
	
	public SQLiteDatabase open() {
		try{
			baseHelper.openDataBase();
		}catch(Exception e){
			e.printStackTrace();
		}
		maBaseDonnees=DataBaseHelper_2.getSqliteDataBase();
		return maBaseDonnees;
	}
	
	/*public SQLiteDatabase open() {
		try{
			baseHelper.createDatabase();
		    baseHelper.openDatabase();
		}catch(Exception e){
			e.printStackTrace();
		}
		maBaseDonnees=baseHelper.getMyDataBase();
		return maBaseDonnees;
	}*/
	public void close() {
		maBaseDonnees.close();
	}
	/**
	* Récupère une planète en fonction de son nom.
	* @param nom
	* Le nom de la planète à retourner.
	* @return La planète dont le nom est égale au paramètre 'nom'.
	*/
	public ArrayList<LesPlaces> getAllPlaces() {
		
		Cursor c = maBaseDonnees.query(Constance.TABLE_PLACES, new String[] {
				Constance.COLONE_TABLE_PLACES_ID, Constance.COLONE_TABLE_PLACES_NOM }, null, null, null,
				null, null);
		return cursorToLesPlaces(c);
	}
	
	private ArrayList<LesPlaces> cursorToLesPlaces(Cursor c) {
		// Si la requête ne renvoie pas de résultat
		if (c.getCount() == 0)
			return new ArrayList<LesPlaces>(0);
		ArrayList<LesPlaces> retPlanetes = new ArrayList<LesPlaces>(c.getCount());
		c.moveToFirst();
		do {
			LesPlaces places = new LesPlaces();
			places.setId(c.getInt(0));
			places.setNom(c.getString(1));
			
			retPlanetes.add(places);
		} while (c.moveToNext());
		// Ferme le curseur pour libérer les ressources
		c.close();
		return retPlanetes;
	}
	
	public List<String> getListeNomPlace(ArrayList<LesPlaces> arl){
		List<String> l;
		ArrayList< String> ar=new ArrayList<String>();
		for(int i=0; i<arl.size(); i++){
			ar.add(arl.get(i).getNom());			
		}
		l=ar;
		return l;
	}
	
	public long insertPlanete(LesPlaces places) {
		ContentValues valeurs = new ContentValues();
		valeurs.put(Constance.COLONE_TABLE_PLACES_NOM, places.getNom());
		return maBaseDonnees.insert(Constance.TABLE_PLACES, null, valeurs);
	}
	public void videBase(){
		baseHelper.onUpgrade(maBaseDonnees, 1, 2);
		
	}
	
}
