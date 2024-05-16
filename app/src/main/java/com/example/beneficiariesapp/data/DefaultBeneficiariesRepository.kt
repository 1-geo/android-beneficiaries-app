package com.example.beneficiariesapp.data

import android.content.Context
import com.example.beneficiariesapp.data.model.Beneficiary
import com.example.beneficiariesapp.utils.ModelParser
import com.example.beneficiariesapp.utils.FileUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray

/**
 * Default implementation, will load locally saved json file.
 */
class DefaultBeneficiariesRepository : BeneficiariesRepository {
    override fun retrieveBeneficiaries(context: Context): Flow<List<Beneficiary>> = flow {
        (FileUtils.readJson(context, "beneficiaries.json") as? JSONArray)?.let {
            emit(ModelParser.jsonToBeneficiaries(it))
        }
    }
}