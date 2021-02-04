/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_0.controllers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import coffee.flavors.android_0.R
import coffee.flavors.android_0.views.screens.activities.TabsView

/**
 * @author, evolandlupiz
 * @date, 5/6/2020
 * @property, Medium-Android
 * @purpose, adapter for recycler vertical orientation.
 */

/**
 * @desc defines the adapter for recycler vertical view.
 */
class VerticalRecyclerAdapter(private val tv: TabsView) : RecyclerView.Adapter<VerticalRecyclerAdapter.ViewHolder>() {

    /**
     * Number of items the TabLayout will contain.
     * @return Integer representing the item count.
     */
    override fun getItemCount(): Int = N_ITEMS

    /**
     * definition for bounded view holder item.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            when(it.id) {
                R.id.item_dots_root -> {
                    for(i in 0 until N_ITEMS) {
                        tv.mBinding.verRv[i].background =
                            it.context.resources.getDrawable(R.drawable.dot_default,null)
                    }
                    holder.itemView.background =
                        it.context.resources.getDrawable(R.drawable.dot_selected,null)
                    tv.mBinding.verVp2.currentItem = position
                }
            }
        }
    }

    /**
     * inflates the layout for the view holder item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mDotInflater = LayoutInflater.from(parent.context)
        val mView = mDotInflater.inflate(R.layout.item_dot, parent, false)
        return ViewHolder(mView)
    }

    /**
     * @desc view holder pattern that can hold reference to the current item.
     */
    inner class ViewHolder(private val iv: View): RecyclerView.ViewHolder(iv){
        /**
         * binding definition for the current item based on position.
         */
        fun bind(position: Int) {}
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