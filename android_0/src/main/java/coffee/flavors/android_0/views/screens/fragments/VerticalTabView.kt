
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
import coffee.flavors.android_0.views.presenters.fragments.VerticalTabsPresenter
import coffee.flavors.android_zzz.core.AndroidZZZ
import coffee.flavors.common.tools.utils.BinderIf

class VerticalTabView : Fragment(),
    BinderIf<LayoutVerticalTabsViewBinding> {
    private val factory: ViewModelProvider.Factory = VerticalTabsViewModelFactory()
    private val horVerVm by viewModels<VerticalTabsViewModel> { factory }
    override lateinit var mBinding: LayoutVerticalTabsViewBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val diShare = (requireActivity().application as AndroidZZZ).diShare
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        this.mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_vertical_tabs_view, container, false)
        this.fireUiBindings()
        this.registerUi()
        this.subscribeUi()
        return this.mBinding.root
    }
    override fun fireUiBindings() {
        mBinding.mPresenter = VerticalTabsPresenter(this)
        mBinding.executePendingBindings()
    }
    private fun registerUi() {}
    private fun subscribeUi() {}
}