
/*********************************************************************
**																	**
** MODULES USED 													**
** 																	**
**********************************************************************/
package broad.car.broadcar.map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import broad.car.broadcar.MainActivity;
import broad.car.broadcar.R;
import broad.car.broadcar.alerts.AlertManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/** @addtogroup Broadcar
*
* @{

* @file googleMap
* @brief Esta clase se encarga del mapa
* de hacer la localización y modificar la vista del mapa
* @par VERSION HISTORY
* Version : v0.0
* Date : 30/01/2013
* Revised by : BroadCar team
* Description : Original version.
*
* @}
*/

public class googleMap extends Activity implements LocationListener{

	/*********************************************************************
	** 																	**
	** IMPORTED CLASSES / Declarations  								**
	** 																	**
	**********************************************************************/
	GoogleMap googleMap;
	LocationManager locationManager;
	String proveedor;
	Location loc; //localización
	LocationListener locListener;//listener para el cambio de posición
	CameraUpdate camUpd1; // para refrescar el mapa
	SupportMapFragment fm_google;
	Criteria criteria;
	SharedPreferences preferencias;
	AlertManager alertManager;
	int cont=0;
	MainActivity main;
	
public googleMap(MainActivity mainActivity) {
		main=mainActivity;
	}


/****************************************************************
 *  
 * @brief Situa el mapa en torno a la localización en la posición
 * en la que esta situada el dispositivo
************************************************************/	
	public void init_location(SupportMapFragment fm, LocationManager locationManager, AlertManager alertManager2)
    {
		alertManager = alertManager2;
		// Getting GoogleMap object from the fragment
        googleMap = fm.getMap();
        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);
		
        // Creating a criteria object to retrieve provider
        criteria = new Criteria();
 
        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);
 
        // Getting Current Location
       Location loc = locationManager.getLastKnownLocation(provider);
 
        if(loc!=null){
           onLocationChanged(loc);
        }
        
       //Mostramos la última posición conocida
       viewLocation(loc);
       //Nos registramos para recibir actualizaciones de la posición
       register_forUpdates();
      
    	
   	locationManager.requestLocationUpdates(
    			LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }
     
	
/****************************************************************
 * @brief Maneja los cambios en el mapa, si detecta cambio de posición
 * lo actualiza a la nueva
************************************************************/	
    private void register_forUpdates() {
    	locListener = new LocationListener() {
	    	//si cambia de posicion
    		public void onLocationChanged(Location location) {
    			//elimina todos los marcadores
    			//googleMap.clear();
    			//pinta el mapa en la nueva localización
    			viewLocation(location);
	   	}
	    public void onProviderDisabled(String provider){
	   	}
	  	public void onProviderEnabled(String provider){
	   	}
	    	public void onStatusChanged(String provider, int status, Bundle extras){
	    	}
    	};		
	}
	
/****************************************************************
 * @brief Dibuja el mapa en la nueva localización
************************************************************/	

	private void viewLocation(Location loc) {
    	if(loc != null)
    	{	    			             
    		//obtenemos la posicion
    	    camUpd1 = CameraUpdateFactory.newLatLng(new LatLng(loc.getLatitude(),loc.getLongitude()));
    	    //movemos el mapa a la posicion
    	    googleMap.moveCamera(camUpd1);    	   
    	   // Zoom in the Google Map
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
			//activamos el compas 
			googleMap.getUiSettings().setCompassEnabled(true);
			
			addMarkersToMap();
    	}
    }

	@Override
	public void onLocationChanged(Location location) {
	}
	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
/****************************************************************
 * @brief Da la opción de modificar la vista del mapa
 * satellite
 * normal
 * terrain 
 * hybrid
************************************************************/	
	public void changeMapView(String vista)
	{
		
    	if(vista.equals("NORMAL")){
    		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    	}
    	else if(vista.equals("SATELLITE")){
    		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    	}
    	else if(vista.equals("TERRAIN")){
    		googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    	}
    	else if(vista.equals("HYBRID")){
    		googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    	}
       	
	   
	}
	
