
package coffee.flavors.common.tools.utils

interface BinderIf<V> {
    var mBinding: V
    fun fireUiBindings()
}