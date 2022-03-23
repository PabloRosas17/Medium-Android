
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
import coffee.flavors.android_1.databinding.LayoutLightThemesViewBinding
import coffee.flavors.android_1.models.entity.ThemeTypes
import coffee.flavors.android_1.views.presenters.activities.PresenterThemes
import coffee.flavors.android_zzz.core.AndroidZZZ
import coffee.flavors.common.tools.constants.Android1Themes
import coffee.flavors.common.tools.constants.Android1Tokens
import coffee.flavors.common.tools.constants.DebugX
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.google.android.play.core.splitcompat.SplitCompat

class ThemesView : AppCompatActivity() {
    var mBinding: ViewDataBinding? = null
    var mTheme: String = Android1Themes.LIGHT_BINDING_MODE
    private lateinit var factory: ViewModelProvider.Factory
    lateinit var mThemesVm: ThemesViewModel
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }
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
    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        if(state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) != null) {
            this.mTheme = state.get(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE) as String
        }
        state.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, this.mTheme)
        this.fireUiBindings()
        when(this.mBinding) {
            is LayoutDarkThemesViewBinding -> { darkState(state) }
            is LayoutLightThemesViewBinding -> { lightState(state) }
            else -> { throw RuntimeException(DebugX.THEMES_VIEW_BINDING_TYPE) }
        }
    }
    private fun darkBinding(){
        this.setTheme(R.style.DarkThemeBinding)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }
    private fun lightBinding(){
        this.setTheme(R.style.LightThemeBinding)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }
    private fun darkDayNight(){
        this.setTheme(R.style.DarkThemeDayNight)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        type.mPresenter = PresenterThemes(this)
        type.lifecycleOwner = this
    }
    private fun lightDayNight(){
        this.setTheme(R.style.LightThemeDayNight)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        type.mPresenter = PresenterThemes(this)
        type.lifecycleOwner = this
    }
    private fun darkForced(){
        this.setTheme(R.style.DarkThemeForced)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }
    private fun lightForced(){
        this.setTheme(R.style.LightThemeForced)
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)
    }
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
    private fun darkOverlay(){
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_dark_themes_view)
        val type = mBinding as LayoutDarkThemesViewBinding
        this.statusBarColor(Android1Themes.DARK_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)

        type.themeCardOverlayRoot.elevation = 8.0f
        type.themeCardOverlayRoot.cardElevation = 8.0f
    }
    private fun lightOverlay(){
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_light_themes_view)
        val type = mBinding as LayoutLightThemesViewBinding
        this.statusBarColor(Android1Themes.LIGHT_BINDING_MODE)
        type.mPresenter = PresenterThemes(this)

        type.themeCardOverlayRoot.elevation = 0.0f
        type.themeCardOverlayRoot.cardElevation = 0.0f
    }
    private fun darkBindingBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_BINDING_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH, true)
        this.onSaveInstanceState(temp)
    }
    private fun lightBindingBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_BINDING_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_BINDING_SWITCH, false)
        this.onSaveInstanceState(temp)
    }
    fun darkDayNightBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_DAY_NIGHT_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH, true)
        this.onSaveInstanceState(temp)
    }
    fun lightDayNightBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_DAY_NIGHT_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_DAY_NIGHT_SWITCH, false)
        this.onSaveInstanceState(temp)
    }
    fun darkForcedBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_FORCED_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH, true)
        this.onSaveInstanceState(temp)
    }
    fun lightForcedBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_FORCED_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_FORCED_SWITCH, false)
        this.onSaveInstanceState(temp)
    }
    fun darkAttributeBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_ATTRIBUTE_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH, true)
        this.onSaveInstanceState(temp)
    }
    fun lightAttributeBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_ATTRIBUTE_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_ATTRIBUTE_SWITCH, false)
        this.onSaveInstanceState(temp)
    }
    fun darkOverlayBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.DARK_OVERLAY_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH, true)
        this.onSaveInstanceState(temp)
    }
    fun lightOverlayBundle(){
        val temp = Bundle()
        temp.putString(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_THEME_MODE, Android1Themes.LIGHT_OVERLAY_MODE)
        temp.putBoolean(Android1Tokens.MARSHALLED_TOKEN_KEY_SM_OVERLAY_SWITCH, false)
        this.onSaveInstanceState(temp)
    }
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
    fun call(mode: Boolean) {
        when(mode){
            true -> { darkBindingBundle() }
            false -> { lightBindingBundle() }
        }
    }
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