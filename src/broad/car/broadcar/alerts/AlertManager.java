/*********************************************************************
**																	**
** MODULES USED 													**
** 																	**
**********************************************************************/
package broad.car.broadcar.alerts;
/** @addtogroup Broadcar
*
* @{

* @file AlertManager
* @brief Clase encargada de generar los arrays de las alertas
*
* @par VERSION HISTORY
* Version : v0.0
* Date : 30/01/2013
* Revised by : BroadCar team
* Description : Original version.
*
* @}
*/

public class AlertManager {
	/*********************************************************************
	** 																	**
	** IMPORTED CLASSES / Declarations  								**
	** 																	**
	**********************************************************************/
	public Crashes_Alerts crashes_Alerts[];
	public HeavyTraffic_Alerts heavytraffic_Alerts[];
	public LowVisibility_Alerts lowVisibility_Alerts[][];
	public Works_Alerts works_Alerts[];
	public NoVisibleVehicle_Alerts noVisibleVehicle_Alerts[];
	public RoadState_Alerts roadstate_Alerts[][];
	/*********************************************************************
	** 																	**
	** LOCAL VARIABLES 													**
	** 																	**
	**********************************************************************/
	private static final int MAX_ALERT=10;
	private static final int MAX_TYPEVisibility = 3;
	private static final int MAX_TYPERoadstate = 5;
	
	private static int posCrashes=0;
	private static int posHeavyTraffic=0;
	private static int posLowVisibility[];
	private static int posWorks=0;
	private static int posNoVisibleVehicle=0;
	private static int posRoadState[];
	

	/*********************************************************************
	** 																	**
	** GLOBAL FUNCTIONS 												**
	** 																	**
	**********************************************************************/
	
	
	/**********************************************************************
	 * @brief  AlertManager() es el constructor de la clase.
	 * Se encarga de : Inicializar las alertas del sistema.
	**********************************************************************/
	public AlertManager(){
		
		//INICIARLIZAR CORRECTAMENTE TODO
		heavytraffic_init();
		lowvisibility_init();
		crashes_inti();
		novisiblevehicle_init();
		roadstate_init();
		works_init();	
	}
	/**********************************************************************
	 * @brief  Getters de los contadores de los arrays.
	 * Se encarga de : Recoger los valores que tienen los contadores de 
	 * 				los arrays de las alertas.
	**********************************************************************/
	public int getposCrashes(){
		return posCrashes;
	}
	public int getposHeavyTraffic(){
		return posHeavyTraffic;
	}
	public int getposLowVisibility(int type){
		return posLowVisibility[type];
	}
	public int getposWorks(){
		return posWorks;
	}
	public int getposNoVisibleVehicle(){
		return posNoVisibleVehicle;
	}
	public int getposRoadState(int type){
		return posRoadState[type];
	}
	/**********************************************************************
	 * @brief  Setters de los contadores de los arrays.
	 * Se encarga de : Cambiar los valores que tienen los contadores de 
	 * 				los arrays de las alertas.
	**********************************************************************/
	public void setPosCrashes(int posCrashes){
		this.posCrashes=posCrashes;
	}
	public void setposHeavyTraffic(int posHeavyTraffic){
		this.posHeavyTraffic=posHeavyTraffic;
	}
	public void setposLowVisibility(int posLowVisibility, int type){
		this.posLowVisibility[type]=posLowVisibility;
	}
	public void setposWorks(int posWorks){
		this.posWorks=posWorks;
	}
	public void setposNoVisibleVehicle(int posNoVisibleVehicle){
		this.posNoVisibleVehicle=posNoVisibleVehicle;
	}
	public void setposRoadState(int posRoadState, int type){
		this.posRoadState[type]=posRoadState;
	}
	/**********************************************************************
	 * @brief  Add de las posiciones.
	 * Se encarga de : Añadir unos mas a los arrays y llevar la cuenta de cual
	 * es el ultimo.
	**********************************************************************/
	public void addPosCrashes(){
		setPosCrashes(getposCrashes()+1);
		if(getposCrashes()==10){
			setPosCrashes(0);
		}
	}
	public void addposHeavyTraffic(){
		setposHeavyTraffic(this.getposHeavyTraffic()+1);
		if(getposHeavyTraffic()==10){
			setposHeavyTraffic(0);
		}
	}
	public void addposLowVisibility(int type){
		setposLowVisibility(getposLowVisibility(type)+1,type);
		if(getposLowVisibility(type)==10){
			setposLowVisibility(0,type);
		}
	}
	public void addposWorks(){
		setposWorks(getposWorks()+1);
		if(getposWorks()==10){
			setposWorks(0);
		}
	}
	public void addposNoVisibleVehicle(){
		setposNoVisibleVehicle(this.getposNoVisibleVehicle()+1);
		if(getposNoVisibleVehicle()==10){
			setposNoVisibleVehicle(0);
		}
	}
	public void addposRoadState(int type){
		setposRoadState(getposRoadState(type)+1,type);
		if(getposRoadState(type)==10){
			setposRoadState(0,type);
		}
	}
	
