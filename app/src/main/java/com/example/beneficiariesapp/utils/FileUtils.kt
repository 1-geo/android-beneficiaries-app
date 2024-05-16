package com.example.beneficiariesapp.utils

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object FileUtils {

    /**
     * @param context
     * @param fileName
     * @return JSONObject, or JSONArray.
     */
    fun readJson(context: Context, fileName: String): Any {
        val file = openFile(context, fileName).trim()
        return if (file.startsWith("{"))
            JSONObject(file)
        else if (file.startsWith("["))
            JSONArray(file)
        else
            throw IllegalArgumentException()
    }

    private fun openFile(context: Context, fileName: String) = context
        .assets
        .open(fileName)
        .bufferedReader().use { it.readText() }
}