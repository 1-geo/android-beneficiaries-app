package com.example.beneficiariesapp

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiariesapp.data.model.Beneficiary
import com.example.beneficiariesapp.list.BeneficiariesAdapter
import com.example.flow_tutorial.utils.UiState
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel

    private lateinit var root: FrameLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRoot()
        setupProgress()
        setContentView(root)

        setupViewModel()
        setupObserver()
    }

    fun setupProgress() {
        progressBar = ProgressBar(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
            ).apply {
                gravity = Gravity.CENTER
            }
        }
        root.addView(progressBar)
    }

    fun setupRoot() {
        root = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            setupRecyclerView(it.data)
                            progressBar.visibility = View.GONE
                        }
                        is UiState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(data: List<Beneficiary>) {
        recyclerView = RecyclerView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = BeneficiariesAdapter(this@HomeActivity, data)
        }

        root.addView(recyclerView)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.fetchCountries(this@HomeActivity)
    }
}

