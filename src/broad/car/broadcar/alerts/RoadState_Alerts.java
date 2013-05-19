/*********************************************************************
**																	**
** MODULES USED 													**
** 																	**
**********************************************************************/
package broad.car.broadcar.alerts;

/** @addtogroup Broadcar
*
* @{

* @file RoadState_Alerts
* @brief Clase objeto: Muestra los datos que podria tener una alerta 
* 		del estado de la carretera.
* @Extends: Extiende de alertas por lo que tendra todas sus funciones y caracteristicas.
*
* @par VERSION HISTORY
* Version : v0.0
* Date : 30/01/2013
* Revised by : BroadCar team
* Description : Original version.
*
* @}
*/
public class RoadState_Alerts extends Alerts{
	/*********************************************************************
	** 																	**
	** LOCAL VARIABLES 													**
	** 																	**
	**********************************************************************/
	static private String incidencetype;
	static private int severity;
	static private final String ICE="Ice";
	static private final String OIL="Oil";
	static private final String MUD="Mud";
	static private final String FOG="Fog";
	static private final String WATER="Water";
	static private final String DETERIORATION="Deterioration";
	
	/**********************************************************************
	 * @brief  Setters de los datos de las alertas.
	 * Se encarga de : Cambiar los valores de una alerta.
	**********************************************************************/
	public void setDirection(boolean direction){
		this.direction=direction;
	}
	public void setSeverity(int severity){ //From 1 to 5
		this.severity=severity;
	}
	public void setIncidencetype(int incidencetype){
		if(incidencetype==0){
			this.incidencetype=FOG;
		}else if(incidencetype==1){
			this.incidencetype=OIL;
		}else if(incidencetype==2){
			this.incidencetype=MUD;
		}else if(incidencetype==3){
			this.incidencetype=WATER;
		}else if(incidencetype==4){
			this.incidencetype=DETERIORATION;
		}		
	}
	/**********************************************************************
	 * @brief  Getters de los datos de las alertas.
	 * Se encarga de : recoger los valores de una alerta.
	**********************************************************************/
	public String getIncidenceType(){
		return this.incidencetype;
	}
	public int getSeverity(){
		return this.severity;
	}	
	boolean direction;
	public boolean getDirection(){
		return this.direction;
	}
	
}
