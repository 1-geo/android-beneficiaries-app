package com.example.beneficiariesapp.data

import android.content.Context
import com.example.beneficiariesapp.data.model.Beneficiary
import kotlinx.coroutines.flow.Flow

/**
 * Repository to retrieve Beneficiary data.
 */
interface BeneficiariesRepository {
    fun retrieveBeneficiaries(context: Context): Flow<List<Beneficiary>>
}