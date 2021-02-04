/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.common.tools.utils

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, Ensures views keep their promises for the view - view-model relationship.
 */

/**
 * @desc contract for classes using generated BR files.
 */
interface BinderIf<V> {

    /**
     * T type of the calling class generated layout, associate the binding element.
     */
    var mBinding: V

    /**
     * Method used to enforce ui bindings happen.
     */
    fun fireUiBindings()
}