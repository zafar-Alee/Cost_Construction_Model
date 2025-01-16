package com.za.cocomo

import android.animation.AnimatorSet
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.za.cocomo.databinding.RvItemBinding
import android.animation.ObjectAnimator

class adapter(
    var dataList: ArrayList<rv_model>,
    private val context: Context,
    private val onCheckboxStateChange: (Boolean, Double) -> Unit // Callback for state changes
) : RecyclerView.Adapter<adapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        anim(holder.itemView)

        val item = dataList[position]

        // Bind driver and checkbox values
        holder.binding.box1.text = item.driver
        holder.binding.value2.text = item.Vlow.toString()
        holder.binding.value3.text = item.low.toString()
        holder.binding.value4.text = item.normal.toString()
        holder.binding.value5.text = item.high.toString()
        holder.binding.value6.text = item.Vhigh.toString()

        val checkBoxes = listOf(
            holder.binding.value2,
            holder.binding.value3,
            holder.binding.value4,
            holder.binding.value5,
            holder.binding.value6
        )

        // Set checkboxes' initial state and listeners
        checkBoxes.forEachIndexed { index, checkBox ->
            checkBox.setOnCheckedChangeListener(null) // Prevent callback duplication
            checkBox.isChecked = item.selectedCheckboxIndex == index
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    updateSelection(position, index)
                } else if (item.selectedCheckboxIndex == index) {
                    clearSelection(position)
                }
            }
        }
    }

    private fun updateSelection(cardPosition: Int, checkboxIndex: Int) {
        val item = dataList[cardPosition]
        item.selectedCheckboxIndex = checkboxIndex
        val selectedValue = when (checkboxIndex) {
            0 -> item.Vlow
            1 -> item.low
            2 -> item.normal
            3 -> item.high
            4 -> item.Vhigh
            else -> null
        }

        selectedValue?.let { onCheckboxStateChange(true, it) }
        notifyItemChanged(cardPosition)
    }

    private fun clearSelection(cardPosition: Int) {
        val item = dataList[cardPosition]
        val previouslySelectedIndex = item.selectedCheckboxIndex
        val deselectedValue = when (previouslySelectedIndex) {
            0 -> item.Vlow
            1 -> item.low
            2 -> item.normal
            3 -> item.high
            4 -> item.Vhigh
            else -> null
        }

        item.selectedCheckboxIndex = null
        deselectedValue?.let { onCheckboxStateChange(false, it) }
        notifyItemChanged(cardPosition)
    }

//



    private fun anim(view: View) {
        // Create a fade-in effect with ObjectAnimator
        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        fadeIn.duration = 1000

        // Add a subtle translation effect to make the animation feel dynamic
        val translateY = ObjectAnimator.ofFloat(view, "translationY", 70f, 0f)
        translateY.duration = 500

        // Play animations together
        fadeIn.start()
        translateY.start()



//        view.scaleX = 0.8f
//        view.scaleY = 0.8f
//        view.animate()
//            .scaleX(1f) // Scale back to original size
//            .scaleY(1f)
//            .setDuration(300)
//            .start()



//        view.translationX = -view.width.toFloat()
//        view.alpha = 0f
//        view.animate()
//            .translationX(0f) // Slide into place
//            .alpha(1f) // Fade in simultaneously
//            .setDuration(500)
//            .start()





    }

}
