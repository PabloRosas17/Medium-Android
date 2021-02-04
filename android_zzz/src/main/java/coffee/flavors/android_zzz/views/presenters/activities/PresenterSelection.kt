/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_zzz.views.presenters.activities

import coffee.flavors.android_zzz.R
import coffee.flavors.android_zzz.views.screens.activities.SelectionView

/**
 * @author, evolandlupiz
 * @date, 5/12/2020
 * @property, Medium-Android
 * @purpose, handles ui actions.
 */

/**
 * @desc defines events for onClick actions.
 */
class PresenterSelection (private val activity: SelectionView){

    /**
     * launch android0.
     */
    fun fireAndroid0(){
        activity.fireLoadAndLaunchModule(activity.resources.getString(R.string.module_android0))
    }

    /**
     * launch android1.
     */
    fun fireAndroid1(){
        activity.fireLoadAndLaunchModule(activity.resources.getString(R.string.module_android1))
    }
}