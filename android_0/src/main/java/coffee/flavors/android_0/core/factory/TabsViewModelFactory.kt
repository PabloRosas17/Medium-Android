
package coffee.flavors.android_0.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_0.controllers.viewmodel.TabsViewModel

class TabsViewModelFactory : FactoryIf<TabsViewModel> {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TabsViewModel::class.java).newInstance()
    }
}