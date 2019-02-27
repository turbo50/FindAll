package com.loc.findall;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class RechercheLieux extends Activity implements OnClickListener,OnItemSelectedListener {

	private Button boutonSuiv,boutonPre; 
	private Spinner spinnerPays,spinnerVille,spinnerTypeLieux,spinnerRegion;
	
	
	//private String[] array={"11","22","33","44","55","66","77","88","99","00","12","13","14","15","16","17","18","19","20"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recherche_lieu);
	
		spinnerPays=(Spinner)findViewById(R.id.spinner_F_Rech_L_Pays);  MainActivity.bdd.chargeSpinner(spinnerPays, "select name from pays",this);
		spinnerVille=(Spinner)findViewById(R.id.spinnerVille);
		spinnerTypeLieux=(Spinner)findViewById(R.id.spinnerTypedeLieux);MainActivity.bdd.chargeSpinner(spinnerTypeLieux, "select name from type order by name",this);
		spinnerRegion=(Spinner)findViewById(R.id.spinnerRegion);        MainActivity.bdd.chargeSpinner(spinnerRegion, "select name from region order by name",this);
		
		
				// gestion des événements
	
		spinnerRegion.setOnItemSelectedListener(
				new OnItemSelectedListener() {
					public void onNothingSelected(AdapterView<?> arg0) { }
					public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
						// code appelé lorsqu'un item est sélectionné
						selectSpinnerRegion();	
					}
				});		
				
		spinnerPays.setOnItemSelectedListener(
						new OnItemSelectedListener() {
							public void onNothingSelected(AdapterView<?> arg0) { }
							public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
								// code appelé lorsqu'un item est sélectionné
								
							}
						});		
		
		boutonPre=(Button)findViewById(R.id.button_f_Lieu_Pre);
		boutonPre.setOnClickListener(this);
		boutonSuiv=(Button)findViewById(R.id.button_f_Lieu_Suiv);
		boutonSuiv.setOnClickListener(this);
	}
	
	
	
	public void clickBoutonSuiv(){
		
		
	}
	public void clickBoutonPre(){
		Intent i=new Intent(getApplicationContext(), MainActivity.class);
		startActivityForResult(i, 1000);
		
		
	}
	

	@Override
	public void onClick(View v) {
		if(v.getId()==boutonPre.getId())
			clickBoutonPre();
		else if(v.getId()==boutonSuiv.getId())
			clickBoutonSuiv();
		
	}



	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	public void selectSpinnerRegion(){
		MainActivity.bdd.chargeSpinner(spinnerVille, "select name from ville where idRegion=(select idRegion from region where name='"+spinnerRegion.getSelectedItem().toString()+"') order by name",this);
		
	}
	
	

}
