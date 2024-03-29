package br.com.fiap.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.com.fiap.fibonacci.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<FibonacciViewModel>()
    private var coroutine: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
        setObservers()
    }

    private fun setObservers() {
        viewModel.fibonacciIsFinished.observe(this, Observer { fibonacciNumber ->
            Snackbar.make(
                binding.buttonIncrementFibonacci,
                "$fibonacciNumber",
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    private fun incrementFibonacci(sequenceNumber: Int) {
        coroutine = lifecycleScope.launch(Dispatchers.Main) {
            binding.buttonIncrementFibonacci.isEnabled = false
            val fibonacciNumber: Int = viewModel.calculateFibonacci(
                sequenceNumber
            )
            binding.labelFibonacciNumber.text = "Valor do fibonacci: $fibonacciNumber"
            binding.buttonIncrementFibonacci.isEnabled = true
        }
    }

    private fun setupButtons() {
        var countClick = binding.labelFibonacciSequence.text.toString().toInt()
        binding.buttonIncrementFibonacci.setOnClickListener {
            countClick++
            incrementFibonacci(countClick)
            binding.labelFibonacciSequence.text = "Sequencia de fibonacci: $countClick"
        }

        binding.buttonStopFibonacci.setOnClickListener {
            binding.buttonIncrementFibonacci.isEnabled = true
            coroutine?.cancel()
        }
    }
}