# Beneficiaries App
Simple android app created with UI programmatically created in Kotlin. List is displayed using RecyclerView. 

Implementation with data access layer. BeneficiariesRepository will load beneficiaries locally from assets.

Coroutines used to fetch from ViewModel within viewModelScope.

## Improvements
- Improve the UI design a bit, built it quickly to show the implementation.
- Json serializing/deserialization can use a framework to simplify things (gson).
- Unit tests using mockito.
