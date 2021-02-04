/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_1.models.entity

/**
 * @author, evolandlupiz
 * @date, 6/7/2020
 * @property, Medium-Android
 * @purpose, extensive definition for theme types.
 */

/**
 * @desc specific set of theme types.
 */
sealed class ThemeTypes {

    /**
     * theme binding definition.
     * @param mode as the theme mode
     */
    data class THEME_BINDING(val mode: String) : ThemeTypes()

    /**
     * theme daynigh definition.
     * @param mode as the theme mode
     */
    data class THEME_DAY_NIGHT(val mode: String) : ThemeTypes()

    /**
     * theme overlay definition.
     * @param mode as the theme mode
     */
    data class THEME_OVERLAY(val mode: String) : ThemeTypes()

    /**
     * theme attribute definition.
     * @param mode as the theme mode
     */
    data class THEME_ATTRIBUTE(val mode: String) : ThemeTypes()

    /**
     * theme unknown definition.
     */
    object THEME_UNKNOWN : ThemeTypes()
}