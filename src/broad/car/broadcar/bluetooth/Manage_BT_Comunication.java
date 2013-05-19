
/*********************************************************************
**																	**
** MODULES USED 													**
** 																	**
**********************************************************************/
package broad.car.broadcar.bluetooth;

import broad.car.broadcar.MainActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import broad.car.broadcar.alerts.AlertManager;
import broad.car.broadcar.bluetooth.DeviceListActivity;


/** @addtogroup Broadcar
*
* @{

* @file Manage_BT_Comunication
* @brief Esta clase se encarga de manejar el estado del bluetooth y 
* de crear la conexión
*
* @par VERSION HISTORY
* Version : v0.0
* Date : 30/01/2013
* Revised by : BroadCar team
* Description : Original version.
*
* @}
*/
public class Manage_BT_Comunication {
	/*********************************************************************
	** 																	**
	** IMPORTED CLASSES / Declarations  								**
	** 																	**
	**********************************************************************/
	//Main Activity class
	MainActivity main; 
	//Adaptador para el manejo del bluetood
	BluetoothAdapter BluetoothAdapter; //
	// Member object for the chat services;hilo para la comunicacion buetooth
	BluetoothChatService ChatService;
	// Gestor de las alertas
	AlertManager alertManager;
	
	/*********************************************************************
	** 																	**
	** GLOBAL VARIABLES 												**
	** 																	**
	**********************************************************************/
	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	
	
	/*********************************************************************
	** 																	**
	** CONSTRUCTOR      												**
	** 																	*
	 * @param alertManager *
	**********************************************************************/
	public Manage_BT_Comunication(MainActivity mainActivity, BluetoothAdapter mBluetoothAdapter,AlertManager alertManager1) {
		//inicializar 
		main=mainActivity;
		BluetoothAdapter=mBluetoothAdapter;
		alertManager=alertManager1;
		
	}


   /***********************************************************************
   * @param mainActivity 
   * @brief  Comprueba el estado del bluetooth <conectado o desconectado>
   *  y si esta desactivado, muestra un mensaje
   ************************************************************************/

