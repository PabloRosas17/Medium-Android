/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_1.core.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import coffee.flavors.android_1.controllers.viewmodel.ThemesViewModel

/**
 * @author, evolandlupiz
 * @date, 6/6/2020
 * @property, Medium-Android
 * @purpose, create themes view model and serve them to the factory.
 */

/**
 * @desc create themes view model.
 */
class ThemesViewModelFactory(private val application: Application) : FactoryIf<ThemesViewModel> {

    /**
     * serves tabs view model to the factory.
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            ThemesViewModel::class.java -> ThemesViewModel(application)
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}