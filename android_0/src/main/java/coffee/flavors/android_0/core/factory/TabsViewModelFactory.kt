/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_0.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_0.controllers.viewmodel.TabsViewModel

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, create tabs view model and serve them to the factory.
 */

/**
 * @desc create tabs view model.
 */
class TabsViewModelFactory : FactoryIf<TabsViewModel> {

    /**
     * serves tabs view model to the factory.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TabsViewModel::class.java).newInstance()
    }
}