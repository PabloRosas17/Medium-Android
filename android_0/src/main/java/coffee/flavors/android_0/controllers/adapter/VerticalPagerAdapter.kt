
package coffee.flavors.android_0.controllers.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import coffee.flavors.android_0.views.screens.activities.TabsView
import coffee.flavors.android_0.views.screens.fragments.VerticalTabView

class VerticalPagerAdapter (private val vt: TabsView): FragmentStateAdapter(vt.supportFragmentManager,vt.lifecycle) {
    override fun getItemCount(): Int = N_ITEMS
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            TAB_ELEMENT_0 -> { VerticalTabView() }
            TAB_ELEMENT_1 -> { VerticalTabView() }
            else -> { throw RuntimeException("VerticalPagerAdapter.kt, createFragment()") }
        }
    }
    companion object {
        const val TAB_ELEMENT_0 = 0
        const val TAB_ELEMENT_1 = 1
        const val N_ITEMS = 2
    }
}