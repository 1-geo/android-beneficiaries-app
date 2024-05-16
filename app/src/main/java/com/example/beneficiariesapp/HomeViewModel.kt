package com.example.beneficiariesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beneficiariesapp.data.DefaultBeneficiariesRepository
import com.example.beneficiariesapp.data.model.Beneficiary
import com.example.flow_tutorial.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val repository = DefaultBeneficiariesRepository()
    private val _uiState = MutableStateFlow<UiState<List<Beneficiary>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Beneficiary>>> = _uiState

    fun fetchCountries(context: Context) {
        viewModelScope.launch {
            repository.retrieveBeneficiaries(context).collect { beneficiaries ->
                _uiState.update { UiState.Success(beneficiaries) }
            }
        }
    }
}