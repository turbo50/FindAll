package com.loc.findall;

import java.util.ArrayList;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

public class TestGPS {
	
      public void lanceTout(){
    	/*  LocationManager locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);

    	  ArrayList<LocationProvider> providers = new  ArrayList<LocationProvider>();
    	  ArrayList<String> names = (ArrayList<String>)locationManager.getProviders(true);
    	  for(String name : names){
    		  providers.add(locationManager.getProvider(name));
    		  System.out.println(name+"\n");
    	  }
    		 
    	  
    	  Criteria critere = new Criteria();
    	// Pour indiquer la pr�cision voulue
    	// On peut mettre ACCURACY_FINE pour une haute pr�cision ou
    	//ACCURACY_COARSE pour une moins bonne pr�cision
    	critere.setAccuracy(Criteria.ACCURACY_FINE);
    	// Est-ce que le fournisseur doit �tre capable de donner une
    	//altitude ?
    	critere.setAltitudeRequired(true);
    	// Est-ce que le fournisseur doit �tre capable de donner une
    	//direction ?
    	critere.setBearingRequired(true);
    	// Est-ce que le fournisseur peut �tre payant ?
    	critere.setCostAllowed(false);
    	// Pour indiquer la consommation d'�nergie demand�e
    	// Criteria.POWER_HIGH pour une haute consommation,
    	//Criteria.POWER_MEDIUM pour une consommation moyenne et
    	//Criteria.POWER_LOW pour une basse consommation
    	critere.setPowerRequirement(Criteria.POWER_HIGH);
    	// Est-ce que le fournisseur doit �tre capable de donner une
    	//vitesse ?
    	critere.setSpeedRequired(true);
    	
    	
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
    			60000, 150, new LocationListener() {
    			@Override
    			public void onStatusChanged(String provider, int status, Bundle
    			extras) {
    			}
    			@Override
    			public void onProviderEnabled(String provider) {
    			}
    			@Override
    			public void onProviderDisabled(String provider) {
    			}
    			@Override
    			public void onLocationChanged(Location location) {
    				Log.d("GPS", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
    			}
				
				
    			});*/
    	  
    	  
    	  
      }

	
	  
}
