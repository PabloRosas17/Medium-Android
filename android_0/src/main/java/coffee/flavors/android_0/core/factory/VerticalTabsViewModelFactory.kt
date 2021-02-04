/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_0.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_0.controllers.viewmodel.VerticalTabsViewModel

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, create vertical tabs view model and serve them to the factory.
 */

/**
 * @desc create vertical tabs view model.
 */
class VerticalTabsViewModelFactory : FactoryIf<VerticalTabsViewModel> {

    /**
     * serves vertical tabs view model to the factory.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(VerticalTabsViewModel::class.java).newInstance()
    }
}