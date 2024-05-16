package com.example.beneficiariesapp.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiariesapp.data.model.Beneficiary


class BeneficiariesAdapter(
    private val context: Context,
    private val bnfsList: List<Beneficiary>,
) :
    RecyclerView.Adapter<BeneficiariesAdapter.BeneficiariesHolder>() {

    var expandedMap = mutableMapOf<Int, Boolean>()

    init {
        bnfsList.forEachIndexed { index, _ ->
            expandedMap[index] = false
        }
    }

    class BeneficiariesHolder(val view: BeneficiariesView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiariesHolder {
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val itemView = BeneficiariesView(context)
        itemView.layoutParams = lp
        return BeneficiariesHolder(itemView)
    }

    override fun onBindViewHolder(holder: BeneficiariesHolder, position: Int) {
        holder.view.bind(bnfsList[position], expandedMap[position]!!)
        holder.view.setOnClickListener {
            val isExpanded = !expandedMap[position]!!
            holder.view.bind(bnfsList[position], expandedMap[position]!!)
            expandedMap[position] = isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = bnfsList.size
}
