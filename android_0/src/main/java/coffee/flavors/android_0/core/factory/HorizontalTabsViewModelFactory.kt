
package coffee.flavors.android_0.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_0.controllers.viewmodel.HorizontalTabsViewModel

class HorizontalTabsViewModelFactory : FactoryIf<HorizontalTabsViewModel> {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HorizontalTabsViewModel::class.java).newInstance()
    }
}