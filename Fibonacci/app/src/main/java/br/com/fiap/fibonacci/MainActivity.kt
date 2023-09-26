package br.com.fiap.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.fiap.fibonacci.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
    }

    private fun incrementFibonacci(sequenceNumber: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            binding.buttonIncrementFibonacci.isEnabled = false
            val fibonacciNumber: Int = CoroutineFactory.calculateFibonacci(
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
    }
}