		public void check_BluetoothStatus() {
			BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
		    if (!mBluetoothAdapter.isEnabled()) {
	         	//mensaje emergente
		    	Toast.makeText( main, "The Bluetooth is not activated", Toast.LENGTH_SHORT).show();
		    }		
		}
		
		
	/****************************************************************
	* @return 
	 * @brief Pone el dispositivo del usuario visible para el resto
	************************************************************/	
	public Intent set_bt_discoverable() {
			/* Pone visible el dispositivo visible a otros 
			 sin ningun limite de tiempo*/
	        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);//el valor  significa que siempre este activado, sin un tiempo determinado
	        return discoverableIntent;			
	}
	
	
   /********************************************************
   * @return serverIntent
   * @brief Encargada de mostrar los dispositivos
   * disponibles para la conexion bluetooth
   ********************************************************/	
		
		public Intent show_Discoverable_devices() {
			//Obtiene una lista con los dispositibos disponibles a conectar
			Intent serverIntent = null;
		    serverIntent = new Intent(main, DeviceListActivity.class);		
		    return serverIntent;
		}
		
	/*****************************************************************************
	* @brief  The Handler that gets information back from the BluetoothChatService
	******************************************************************************/	

	    public final Handler mHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
	            case MESSAGE_STATE_CHANGE:
	                switch (msg.arg1) {
	                case BluetoothChatService.STATE_CONNECTED:
	                    //setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
	                    //mConversationArrayAdapter.clear();
	                    break;
	                case BluetoothChatService.STATE_CONNECTING:
	                   // setStatus(R.string.title_connecting);
	                    break;
	                case BluetoothChatService.STATE_LISTEN:
	                case BluetoothChatService.STATE_NONE:
	                  //  setStatus(R.string.title_not_connected);
	                    break;
	                }
	                break;
          
	            case MESSAGE_READ://leer el mensaje recibido bia bluetooth
	                byte[] readBuf = (byte[]) msg.obj;
	                // construct a string from the valid bytes in the buffer
	                String readMessage = new String(readBuf, 0, msg.arg1);
	                Toast.makeText(main.getApplicationContext(), readMessage, Toast.LENGTH_SHORT).show();
	                //FUNCION PARA CAMBIAR LAS CARACTERISTICAS DE LAS ALERTAS.
	                btListenerAlertChange(readMessage);
	                break;
	           
	            }
	        }
	 
	    };
		
	    /****************************************************************************
		 * @brief  FUNCION PARA CAMBIAR EL ESTADO DE LAS ALERTAS Y SU INFORMACION
		 ****************************************************************************/
			  public void btListenerAlertChange(String readMessage) {
					//NORTE/SUR =0/1 (mirar +/-) 
					//este/oeste =2/3 (mirar +/-) 
					//los grados igual + minutos/60 + segundos/3600
				  // Creamos unos string donde almacenaremos los trozos separados.
					String[] lista;
					lista= new String[6];
					for(int i=0;i<lista.length;i++){
						lista[i]=new String();
					}
					String[] lat;
					lat=new String[4];
					for(int i=0;i<lat.length;i++){
						lat[i]=new String();
					}
					String[] lon;
					lon=new String[4];
					for(int i=0;i<lon.length;i++){
						lon[i]=new String();
					}
					Double dlat=0.0;
					Double dlon=0.0;
					String cadena = readMessage;
					//Separamos el string grande que se recibe.
					lista = cadena.split("/");
					//Separamos los string mas pequeños (Lat y Long)
					lat = lista[1].split("\\.");
					lon = lista[2].split("\\.");
					//convertimos los grados, minutos y segundos a grados para que google maps los interprete
					dlat=Double.parseDouble(lat[1])+(Double.parseDouble(lat[2])/60)+(Double.parseDouble(lat[3])/3600);
					dlon=Double.parseDouble(lon[1])+(Double.parseDouble(lon[2])/60)+(Double.parseDouble(lon[3])/3600);
					//Si el primer numero es 1 o 3 la lat y lon seran negativas.
					if(lon[0].equals("1")){
						dlat=0-dlat;
					}
					if(lon[0].equals("3")){
						dlon=0-dlon;
					}
					//Cambiamos la ultima alerta de trafico denso
					if(lista[0].equals("0")){
						if(lista[3].equals("1")){
							alertManager.heavytraffic_Alerts[alertManager.getposHeavyTraffic()].setDirection(true);
						}else{
							alertManager.heavytraffic_Alerts[alertManager.getposHeavyTraffic()].setDirection(false);
						}
						alertManager.heavytraffic_Alerts[alertManager.getposHeavyTraffic()].setSpeed(Integer.parseInt(lista[4]));
						alertManager.heavytraffic_Alerts[alertManager.getposHeavyTraffic()].setLat(dlat);
						alertManager.heavytraffic_Alerts[alertManager.getposHeavyTraffic()].setLon(dlon);
						alertManager.addposHeavyTraffic();
						
					}
					//Cambiamos la ultima alerta de Vehiculo no visible
					if(lista[0].equals("2")){
						if(lista[3].equals("1")){
							alertManager.noVisibleVehicle_Alerts[alertManager.getposNoVisibleVehicle()].setDirection(true);
						}else{
							alertManager.noVisibleVehicle_Alerts[alertManager.getposNoVisibleVehicle()].setDirection(false);
						}
						alertManager.noVisibleVehicle_Alerts[alertManager.getposNoVisibleVehicle()].setSpeed(Integer.parseInt(lista[4]));
						alertManager.noVisibleVehicle_Alerts[alertManager.getposNoVisibleVehicle()].setLat(dlat);
						alertManager.noVisibleVehicle_Alerts[alertManager.getposNoVisibleVehicle()].setLon(dlon);
						alertManager.addposNoVisibleVehicle();
					}
					//Cambiamos la ultima alerta de obras
					if(lista[0].equals("1")){ 
						if(lista[3].equals("1")){
							alertManager.works_Alerts[alertManager.getposWorks()].setDirection(true);
						}else{
							alertManager.works_Alerts[alertManager.getposWorks()].setDirection(false);
						}
						if(lista[4].equals("1")){
							alertManager.works_Alerts[alertManager.getposWorks()].setClosedRoad(true);
						}else{
							alertManager.works_Alerts[alertManager.getposWorks()].setClosedRoad(false);
						}
						alertManager.works_Alerts[alertManager.getposWorks()].setLat(dlat);
						alertManager.works_Alerts[alertManager.getposWorks()].setLon(dlon);
						alertManager.addposWorks();
					}
					//Cambiamos la ultima alerta de accidente en carretera
					if(lista[0].equals("5")){ 
						if(lista[3].equals("1")){
							alertManager.crashes_Alerts[alertManager.getposCrashes()].setDirection(true);
						}else{
							alertManager.crashes_Alerts[alertManager.getposCrashes()].setDirection(false);
						}
						if(lista[4].equals("1")){
							alertManager.crashes_Alerts[alertManager.getposCrashes()].setClosedRoad(true);
						}else{
							alertManager.crashes_Alerts[alertManager.getposCrashes()].setClosedRoad(false);
						}
						alertManager.crashes_Alerts[alertManager.getposCrashes()].setLat(dlat);
						alertManager.crashes_Alerts[alertManager.getposCrashes()].setLon(dlon);
						alertManager.addPosCrashes();
					}   
					//Cambiamos la ultima alerta de poca visibilidad	
					if(lista[0].equals("3")){  
						alertManager.lowVisibility_Alerts[Integer.parseInt(lista[3])][alertManager.getposLowVisibility(Integer.parseInt(lista[3]))].setSeverity(Integer.parseInt(lista[4]));
						alertManager.lowVisibility_Alerts[Integer.parseInt(lista[3])][alertManager.getposLowVisibility(Integer.parseInt(lista[3]))].setLat(dlat);
						alertManager.lowVisibility_Alerts[Integer.parseInt(lista[3])][alertManager.getposLowVisibility(Integer.parseInt(lista[3]))].setLon(dlon);
						alertManager.addposLowVisibility(Integer.parseInt(lista[3]));
					}
					//Cambiamos la ultima alerta del estado de la carretera
					if(lista[0].equals("4")){
						if(lista[4].equals("1")){
							alertManager.roadstate_Alerts[Integer.parseInt(lista[3])][alertManager.getposRoadState(Integer.parseInt(lista[3]))].setDirection(true);
						}else{
							alertManager.roadstate_Alerts[Integer.parseInt(lista[3])][alertManager.getposRoadState(Integer.parseInt(lista[3]))].setDirection(false);
						}
						alertManager.roadstate_Alerts[Integer.parseInt(lista[3])][alertManager.getposRoadState(Integer.parseInt(lista[3]))].setSeverity(Integer.parseInt(lista[5]));
						alertManager.roadstate_Alerts[Integer.parseInt(lista[3])][alertManager.getposRoadState(Integer.parseInt(lista[3]))].setLat(dlat);
						alertManager.roadstate_Alerts[Integer.parseInt(lista[3])][alertManager.getposRoadState(Integer.parseInt(lista[3]))].setLon(dlon);
						alertManager.addposRoadState(Integer.parseInt(lista[3]));
					}
					
				}
		
		
	/*********************************************************************
	* @return mHandler
	* @brief  FUunción que devuelve el mHander
	**********************************************************************/	    
	    public Handler getHandler(){
	    	return mHandler;
	    }
	   
		  
	/*************************************************************
	* @param mBluetoothAdapter 
	* @brief  Conecta dispositivos
	**************************************************************/	
	    public void connectDevice(Intent data, boolean secure) {
	        // Get the device MAC address
	        String address = data.getExtras()
	            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
	        // Get the BluetoothDevice object
	        BluetoothDevice device = BluetoothAdapter.getRemoteDevice(address);
	        // Attempt to connect to the device
	        ChatService.connect(device, secure);
	    }

	    
	    
	/*************************************************************
	* @param mChatService
	* @brief  Conecta dispositivos
	**************************************************************/	
		public void setChatService(BluetoothChatService mChatService) {
			ChatService=mChatService;			
		}

	/*************************************************************
	* @param mainActivity
	* @brief  Crea el objeto BluetoothChatService
	**************************************************************/	
		
	   public void createBluetoothChatService(MainActivity mainActivity) {
			if (BluetoothAdapter.isEnabled() && ChatService == null) {
	        	// Initialize the BluetoothChatService to perform bluetooth connections
				ChatService = new BluetoothChatService(mainActivity, mHandler);
	        }			
		}
	    
}
