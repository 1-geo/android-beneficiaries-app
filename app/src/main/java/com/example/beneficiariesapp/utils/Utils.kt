package com.example.beneficiariesapp.utils

import android.content.res.Resources
import android.util.TypedValue
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    /**
     * @return pixed given a dp value
     */
    fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

    /**
     * @return formatted date string as MM/DD/YYYY
     */
    fun formatDate (inputDateString: String): String {
        val inputFormat = SimpleDateFormat("MMddyyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val date = inputFormat.parse(inputDateString)
        return outputFormat.format(date!!)
    }
}