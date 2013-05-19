/*********************************************************************
**																	**
** MODULES USED 													**
** 																	**
**********************************************************************/
package broad.car.broadcar;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/** @addtogroup Broadcar
*
* @{

* @file QuickPrefsActivity
* @brief Clase encargada de generar la vista de configuración de alertas
*
* @par VERSION HISTORY
* Version : v0.0
* Date : 30/01/2013
* Revised by : BroadCar team
* Description : Original version.
*
* @}
*/

public class QuickPrefsActivity extends PreferenceActivity {

	/*********************************************************************
	** 																	**
	** LOCAL FUNCTIONS 													**
	** 																	**
	**********************************************************************/
	
	
	/**********************************************************************
	 * @brief  Muestra la vista de la configuración de las alertas 
	**********************************************************************/	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource    
        addPreferencesFromResource(R.xml.preferences);
    }

}