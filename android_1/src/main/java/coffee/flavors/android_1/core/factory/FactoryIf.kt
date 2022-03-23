
package coffee.flavors.android_1.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface FactoryIf<T> : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T
}