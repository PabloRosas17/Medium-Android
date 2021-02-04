/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_zzz.core.factory

import androidx.lifecycle.ViewModel
import coffee.flavors.android_zzz.controllers.viewmodel.SelectionViewModel

/**
 * @author, evolandlupiz
 * @date, 5/12/2020
 * @property, Medium-Android
 * @purpose, create selection view model and serve them to the factory.
 */

/**
 * @desc create selection view model.
 */
class SelectionViewModelFactory : FactoryIf<SelectionViewModel> {

    /**
     * serves selection view model to the factory.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SelectionViewModelFactory::class.java).newInstance()
    }
}