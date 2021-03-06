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

* @file Works_Alerts
* @brief Clase objeto: Muestra los datos que podria tener una alerta 
* 		de las obras.
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
public class Works_Alerts extends Alerts{
	/*********************************************************************
	** 																	**
	** LOCAL VARIABLES 													**
	** 																	**
	**********************************************************************/
	private boolean direction;
	private boolean closedroad;
	/**********************************************************************
	 * @brief  NoVisibleVehicle_Alerts() es el constructor de la clase.
	 * Se encarga de : Inicializar el icono de la alerta.
	**********************************************************************/
	public Works_Alerts(){
		this.setIcon(R.drawable.works_alert_icon);
	}
	/**********************************************************************
	 * @brief  Getters de los datos de las alertas.
	 * Se encarga de : recoger los valores de una alerta.
	**********************************************************************/
	public boolean getDirection(){
		return this.direction;
	}
	public boolean getClosedRoad(){
		return this.closedroad;
	}
	/**********************************************************************
	 * @brief  Setters de los datos de las alertas.
	 * Se encarga de : Cambiar los valores de una alerta.
	**********************************************************************/
	public void setDirection(boolean direction){
		this.direction=direction;
	}
	public void setClosedRoad(boolean closedroad){
		this.closedroad=closedroad;
	}
}
