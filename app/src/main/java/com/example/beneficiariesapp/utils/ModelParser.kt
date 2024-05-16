package com.example.beneficiariesapp.utils

import com.example.beneficiariesapp.data.model.Beneficiary
import com.example.beneficiariesapp.data.model.BeneficiaryAddress
import org.json.JSONArray
import org.json.JSONObject

/**
 * Simple parser to map serialized/deserialized data to model.
 */
object ModelParser {

    fun jsonToBeneficiaries(json: JSONArray): List<Beneficiary> {
        val models = mutableListOf<Beneficiary>()

        for (i in 0 until json.length()) {
            val obj = json.getJSONObject(i)
            obj?.let {
                models.add(toBeneficiary(it))
            }
        }

        return models
    }

    private fun toBeneficiary(json: JSONObject) = Beneficiary(
        lastName = json.getString("lastName"),
        firstName = json.getString("firstName"),
        designationCode = json.getString("designationCode"),
        beneType = json.getString("beneType"),
        socialSecurityNumber = json.getString("socialSecurityNumber"),
        dateOfBirth = json.getString("dateOfBirth"),
        middleName = json.getString("middleName"),
        phoneNumber = json.getString("phoneNumber"),
        beneficiaryAddress = BeneficiaryAddress(
            firstLineMailing = json.getJSONObject("beneficiaryAddress").getString("firstLineMailing"),
            scndLineMailing = json.getJSONObject("beneficiaryAddress").getString("scndLineMailing"),
            city = json.getJSONObject("beneficiaryAddress").getString("city"),
            zipCode = json.getJSONObject("beneficiaryAddress").getString("zipCode"),
            stateCode = json.getJSONObject("beneficiaryAddress").getString("stateCode"),
            country = json.getJSONObject("beneficiaryAddress").getString("country")
        )
    )
}