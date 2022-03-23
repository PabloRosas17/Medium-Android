
package coffee.flavors.android_1.core.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import coffee.flavors.android_1.controllers.viewmodel.ThemesViewModel

class ThemesViewModelFactory(private val application: Application) : FactoryIf<ThemesViewModel> {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            ThemesViewModel::class.java -> ThemesViewModel(application)
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}