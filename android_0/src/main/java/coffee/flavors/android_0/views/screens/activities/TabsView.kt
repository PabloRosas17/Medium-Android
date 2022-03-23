
package coffee.flavors.android_0.views.screens.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import coffee.flavors.android_0.R
import coffee.flavors.android_0.controllers.adapter.HorizontalPagerAdapter
import coffee.flavors.android_0.controllers.adapter.VerticalPagerAdapter
import coffee.flavors.android_0.controllers.adapter.VerticalRecyclerAdapter
import coffee.flavors.android_0.controllers.viewmodel.TabsViewModel
import coffee.flavors.android_0.core.factory.TabsViewModelFactory
import coffee.flavors.android_0.databinding.LayoutTabsViewBinding
import coffee.flavors.android_0.views.presenters.activities.TabsPresenter
import coffee.flavors.android_zzz.core.AndroidZZZ
import coffee.flavors.common.tools.utils.BinderIf
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.play.core.splitcompat.SplitCompat

class TabsView : AppCompatActivity() ,
    BinderIf<LayoutTabsViewBinding> {
    private val factory: ViewModelProvider.Factory = TabsViewModelFactory()
    private val tabsVm by viewModels<TabsViewModel> { factory }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        val diShare = (this.application as AndroidZZZ).diShare
        this.fireUiBindings()
    }
    override lateinit var mBinding: LayoutTabsViewBinding
    override fun fireUiBindings() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_tabs_view)
        mBinding.mPresenter = TabsPresenter(this)
        mBinding.executePendingBindings()

        this.registerUi()
        this.subscribeUi()
    }
    private fun registerUi() {
        this.mBinding.verVp2.adapter = VerticalPagerAdapter(this)
        this.mBinding.horVp2.adapter = HorizontalPagerAdapter(this)
    }
    private fun subscribeUi() {
        this.mBinding.verRv.layoutManager = LinearLayoutManager(this)
        mBinding.verRv.adapter = VerticalRecyclerAdapter(this)

        mBinding.verVp2.adapter = VerticalPagerAdapter(this)
        TabLayoutMediator(mBinding.horTl, mBinding.horVp2) { tab, position ->
            when(position){
                HorizontalPagerAdapter.TAB_ELEMENT_0 -> { tab.text = "HORIZONTAL - LEFT" }
                HorizontalPagerAdapter.TAB_ELEMENT_1 -> { tab.text = "HORIZONTAL - RIGHT" }
            }
        }.attach()
        this.mBinding.verVp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for(i in 0 until VerticalRecyclerAdapter.N_ITEMS) {
                    mBinding.verRv[i].background =
                        this@TabsView.resources?.getDrawable(R.drawable.dot_default,null)
                }
                mBinding.verRv[position].background =
                    this@TabsView.resources?.getDrawable(R.drawable.dot_selected,null)
            }
        })
    }
}