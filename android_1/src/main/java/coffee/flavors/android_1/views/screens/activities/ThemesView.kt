/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_1.views.screens.activities

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import coffee.flavors.android_1.R
import coffee.flavors.android_1.controllers.viewmodel.ThemesViewModel
import coffee.flavors.android_1.core.factory.ThemesViewModelFactory
import coffee.flavors.android_1.databinding.LayoutDarkThemesViewBinding
import coffee.flavors.android_1.databinding.LayoutDarkThemesViewBindingImpl
import coffee.flavors.android_1.databinding.LayoutLightThemesViewBinding
import coffee.flavors.android_1.databinding.LayoutLightThemesViewBindingImpl
import coffee.flavors.android_1.models.entity.ThemeTypes
import coffee.flavors.android_1.views.presenters.activities.PresenterThemes
import coffee.flavors.android_zzz.core.AndroidZZZ
import coffee.flavors.common.tools.constants.Android1Themes
import coffee.flavors.common.tools.constants.Android1Tokens
import coffee.flavors.common.tools.constants.DebugX
import com.google.android.material.card.MaterialCardView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.google.android.play.core.splitcompat.SplitCompat

/**
 * @author, evolandlupiz
 * @date, 5/26/2020
 * @property, Medium-Android
 * @purpose, verify functionality.
 */

/**
 * @desc themes view screen which contains different types of dark mode implementations
 * , each render practically the same thing.
 */
class ThemesView : AppCompatActivity() {

    /**
     * binding type definition
     */
    var mBinding: ViewDataBinding? = null

    /**
     * Local that holds the default theme which can change.
     * @return the theme.
     */
    var mTheme: String = Android1Themes.LIGHT_BINDING_MODE

    /**
     * factory for [ThemesViewModelFactory]
     */
    private lateinit var factory: ViewModelProvider.Factory


    /**
     * view model for [ThemesViewModel]
     */
    lateinit var mThemesVm: ThemesViewModel

