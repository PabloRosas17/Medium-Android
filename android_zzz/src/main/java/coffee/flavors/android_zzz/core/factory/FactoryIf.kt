/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_zzz.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author, evolandlupiz
 * @date, 5/12/2020
 * @property, Medium-Android
 * @purpose, factory that creates view models of type T.
 */

/**
 * @desc factory definition to create objects of type [T]
 */
interface FactoryIf<T> : ViewModelProvider.Factory {

    /**
     * create contract for view model of type T.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
}