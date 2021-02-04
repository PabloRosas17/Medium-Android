/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_0.controllers.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import coffee.flavors.android_0.views.screens.activities.TabsView
import coffee.flavors.android_0.views.screens.fragments.VerticalTabView

/**
 * @author, evolandlupiz
 * @date, 5/6/2020
 * @property, Medium-Android
 * @purpose, adapter for viewpager2 vertical orientation.
 */

/**
 * @desc defines the adapter for vertical tab view.
 */
class VerticalPagerAdapter (private val vt: TabsView): FragmentStateAdapter(vt.supportFragmentManager,vt.lifecycle) {

    /**
     * Number of items the TabLayout will contain.
     * @return Integer representing the item count.
     */
    override fun getItemCount(): Int = N_ITEMS
    /**
     * When a tab is tapped, generate a fragment instance.
     * @return fragment based on position tapped.
     * @throws RuntimeException when the position is unknown.
     */
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            TAB_ELEMENT_0 -> { VerticalTabView() }
            TAB_ELEMENT_1 -> { VerticalTabView() }
            else -> { throw RuntimeException("VerticalPagerAdapter.kt, createFragment()") }
        }
    }

    /**
     * Definitions for the ViewPager2 embedded TabLayout.
     */
    companion object {
        const val TAB_ELEMENT_0 = 0
        const val TAB_ELEMENT_1 = 1
        const val N_ITEMS = 2
    }
}