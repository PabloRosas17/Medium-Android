
package coffee.flavors.android_1.models.entity

sealed class ThemeTypes {
    data class THEME_BINDING(val mode: String) : ThemeTypes()
    data class THEME_DAY_NIGHT(val mode: String) : ThemeTypes()
    data class THEME_OVERLAY(val mode: String) : ThemeTypes()
    data class THEME_ATTRIBUTE(val mode: String) : ThemeTypes()
    object THEME_UNKNOWN : ThemeTypes()
}