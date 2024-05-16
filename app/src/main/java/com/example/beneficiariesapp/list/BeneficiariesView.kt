package com.example.beneficiariesapp.list

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.beneficiariesapp.R
import com.example.beneficiariesapp.data.model.Beneficiary
import com.example.beneficiariesapp.utils.Utils

class BeneficiariesView(context: Context) : FrameLayout(context) {

    private var root = LinearLayout(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
        )
        orientation = LinearLayout.VERTICAL
        setPadding(Utils
            .dpToPx(16), Utils.dpToPx(16), Utils.dpToPx(16), Utils.dpToPx(16)
        )
    }

    private var headerContainer = FrameLayout(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
        )
    }

    private var moreContainer = LinearLayout(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT,
        )
        orientation = LinearLayout.VERTICAL
        setPadding(Utils
            .dpToPx(0), Utils.dpToPx(8), Utils.dpToPx(0), Utils.dpToPx(0)
        )
    }

    private val nameTv: TextView
    private val beneType: TextView
    private val moreDetails: TextView
    private val address: TextView
    private val imageView: ImageView

    init {
        // name
        nameTv = TextView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setTextSize(TypedValue.COMPLEX_UNIT_SP,20f);
            setTypeface(null, Typeface.BOLD);
        }
        imageView = ImageView(context).apply {
            layoutParams = LayoutParams(
                Utils.dpToPx(36),
                Utils.dpToPx(36),
            ).apply {
                gravity = Gravity.END or Gravity.TOP
            }
            setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_arrow_drop_up_24))
        }
        headerContainer.addView(nameTv)
        headerContainer.addView(imageView)

        // beneType
        beneType = TextView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        }

        // moreDetails
        moreDetails = TextView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        }
        moreContainer.addView(moreDetails)
        // address
        address = TextView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        }
        moreContainer.addView(address)

        root.addView(headerContainer)
        root.addView(beneType)
        root.addView(moreContainer)

        addView(root)
    }

    fun bind(bnfs: Beneficiary, expanded: Boolean) {
        val dob = Utils.formatDate(bnfs.dateOfBirth!!)

        nameTv.text = "${bnfs.firstName}, ${bnfs.lastName}"
        beneType.text = if (bnfs.designationCode == "P") "${bnfs.beneType} (Primary)" else "${bnfs.beneType} (Contingent)"
        moreDetails.text = "${bnfs.socialSecurityNumber}, ${dob}, ${bnfs.phoneNumber}"
        address.text = "${bnfs.beneficiaryAddress?.firstLineMailing}"

        if (expanded) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_arrow_drop_up_24))
            moreContainer.visibility = VISIBLE
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.baseline_arrow_drop_down_24))
            moreContainer.visibility = GONE
        }
    }
}
