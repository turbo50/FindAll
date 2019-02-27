package com.loc.findall;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CarteItineraire extends Activity implements OnClickListener {
	private String[] rayon={"20","40","80","120","150","200","300","400","500","600","700","800","900","1000","1200","1500","2000"};
	private Button boutonSuiv,boutonPre; 
	private Spinner spinnerRayon;
	private TextView textViewDepart,textViewArrive;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carte_itineraire);
		
		//On fixe les donnees provenant de la page precedente
		//String depart=
		spinnerRayon=(Spinner)findViewById(R.id.spinnerRayonAlerte);
		textViewArrive=(TextView)findViewById(R.id.textViewSe_Ville);
		textViewDepart=(TextView)findViewById(R.id.textView12);
		
		ArrayAdapter<String> aspnMusketeers =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,rayon);
		aspnMusketeers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerRayon.setAdapter(aspnMusketeers);
				
		//On récupère le départ et l'arrivée et on l'affiche 
		final String editDepart = getIntent().getStringExtra("LieuDepart");
		final String editArrivee = getIntent().getStringExtra("LieuArrive");
		textViewArrive.setText(editArrivee);
		textViewDepart.setText(editDepart);
		/*
		DataBaseHelper_2 db=new DataBaseHelper_2(this);
		double latD=Double.parseDouble(db.executeRequette("select lat from ville where name='"+editDepart+"'", null));
		double latA=Double.parseDouble(db.executeRequette("select lat from ville where name='"+editArrivee+"'", null));
		double lonD=Double.parseDouble(db.executeRequette("select longi from ville where name='"+editDepart+"'", null));
		double lonA=Double.parseDouble(db.executeRequette("select longi from ville where name='"+editArrivee+"'", null));
		GeoCode geo=new GeoCode(latD, lonD, this);
		geo.start(); String adrD=geo.getAdresse();
		geo=new GeoCode(latA, lonA, this);
		geo.start(); String adrA=geo.getAdresse();
		Toast t=new Toast(this);
		t.makeText(this, adrD+"\n"+adrA, Toast.LENGTH_LONG);
		*/
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
