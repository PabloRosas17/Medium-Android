
package coffee.flavors.android_0.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_0.controllers.viewmodel.VerticalTabsViewModel

class VerticalTabsViewModelFactory : FactoryIf<VerticalTabsViewModel> {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(VerticalTabsViewModel::class.java).newInstance()
    }
}