    /**
     *  need to attach the context of this context to the base one.
     */
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    /**
     * perform di, load state, adjust theme, fire bindings.
     */
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        val diShare = (this.application as AndroidZZZ).diShare
        this.factory = ThemesViewModelFactory(application)
        this.mThemesVm = factory.create(ThemesViewModel::class.java)
        if(state == null){
            this.fireUiBindings()
        } else {
            if (state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) != null) {
                this.mTheme = state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) as String
            }
            this.fireUiBindings()
        }
    }

    /**
     * binding is generated through BR class, this will set the view through binding
     * bind presenter account instance with this view, execute pending binding
     * @throws RuntimeException when binding type is zZz unknown.
     */
    private fun fireUiBindings() {
        val type: ThemeTypes
        when(this.mTheme){
            Android1Themes.DARK_BINDING_MODE -> { darkBinding() }
            Android1Themes.LIGHT_BINDING_MODE -> { lightBinding() }
            Android1Themes.DARK_DAY_NIGHT_MODE -> { darkDayNight() }
            Android1Themes.LIGHT_DAY_NIGHT_MODE -> { lightDayNight() }
            Android1Themes.DARK_FORCED_MODE -> { darkForced() }
            Android1Themes.LIGHT_FORCED_MODE -> { lightForced() }
            Android1Themes.DARK_ATTRIBUTE_MODE -> { darkAttribute() }
            Android1Themes.LIGHT_ATTRIBUTE_MODE -> { lightAttribute() }
            Android1Themes.DARK_OVERLAY_MODE -> { darkOverlay() }
            Android1Themes.LIGHT_OVERLAY_MODE -> { lightOverlay() }
            else -> { throw RuntimeException(Android1Themes.UNKNOWN_MODE) }
        }
        mBinding?.executePendingBindings()
    }

    /**
     * part of gracefully preserving users transient state.
     * serializes theme to handle configuration change.
     * @throws RuntimeException when binding type is zZz unknown.
     */
    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) != null) {
            this.mTheme = state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) as String
        }
        state.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, this.mTheme)
        this.fireUiBindings()
        when(this.mBinding) {
            is LayoutDarkThemesViewBindingImpl -> { darkState(state) }
            is LayoutLightThemesViewBindingImpl -> { lightState(state) }
            else -> { throw RuntimeException(DebugX.THEMES_VIEW_BINDING_TYPE) }
        }
    }

    /**
     * dark binding, set theme, update content view, set status bar, and set presenter.
     */
    private fun darkBinding(){
        this.setTheme(R.style.DarkThemeBinding)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }

    /**
     * light binding, set theme, update content view, set status bar, and set presenter.
     */
    private fun lightBinding(){
        this.setTheme(R.style.LightThemeBinding)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }

    /**
     * dark day night, set theme, update delegate, update binding, set presenter, set lifecycle scope.
     */
    private fun darkDayNight(){
        this.setTheme(R.style.DarkThemeDayNight)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        type.mPresenter = PresenterThemes(this)
        type.lifecycleOwner = this
    }

    /**
     * light day night, set theme, update delegate, update binding, set presenter, set lifecycle scope.
     */
    private fun lightDayNight(){
        this.setTheme(R.style.LightThemeDayNight)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        type.mPresenter = PresenterThemes(this)
        type.lifecycleOwner = this
    }

    /**
     * dark forced, set theme, set binding, update status bar, set presenter.
     */
    private fun darkForced(){
        this.setTheme(R.style.DarkThemeForced)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }

    /**
     * light forced, set theme, set binding, update status bar, set presenter.
     */
    private fun lightForced(){
        this.setTheme(R.style.LightThemeForced)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }

    /**
     * dark attribute, reference ui elements, set their color.
     */
    private fun darkAttribute(){
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)

        val layoutRoot = this.findViewById<ConstraintLayout>(R.id.themes_view_root)
        val layoutSmBinding = this.findViewById<SwitchMaterial>(R.id.theme_binding_switch)
        val layoutTvBinding = this.findViewById<MaterialTextView>(R.id.theme_binding_tv)
        val layoutSmAttribute = this.findViewById<SwitchMaterial>(R.id.theme_attribute_switch)
        val layoutTvAttribute = this.findViewById<MaterialTextView>(R.id.theme_attribute_tv)
        val layoutSmOverlay = this.findViewById<SwitchMaterial>(R.id.theme_overlay_switch)
        val layoutTvOverlay = this.findViewById<MaterialTextView>(R.id.theme_overlay_tv)
        val layoutSmDayNight =  this.findViewById<SwitchMaterial>(R.id.theme_daynight_switch)
        val layoutTvDayNight =  this.findViewById<MaterialTextView>(R.id.theme_daynight_tv)
        val layoutSmForced =  this.findViewById<SwitchMaterial>(R.id.theme_forced_switch)
        val layoutTvForced =  this.findViewById<MaterialTextView>(R.id.theme_forced_tv)
        val layoutThemeCard = this.findViewById<CardView>(R.id.theme_card_root)
        val layoutThemeCardTv = this.findViewById<MaterialTextView>(R.id.theme_card_tv)

        layoutRoot.setBackgroundColor(resources.getColor(R.color.colorDarkBackground,null))
        layoutSmBinding.setBackgroundColor(resources.getColor(R.color.colorDarkPrimary,null))
        layoutTvBinding.setTextColor(resources.getColor(R.color.colorLight,null))
        layoutSmAttribute.setBackgroundColor(resources.getColor(R.color.colorDarkPrimary,null))
        layoutTvAttribute.setTextColor(resources.getColor(R.color.colorLight,null))
        layoutSmOverlay.setBackgroundColor(resources.getColor(R.color.colorDarkPrimary,null))
        layoutTvOverlay.setTextColor(resources.getColor(R.color.colorLight,null))
        layoutSmDayNight.setBackgroundColor(resources.getColor(R.color.colorDarkPrimary,null))
        layoutTvDayNight.setTextColor(resources.getColor(R.color.colorLight,null))
        layoutSmForced.setBackgroundColor(resources.getColor(R.color.colorDarkPrimary,null))
        layoutTvForced.setTextColor(resources.getColor(R.color.colorLight,null))
        layoutThemeCard.setBackgroundColor(resources.getColor(R.color.colorDarkPrimary,null))
        layoutThemeCardTv.setTextColor(resources.getColor(R.color.colorLight,null))

        layoutThemeCardTv.text = resources.getText(R.string.dark_mode)
    }

    /**
     * light attribute, reference ui elements, set their color.
     */
    private fun lightAttribute(){
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)

        val layoutRoot = this.findViewById<ConstraintLayout>(R.id.themes_view_root)
        val layoutSmBinding = this.findViewById<SwitchMaterial>(R.id.theme_binding_switch)
        val layoutTvBinding = this.findViewById<MaterialTextView>(R.id.theme_binding_tv)
        val layoutSmAttribute = this.findViewById<SwitchMaterial>(R.id.theme_attribute_switch)
        val layoutTvAttribute = this.findViewById<MaterialTextView>(R.id.theme_attribute_tv)
        val layoutSmOverlay = this.findViewById<SwitchMaterial>(R.id.theme_overlay_switch)
        val layoutTvOverlay = this.findViewById<MaterialTextView>(R.id.theme_overlay_tv)
        val layoutSmDayNight =  this.findViewById<SwitchMaterial>(R.id.theme_daynight_switch)
        val layoutTvDayNight =  this.findViewById<MaterialTextView>(R.id.theme_daynight_tv)
        val layoutSmForced =  this.findViewById<SwitchMaterial>(R.id.theme_forced_switch)
        val layoutTvForced =  this.findViewById<MaterialTextView>(R.id.theme_forced_tv)
        val layoutThemeCard = this.findViewById<CardView>(R.id.theme_card_root)
        val layoutThemeCardTv = this.findViewById<MaterialTextView>(R.id.theme_card_tv)

        layoutRoot.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutSmBinding.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutTvBinding.setTextColor(resources.getColor(R.color.colorDark,null))
        layoutSmAttribute.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutTvAttribute.setTextColor(resources.getColor(R.color.colorDark,null))
        layoutSmOverlay.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutTvOverlay.setTextColor(resources.getColor(R.color.colorDark,null))
        layoutSmDayNight.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutTvDayNight.setTextColor(resources.getColor(R.color.colorDark,null))
        layoutSmForced.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutTvForced.setTextColor(resources.getColor(R.color.colorDark,null))
        layoutThemeCard.setBackgroundColor(resources.getColor(R.color.colorLightPrimary,null))
        layoutThemeCardTv.setTextColor(resources.getColor(R.color.colorLight,null))

        layoutThemeCardTv.text = resources.getText(R.string.light_mode)
    }

    /**
     * change theme by overlaying elements with percentage varying transparent cards through elevation.
     */
    private fun darkOverlay(){
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)

        type.themeCardOverlayRoot.elevation = 8.0f
        type.themeCardOverlayRoot.cardElevation = 8.0f
    }

    /**
     * change theme by overlaying elements with percentage varying transparent cards through elevation.
     */
    private fun lightOverlay(){
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)

        type.themeCardOverlayRoot.elevation = 0.0f
        type.themeCardOverlayRoot.cardElevation = 0.0f
    }

    /**
     * dark binding, bundle desired mode, bundle new state for switch material.
     */
    private fun darkBindingBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_BINDING_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH, true)
        this.onSaveInstanceState(temp)
    }

    /**
     * light binding, bundle desired mode, bundle new state for switch material.
     */
    private fun lightBindingBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_BINDING_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH, false)
        this.onSaveInstanceState(temp)
    }

    /**
     * dark day night, bundle desired mode, bundle new state for switch material.
     */
    fun darkDayNightBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_DAY_NIGHT_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH, true)
        this.onSaveInstanceState(temp)
    }

    /**
     * light day night, bundle desired mode, bundle new state for switch material.
     */
    fun lightDayNightBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_DAY_NIGHT_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH, false)
        this.onSaveInstanceState(temp)
    }

    /**
     * dark forced, bundle desired mode, bundle new state for switch material.
     */
    fun darkForcedBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_FORCED_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH, true)
        this.onSaveInstanceState(temp)
    }

    /**
     * light forced, bundle desired mode, bundle new state for switch material.
     */
    fun lightForcedBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_FORCED_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH, false)
        this.onSaveInstanceState(temp)
    }

    /**
     * dark attribute, bundle desired mode, bundle new state for switch material.
     */
    fun darkAttributeBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_ATTRIBUTE_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH, true)
        this.onSaveInstanceState(temp)
    }

    /**
     * light attribute, bundle desired mode, bundle new state for switch material.
     */
    fun lightAttributeBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_ATTRIBUTE_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH, false)
        this.onSaveInstanceState(temp)
    }

    /**
     * dark overlay, bundle desired mode, bundle new state for switch material.
     */
    fun darkOverlayBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_OVERLAY_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH, true)
        this.onSaveInstanceState(temp)
    }

    /**
     * light overlay, bundle desired mode, bundle new state for switch material.
     */
    fun lightOverlayBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_OVERLAY_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH, false)
        this.onSaveInstanceState(temp)
    }

    /**
     * dark state, set the ui elements states.
     */
    private fun darkState(state: Bundle) {
        val type = mBinding as LayoutDarkThemesViewBinding
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH) != null) {
            type.themeBindingSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH) != null) {
            type.themeDaynightSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH) != null) {
            type.themeForcedSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH) != null) {
            type.themeAttributeSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH) != null) {
            type.themeOverlaySwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH)
        }
    }

    /**
     * light state, set the ui elements states.
     */
    private fun lightState(state: Bundle) {
        val type = mBinding as LayoutLightThemesViewBinding
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH) != null) {
            type.themeBindingSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH) != null) {
            type.themeDaynightSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH) != null) {
            type.themeForcedSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH) != null) {
            type.themeAttributeSwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH)
        }
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH) != null) {
            type.themeOverlaySwitch.isChecked =
                state.getBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH)
        }
    }

    /**
     * Delegate that performs state changes to generate a new theme during runtime.
     * @param mode as the theme mode.
     */
    fun call(mode: Boolean) {
        when(mode){
            true -> { darkBindingBundle() }
            false -> { lightBindingBundle() }
        }
    }

    /**
     * @desc handles a simple change in color for the status bar.
     * @note scalable , this can be moved to ui utils.
     * @requires SDK >= 21
     */
    private fun statusBarColor(color: String) {
        when(color){
            Android1Themes.DARK_BINDING_MODE -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = this.resources.getColor(R.color.colorDarkPrimary, null)
            }
            Android1Themes.LIGHT_BINDING_MODE -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = this.resources.getColor(R.color.colorLightPrimary, null)
            }
        }
    }
}