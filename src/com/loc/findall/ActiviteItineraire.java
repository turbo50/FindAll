package com.loc.findall;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;

public class ActiviteItineraire extends Activity implements OnClickListener {
	
	private Button boutonSuiv,boutonPre; 
	private AutoCompleteTextView autoC_Depart,autoC_Arrive;
	private CheckBox chexBoxFiltreD,chexBoxFiltreA;
	String req2="select name from place where name is not null and name!=''  union select name from ville order by name";
	String req1="select name from place where name is not null and name!=''  and idVille=(select idVille from ville where name=(select ville from mespreferences))";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulaire_itineraire);
		
		boutonPre=(Button)findViewById(R.id.button_iti_Prec);
		boutonPre.setOnClickListener(this);
		boutonSuiv=(Button)findViewById(R.id.button_iti_Suiv);
		boutonSuiv.setOnClickListener(this);
		
		autoC_Depart=(AutoCompleteTextView)findViewById(R.id.autoCompletePointDepart);
		autoC_Arrive=(AutoCompleteTextView)findViewById(R.id.autoCompletePointArrivee);
		
		chexBoxFiltreD=(CheckBox)findViewById(R.id.checkBoxFiltrerPreference_PtDepart);
		chexBoxFiltreD.setOnClickListener(this);
		chexBoxFiltreA=(CheckBox)findViewById(R.id.CheckBoxFiltrerPreferenceArrive);
		chexBoxFiltreA.setOnClickListener(this);
		
		
		//Chargement des lieux (depart/arrive)
		String[] tabD=MainActivity.bdd.getTableau_De_(req2);
		ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabD);
		adapterD.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Depart.setAdapter(adapterD);
		
		String[] tabA=MainActivity.bdd.getTableau_De_(req2);
		ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabA);
		adapterA.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Arrive.setAdapter(adapterA);
	}
	
	 // methodes de gestion des clicks
	public void clickBoutonSuiv(){
			Intent i=new Intent(getApplicationContext(), CarteItineraire.class);
			i.putExtra("LieuDepart", autoC_Depart.getText().toString());
			i.putExtra("LieuArrive", autoC_Arrive.getText().toString());
			startActivityForResult(i, 1000);
					
		
	}
	
	public void clickBoutonPre(){
		Intent i=new Intent(getApplicationContext(), MainActivity.class);
		startActivityForResult(i, 1000);
		
		
	}
	
	public void clickCheckBoxDepart(String check){
		
		String sqlRequette= (check.equalsIgnoreCase("check"))? req1:req2;
		String[] tabD=MainActivity.bdd.getTableau_De_(sqlRequette);
		ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabD);
		adapterD.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Depart.setAdapter(adapterD);
		System.out.println("check sur click depart");
	}
	
	public void clickCheckBoxArrive(String check){
		String sqlRequette= (check.equalsIgnoreCase("check"))? req1:req2;
		String[] tabA=MainActivity.bdd.getTableau_De_(sqlRequette);
		ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,tabA);
		adapterD.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		autoC_Arrive.setAdapter(adapterD);
		System.out.println("check sur click arrive");
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==boutonPre.getId())
			clickBoutonPre();
		else if(v.getId()==boutonSuiv.getId())
			clickBoutonSuiv();
		else if(v.getId()==chexBoxFiltreA.getId()){
			 if(chexBoxFiltreA.isChecked())
			     clickCheckBoxArrive("check");
			 else
				clickCheckBoxArrive("!check");
		}
			
		else if(v.getId()==chexBoxFiltreD.getId()){
			 if(chexBoxFiltreD.isChecked())
			     clickCheckBoxDepart("check");
			 else
				 clickCheckBoxDepart("!check");
		}
			
		
		
	}
	

}
