/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_1.controllers.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author, evolandlupiz
 * @date, 5/31/2020
 * @property, Medium-Android
 * @purpose, view model for themes.
 */

/**
 * @desc view model.
 */
class ThemesViewModel constructor(application: Application): ViewModel() {

    /**
     * two-way data binding day night switch material state reference through live data.
     * this determines if the switch is checked on or off.
     * updates at each event on switch click.
     */
    var mDayNightSwitchOnOff = MutableLiveData<Boolean>()

    /**
     * two-way data binding forced switch material state reference through live data.
     * this determines if the switch is checked on or off.
     * updates at each event on switch click.
     */
    var mForcedSwitchOnOff = MutableLiveData<Boolean>()

    /**
     * two-way data binding forced switch material state reference through live data.
     * this determines if the switch is checked on or off.
     * updates at each event on switch click.
     */
    var mAttributeSwitchOnOff = MutableLiveData<Boolean>()

    /**
     * two-way data binding overlay switch material state reference through live data.
     * this determines if the switch is checked on or off.
     * updates at each event on switch click.
     */
    var mOverlaySwitchOnOff = MutableLiveData<Boolean>()
}