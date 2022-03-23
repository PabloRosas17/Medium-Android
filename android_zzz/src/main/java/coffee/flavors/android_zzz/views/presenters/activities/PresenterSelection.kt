
package coffee.flavors.android_zzz.views.presenters.activities

import coffee.flavors.android_zzz.R
import coffee.flavors.android_zzz.views.screens.activities.SelectionView

class PresenterSelection (private val activity: SelectionView){
    fun fireAndroid0(){
        activity.fireLoadAndLaunchModule(activity.resources.getString(R.string.module_android0))
    }
    fun fireAndroid1(){
        activity.fireLoadAndLaunchModule(activity.resources.getString(R.string.module_android1))
    }
}