/****************************************************************
 * @brief Dibuja todos los marcadores en el mapa
************************************************************/	
	public void addMarkersToMap(){

		//Limpia el mapa
		googleMap.clear();
		//HAY QUE CAMBIAR LAS ETIQUETAS DE LOS MARCADORES POR LOS @STRING/
		/**
		 * LOW VISIBILITY MARKETS
		 */	
		for(int j=0;j<alertManager.getLowVisibility_Alerts().length;j++){
			for(int i=0;i<alertManager.lowVisibility_Alerts[j].length;i++){
				if((alertManager.lowVisibility_Alerts[j][i].getState())){
					googleMap.addMarker(new MarkerOptions()
					.position(new LatLng(alertManager.lowVisibility_Alerts[j][i].getLat(), alertManager.lowVisibility_Alerts[j][i].getLon()))
					.snippet(alertManager.lowVisibility_Alerts[j][i].getIncidenceType())
					.icon(BitmapDescriptorFactory.fromResource(alertManager.lowVisibility_Alerts[j][i].getIcon()))
					.title(alertManager.lowVisibility_Alerts[j][i].getKey()));
				}
			}	
		}	
		/**
		 * CRASHES MARKETS
		 */
		for(int i=0;i<alertManager.getCrashes_Alerts().length;i++){
			if((alertManager.crashes_Alerts[i].getState())){
				googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(alertManager.crashes_Alerts[i].getLat(), alertManager.crashes_Alerts[i].getLon()))
				.snippet("Crashes")
				.icon(BitmapDescriptorFactory.fromResource(alertManager.crashes_Alerts[i].getIcon()))
				.title(alertManager.crashes_Alerts[i].getKey()));
			}
		}	
		/**
		 * HEAVY TRAFFIC MARKETS
		 */
		for(int i=0;i<alertManager.getHeavytraffic_alerts().length;i++){
			if((alertManager.heavytraffic_Alerts[i].getState())){
				googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(alertManager.heavytraffic_Alerts[i].getLat(), alertManager.heavytraffic_Alerts[i].getLon()))
				.snippet("Heavy traffic")
				.icon(BitmapDescriptorFactory.fromResource(alertManager.heavytraffic_Alerts[i].getIcon()))
				.title(alertManager.heavytraffic_Alerts[i].getKey()));
			}
		}	
		/**
		 * NO VISIBLE VEHICLE MARKETS
		 */
		for(int i=0;i<alertManager.getNoVisibleVehicle_Alerts().length;i++){
			if((alertManager.noVisibleVehicle_Alerts[i].getState())){
				googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(alertManager.noVisibleVehicle_Alerts[i].getLat(), alertManager.noVisibleVehicle_Alerts[i].getLon()))
				.snippet("Vehicle not visible")
				.icon(BitmapDescriptorFactory.fromResource(alertManager.noVisibleVehicle_Alerts[i].getIcon()))
				.title(alertManager.noVisibleVehicle_Alerts[i].getKey()));
			}
		}	
		/**
		 * ROAD STATE MARKETS
		 */
		for(int j=0;j<alertManager.getRoadState_Alerts().length;j++){
			for(int i=0;i<alertManager.roadstate_Alerts[j].length;i++){
				if((alertManager.roadstate_Alerts[j][i].getState())){
					googleMap.addMarker(new MarkerOptions()
					.position(new LatLng(alertManager.roadstate_Alerts[j][i].getLat(), alertManager.roadstate_Alerts[j][i].getLon()))
					.snippet(alertManager.roadstate_Alerts[j][i].getIncidenceType())
					.icon(BitmapDescriptorFactory.fromResource(alertManager.roadstate_Alerts[j][i].getIcon()))
					.title(alertManager.roadstate_Alerts[j][i].getKey()));
				}
			}	
		}
		/**
		 * WORKS MARKETS
		 */
		for(int i=0;i<alertManager.getWorks_Alerts().length;i++){
			if((alertManager.works_Alerts[i].getState())){
				googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(alertManager.works_Alerts[i].getLat(), alertManager.works_Alerts[i].getLon()))
				.snippet("Works")
				.icon(BitmapDescriptorFactory.fromResource(alertManager.works_Alerts[i].getIcon()))
				.title(alertManager.works_Alerts[i].getKey()));
			}
		}	
	}
	
}
