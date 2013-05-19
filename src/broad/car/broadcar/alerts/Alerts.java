/*********************************************************************
**																	**
** MODULES USED 													**
** 																	**
**********************************************************************/
package broad.car.broadcar.alerts;
import broad.car.broadcar.R;
/** @addtogroup Broadcar
*
* @{

* @file Alerts
* @brief Clase objeto: Muestra los datos que podria tener una alerta 
* 		y sus funciones
*
* @par VERSION HISTORY
* Version : v0.0
* Date : 30/01/2013
* Revised by : BroadCar team
* Description : Original version.
*
* @}
*/

public class Alerts {
	/*********************************************************************
	** 																	**
	** LOCAL VARIABLES 													**
	** 																	**
	**********************************************************************/
	private int id=0;
	private String key="Alert";
	private boolean state=false;
	private double lat;
	private double lon;
	private int icon=R.drawable.default_alert_icon;
	
	
	/**********************************************************************
	 * @brief  Getters de los datos de las alertas.
	 * Se encarga de : recoger los valores de una alerta.
	**********************************************************************/
	public int getIcon(){
		return this.icon;
	}	
	public int getId(){
		return this.id;
	}
	public String getKey(){
		return this.key;
	}
	public boolean getState(){
		return this.state;
	}

	public double getLat(){
		return this.lat;
	}
	public double getLon(){
		return this.lon;
	}
	
	/**********************************************************************
	 * @brief  Setters de los datos de las alertas.
	 * Se encarga de : Cambiar los valores de una alerta.
	**********************************************************************/
	public void setIcon(int icon){
		this.icon=icon;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setKey(String key){
		this.key=key;
	}
	public void setState(boolean state){
		this.state=state;
	}
	public void setLat(double lat){
		this.lat=lat;
	}
	public void setLon(double lon){
		this.lon=lon;
	}
}
