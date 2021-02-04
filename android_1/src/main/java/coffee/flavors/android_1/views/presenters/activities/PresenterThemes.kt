/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_1.views.presenters.activities

import android.os.Build
import coffee.flavors.android_1.views.screens.activities.ThemesView
import coffee.flavors.common.tools.constants.Android1Themes

/**
 * @author, evolandlupiz
 * @date, 6/4/2020
 * @property, Medium-Android
 * @purpose, handles ui actions.
 */

/**
 * @desc defines events for onClick actions.
 */
class PresenterThemes (private val mThemesView: ThemesView){

    /**
     * binding switch action was invoked
     * , if theme was another type, update the theme
     * , then callback to the desired theme.
     */
    fun fireBindingSwitch(){
        if(mThemesView.mTheme == Android1Themes.DARK_DAY_NIGHT_MODE){ mThemesView.mTheme = Android1Themes.DARK_BINDING_MODE }
        if(mThemesView.mTheme == Android1Themes.LIGHT_DAY_NIGHT_MODE){ mThemesView.mTheme = Android1Themes.LIGHT_BINDING_MODE }
        when(mThemesView.mTheme) {
            Android1Themes.LIGHT_BINDING_MODE -> { mThemesView.call(true) }
            Android1Themes.DARK_BINDING_MODE -> { mThemesView.call(false) }
        }
    }

    /**
     * attribute switch action was invoked
     * , when switch is toggled post live data value and then function to desired theme.
     */
    fun fireAttributeSwitch(){
        if(mThemesView.mThemesVm.mAttributeSwitchOnOff.value != true){
            mThemesView.mThemesVm.mAttributeSwitchOnOff.postValue(true)
            mThemesView.darkAttributeBundle()
        } else {
            mThemesView.mThemesVm.mAttributeSwitchOnOff.postValue(false)
            mThemesView.lightAttributeBundle()
        }
    }

    /**
     * overlay switch action was invoked
     * , when switch is toggled post live data value and then function to desired theme.
     */
    fun fireOverlaySwitch(){
        if(mThemesView.mThemesVm.mOverlaySwitchOnOff.value != true){
            mThemesView.mThemesVm.mOverlaySwitchOnOff.postValue(true)
            mThemesView.darkOverlayBundle()
        } else {
            mThemesView.mThemesVm.mOverlaySwitchOnOff.postValue(false)
            mThemesView.lightOverlayBundle()
        }
    }

    /**
     * daynight switch action was invoked
     * , when switch is toggled post live data value and then function to desired theme.
     */
    fun fireDayNightSwitch() {
        if(mThemesView.mThemesVm.mDayNightSwitchOnOff.value != true){
            mThemesView.mThemesVm.mDayNightSwitchOnOff.postValue(true)
            mThemesView.darkDayNightBundle()
        } else {
            mThemesView.mThemesVm.mDayNightSwitchOnOff.postValue(false)
            mThemesView.lightDayNightBundle()
        }
    }

    /**
     * force dark mode switch action was invoked
     * , when switch is toggled post live data value and then function to desired theme.
     * , check api is at least (29)Q
     * @throws RuntimeException when sdk is less than 29
     */
    fun fireForcedDarkMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){  /* (29)Q */
            if(mThemesView.mThemesVm.mForcedSwitchOnOff.value != true){
                mThemesView.mThemesVm.mForcedSwitchOnOff.postValue(true)
                mThemesView.darkForcedBundle()
            } else {
                mThemesView.mThemesVm.mForcedSwitchOnOff.postValue(false)
                mThemesView.lightForcedBundle()
            }
        } else {
            throw RuntimeException("VERSION.SDK_INT < 29, NOT SUPPORTED")
        }
    }
}