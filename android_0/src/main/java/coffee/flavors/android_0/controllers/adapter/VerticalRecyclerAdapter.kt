
package coffee.flavors.android_0.controllers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import coffee.flavors.android_0.R
import coffee.flavors.android_0.views.screens.activities.TabsView

class VerticalRecyclerAdapter(private val tv: TabsView) : RecyclerView.Adapter<VerticalRecyclerAdapter.ViewHolder>() {
    override fun getItemCount(): Int = N_ITEMS
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mDotInflater = LayoutInflater.from(parent.context)
        val mView = mDotInflater.inflate(R.layout.item_dot, parent, false)
        return ViewHolder(mView)
    }
    inner class ViewHolder(private val iv: View): RecyclerView.ViewHolder(iv){
        fun bind(position: Int) {}
    }
    companion object {
        const val TAB_ELEMENT_0 = 0
        const val TAB_ELEMENT_1 = 1
        const val N_ITEMS = 2
    }
}