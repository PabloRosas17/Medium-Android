
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
import coffee.flavors.android_0.controllers.viewmodel.HorizontalTabsViewModel
import coffee.flavors.android_0.core.factory.HorizontalTabsViewModelFactory
import coffee.flavors.android_0.databinding.LayoutHorizontalTabsViewBinding
import coffee.flavors.android_0.views.presenters.fragments.HorizontalTabsPresenter
import coffee.flavors.android_zzz.core.AndroidZZZ
import coffee.flavors.common.tools.utils.BinderIf

class HorizontalTabView : Fragment(),
    BinderIf<LayoutHorizontalTabsViewBinding> {

    private val factory: ViewModelProvider.Factory = HorizontalTabsViewModelFactory()

    private val horTabVm by viewModels<HorizontalTabsViewModel> { factory }

    override lateinit var mBinding: LayoutHorizontalTabsViewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val diShare = (requireActivity().application as AndroidZZZ).diShare
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        this.mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_horizontal_tabs_view, container, false)
        this.fireUiBindings()
        this.registerUi()
        this.subscribeUi()
        return this.mBinding.root
    }

    override fun fireUiBindings() {
        mBinding.mPresenter = HorizontalTabsPresenter(this)
        mBinding.executePendingBindings()
    }

    private fun registerUi() {}

    private fun subscribeUi() {}
}