	/**********************************************************************
	 * @brief  Getters de los arrays.
	 * Se encarga de : Recoger los arrays completos.
	**********************************************************************/
	public Crashes_Alerts[] getCrashes_Alerts(){
		return crashes_Alerts;
	}
	public HeavyTraffic_Alerts[] getHeavytraffic_alerts(){
		return heavytraffic_Alerts;
	}
	public LowVisibility_Alerts[][] getLowVisibility_Alerts(){
		return lowVisibility_Alerts;
	}
	public Works_Alerts[] getWorks_Alerts(){
		return works_Alerts;
	}
	public NoVisibleVehicle_Alerts[] getNoVisibleVehicle_Alerts(){
		return noVisibleVehicle_Alerts;
	}
	public RoadState_Alerts[][] getRoadState_Alerts(){
		return roadstate_Alerts;
	}
	
	
	/*********************************************************************
	** 																	**
	** LOCAL FUNCTIONS 													**
	** 																	**
	**********************************************************************/
	
	
	/**********************************************************************
	 * @brief  heavytraffic_init es la funcion que se ocupa de inicializar 
	 * las alertas correspondientes al trafico denso.
	 * Se encarga de: 
	 * 		Crea los arrays para las alertas y dentro de el crea las alertas en 
	 * 		si 
	**********************************************************************/
	public void heavytraffic_init(){
		heavytraffic_Alerts=new HeavyTraffic_Alerts[MAX_ALERT];
		for(int i=0;i<heavytraffic_Alerts.length;i++){
			heavytraffic_Alerts[i]=new HeavyTraffic_Alerts();
			
		}
	}
	/**********************************************************************
	 * @brief  lowvisibility_init es la funcion que se ocupa de inicializar 
	 * las alertas correspondientes a la poca visibilidad
	 * Se encarga de: 
	 * 		Crea los arrays para las alertas y dentro de el crea las alertas en 
	 * 		si 
	**********************************************************************/
	public void lowvisibility_init(){
		lowVisibility_Alerts=new LowVisibility_Alerts[MAX_TYPEVisibility][MAX_ALERT]; //<--CAMBIAR PARA QUE SEA GENERICO EL 3
		posLowVisibility=new int[MAX_TYPEVisibility];
		for(int j=0;j<getLowVisibility_Alerts().length;j++){		
			posLowVisibility[j]=0;
			for(int i=0;i<lowVisibility_Alerts[j].length;i++){
				lowVisibility_Alerts[j][i]=new LowVisibility_Alerts();
				lowVisibility_Alerts[j][i].setIncidencetype(j);
				lowVisibility_Alerts[j][i].setId(i);			
			}
		}

	}
	/**********************************************************************
	 * @brief  works_init es la funcion que se ocupa de inicializar 
	 * las alertas correspondientes a las obras.
	 * Se encarga de: 
	 * 		Crea los arrays para las alertas y dentro de el crea las alertas en 
	 * 		si 
	**********************************************************************/
	public void works_init(){
		works_Alerts=new Works_Alerts[MAX_ALERT];
		for(int i=0;i<works_Alerts.length;i++){
			works_Alerts[i]=new Works_Alerts();
		}
	}
	/**********************************************************************
	 * @brief  novisiblevehicle_init es la funcion que se ocupa de inicializar 
	 * las alertas correspondientes a las obras.
	 * Se encarga de: 
	 * 		Crea los arrays para las alertas y dentro de el crea las alertas en 
	 * 		si 
	**********************************************************************/
	public void novisiblevehicle_init(){
		noVisibleVehicle_Alerts=new NoVisibleVehicle_Alerts[MAX_ALERT];
		for(int i=0;i<noVisibleVehicle_Alerts.length;i++){
			noVisibleVehicle_Alerts[i]=new NoVisibleVehicle_Alerts();
		}
	}
	/**********************************************************************
	 * @brief  roadstate_init es la funcion que se ocupa de inicializar 
	 * las alertas correspondientes al estado de la carretera.
	 * Se encarga de: 
	 * 		Crea los arrays para las alertas y dentro de el crea las alertas en 
	 * 		si 
	**********************************************************************/
	public void roadstate_init(){
		roadstate_Alerts=new RoadState_Alerts[MAX_TYPERoadstate][MAX_ALERT];
		posRoadState=new int[MAX_TYPERoadstate];
		for(int j=0;j<getRoadState_Alerts().length;j++){
			posRoadState[j]=0;
			for(int i=0;i<roadstate_Alerts[j].length;i++){
				roadstate_Alerts[j][i]=new RoadState_Alerts();
				roadstate_Alerts[j][i].setIncidencetype(j);
				roadstate_Alerts[j][i].setId(i);			
			}
		}
	}
	/**********************************************************************
	 * @brief  crashes_inti es la funcion que se ocupa de inicializar 
	 * las alertas correspondientes a los accidentes.
	 * Se encarga de: 
	 * 		Crea los arrays para las alertas y dentro de el crea las alertas en 
	 * 		si 
	**********************************************************************/
	public void crashes_inti(){
		crashes_Alerts=new Crashes_Alerts[MAX_ALERT];
		for(int i=0;i<crashes_Alerts.length;i++){
			crashes_Alerts[i]=new Crashes_Alerts();

		}
	}
}
