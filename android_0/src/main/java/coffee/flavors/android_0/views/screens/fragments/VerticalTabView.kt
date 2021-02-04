/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_0.views.screens.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import coffee.flavors.android_0.R
import coffee.flavors.android_0.controllers.viewmodel.VerticalTabsViewModel
import coffee.flavors.android_0.core.factory.VerticalTabsViewModelFactory
import coffee.flavors.android_0.databinding.LayoutVerticalTabsViewBinding
import coffee.flavors.common.tools.utils.BinderIf
import coffee.flavors.android_0.views.presenters.fragments.VerticalTabsPresenter
import coffee.flavors.android_zzz.core.AndroidZZZ

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, VerticalTabsView will display a vertical tab layout.
 */

/**
 * @desc VerticalTabsView servers as the vertical tabs layout.
 */
class VerticalTabView : Fragment(),
    BinderIf<LayoutVerticalTabsViewBinding> {

    /**
     * factory for [VerticalTabsViewModelFactory]
     */
    private val factory: ViewModelProvider.Factory = VerticalTabsViewModelFactory()

    /**
     * view model for [VerticalTabsViewModel]
     */
    private val horVerVm by viewModels<VerticalTabsViewModel> { factory }

    /**
     * binding type definition
     */
    override lateinit var mBinding: LayoutVerticalTabsViewBinding

    /**
     * invoke di.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val diShare = (requireActivity().application as AndroidZZZ).diShare
    }

    /**
     * initialize the resources for this fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        this.mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_vertical_tabs_view, container, false)
        this.fireUiBindings()
        this.registerUi()
        this.subscribeUi()
        return this.mBinding.root
    }

    /**
     * binding is generated through BR class, this will set the view through binding
     * bind presenter account instance with this view, execute pending binding
     */
    override fun fireUiBindings() {
        mBinding.mPresenter = VerticalTabsPresenter(this)
        mBinding.executePendingBindings()
    }

    /**
     * ui association
     */
    private fun registerUi() {}

    /**
     * ui listeners
     */
    private fun subscribeUi() {}
}