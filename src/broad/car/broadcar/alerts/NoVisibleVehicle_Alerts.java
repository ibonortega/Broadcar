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

* @file NoVisibleVehicle_Alerts
* @brief Clase objeto: Muestra los datos que podria tener una alerta 
* 		de vehiculos no visibles.
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
public class NoVisibleVehicle_Alerts extends Alerts{

	private boolean direction;
	private int speed;
	
	
	/**********************************************************************
	 * @brief  NoVisibleVehicle_Alerts() es el constructor de la clase.
	 * Se encarga de : Inicializar el icono de la alerta.
	**********************************************************************/
	public NoVisibleVehicle_Alerts(){
		this.setIcon(R.drawable.novisible_alert_icon);
	}
	/**********************************************************************
	 * @brief  Getters de los datos de las alertas.
	 * Se encarga de : recoger los valores de una alerta.
	**********************************************************************/
	public boolean getDirection(){
		return this.direction;
	}
	public int getSpeed(){
		return this.speed;
	}
	/**********************************************************************
	 * @brief  Setters de los datos de las alertas.
	 * Se encarga de : Cambiar los valores de una alerta.
	**********************************************************************/
	public void setDirection(boolean direction){
		this.direction=direction;
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
}
