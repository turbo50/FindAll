package com.loc.findall;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loc.findall.database.DataBaseHelper_2;




public class MainActivity extends Activity implements OnClickListener {


	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	//private Menu menu;
	//private MenuItem menuItemIti,menuItemRechLieux,menuRechLieuxPro;
	private Button boutonItineraire,boutonRechercheLieu,boutonModifier;
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	public static DataBaseHelper_2 bdd;
	private AutoCompleteTextView autoC_Pays,autoC_Ville,autoC_Region;
	private TextView textViewRegion;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.accueil); 
		
		bdd=new DataBaseHelper_2(this);
		try{
			bdd.openDataBase();
		}catch(Exception e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
		}
		
		
		//obtention des widget
		boutonModifier=(Button)findViewById(R.id.buttonModifierPreference);
		boutonModifier.setOnClickListener(this);
		boutonItineraire=(Button)findViewById(R.id.buttonItineraire);
		boutonItineraire.setOnClickListener(this);
		boutonRechercheLieu=(Button)findViewById(R.id.buttonRechelieu);
		boutonRechercheLieu.setOnClickListener(this);
		autoC_Pays=(AutoCompleteTextView)findViewById(R.id.autoCompletePointDepart);
		
		autoC_Ville=(AutoCompleteTextView)findViewById(R.id.autoCompletePreferenceVille);
		
		autoC_Pays.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		    		int position, long id) {
		    	// TODO Auto-generated method stub
	            selectItemPays();
		    	
		    }@Override
		    	public void onNothingSelected(AdapterView<?> parent) {
		    		// TODO Auto-generated method stub
		    		
		    	}
			
			});
		autoC_Region=(AutoCompleteTextView)findViewById(R.id.autoCompletePreferenceRegion);
	    autoC_Region.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> parent, View view,
	    		int position, long id) {
	    	// TODO Auto-generated method stub
            selectItemRegion();
	    	
	    }@Override
	    	public void onNothingSelected(AdapterView<?> parent) {
	    		// TODO Auto-generated method stub
	    		
	    	}
		
		});
		
		//On affiche les preferences
		setPreference();
		//Obtention des liste depuis la bdd
		String[] tabP=bdd.getTableau_De_("select name from pays");
		
		
		ArrayAdapter<String> adapterP = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabP);
		adapterP.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Pays.setAdapter(adapterP); 
		
		
		String[] tabR=bdd.getTableau_De_("select name from region");
		ArrayAdapter<String> adapterR = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabR);
		adapterR.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Region.setAdapter(adapterR); 
					
		
		String[] tabV=bdd.getTableau_De_("select name from ville ");
		ArrayAdapter<String> adapterV = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabV);
		adapterV.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Ville.setAdapter(adapterV); 	
		
		
		
		
	}
	
	
    // methodes de gestion des clicks
	public void clickItineraire(){
		Intent i=new Intent(getApplicationContext(), ActiviteItineraire.class);
		startActivityForResult(i, 1000);
		
		
	}
	//chargement des preferences
	public void setPreference(){
		Cursor c=bdd.executeRequette_Select("select pays,region,ville from mespreferences", null); ArrayList<String> arl=new ArrayList<String>();
    	if(c.getCount()>0){
    		c.moveToFirst();
    		do{
    			autoC_Pays.setText(c.getString(0));
    			autoC_Region.setText(c.getString(1));
    			autoC_Ville.setText(c.getString(2));
    		}while(c.moveToNext());
    	}
    		
	}
	
	//Modifications des preferences dans la bdd
	public void clickModifier(){
		/*String pays=autoC_Pays.getText().toString();
		String Region=autoC_Region.getText().toString();
		String Ville=autoC_Ville.getText().toString();
		
		ContentValues values=new ContentValues();
		values.put("Pays", pays);
		values.put("Region", Region);
		values.put("Ville",Ville);
		DataBaseHelper_2.getSqliteDataBase().update("mespreferences", values, null, null);
		*/
		lanceGPS();
		
	}
	
	public void lanceGPS(){
		// Fournisseurs de service
		LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		List<String> fournisseurs = manager.getAllProviders();
		for (String f : fournisseurs){
			//Toast.makeText(getApplicationContext(), "" + f, Toast.LENGTH_SHORT).show();
			//if (f.toString().equals(LocationManager.))
			   System.out.println("fournisseur :"+f);
		}
		
		// Récupération de la localisation
		Location localisation = manager.getLastKnownLocation("passive");
		//Toast.makeText(getApplicationContext(), "Lattitude" + localisation.getLatitude(), Toast.LENGTH_SHORT).show();
		//Toast.makeText(getApplicationContext(), "Longitude" + localisation.getLongitude(), Toast.LENGTH_SHORT).show();
		System.out.println("Lattitude " + localisation.toString());
		//System.out.println("Longitude " + localisation.getLongitude());
		// Ecouteur de changement de localisation
		manager.requestLocationUpdates("passive", 6000, 100, new LocationListener() {
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		public void onProviderEnabled(String provider) {
		}
		public void onProviderDisabled(String provider) {
		}
		public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		}
		});
	}
	
	public void clickRechercheLieu(){
		Intent i=new Intent(getApplicationContext(), RechercheLieux.class);
		startActivityForResult(i, 1000);
		
		
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==boutonItineraire.getId())
			clickItineraire();
		else if(v.getId()==boutonRechercheLieu.getId())
			clickRechercheLieu();
		else if(v.getId()==boutonModifier.getId())
			clickModifier();
		
	}



    public void selectItemPays(){
    	String[] tabR=bdd.getTableau_De_("select name from region");
		ArrayAdapter<String> adapterR = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabR);
		adapterR.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Region.setAdapter(adapterR);
    }
    
	public void selectItemRegion(){
		String[] tabV=bdd.getTableau_De_("select name from ville where idRegion=(select idRegion from region where name='"+autoC_Region.getText().toString()+"')");
		ArrayAdapter<String> adapterV = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabV);
		adapterV.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Ville.setAdapter(adapterV);
		System.out.println("selection region");
	}



}
