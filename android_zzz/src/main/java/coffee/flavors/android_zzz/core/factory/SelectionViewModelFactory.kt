
package coffee.flavors.android_zzz.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_zzz.controllers.viewmodel.SelectionViewModel

class SelectionViewModelFactory : FactoryIf<SelectionViewModel> {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SelectionViewModelFactory::class.java).newInstance()
    }
}