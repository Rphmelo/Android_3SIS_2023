package br.com.fiap.todoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.children
import br.com.fiap.todoapp.database.TaskStatus
import br.com.fiap.todoapp.databinding.ActivityMainBinding
import br.com.fiap.todoapp.databinding.ViewFilterItemBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()

    private val taskAdapter by lazy {
        TaskAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        getFilteredList()
        setupFilters()
    }

    private fun setupRecycler() {
        binding.recyclerViewTasks.setHasFixedSize(true)
        binding.recyclerViewTasks.adapter = taskAdapter
    }

    private fun getTaskList() {
        taskAdapter.setData(viewModel.selectAll())
    }

    private fun getTaskFromStatus(status: List<TaskStatus>) {
        taskAdapter.setData(viewModel.selectByStatus(status))
    }

    private fun setupFilters() {
        TaskStatus.values().forEach {
            val filterOption = ViewFilterItemBinding.inflate(layoutInflater, binding.taskFilters, false)
            filterOption.filter.text = it.title
            binding.taskFilters.addView(filterOption.root)
        }

        binding.taskFilters.setOnCheckedChangeListener { group, checkedId ->
            binding.taskFilters.children.forEach { view ->
                (view as? Chip)?.let { chip ->
                    if(chip.isChecked) {
                        viewModel.selectedFilters.add(TaskStatus.getByTitle(chip.text.toString()))
                    } else {
                        viewModel.selectedFilters.remove(TaskStatus.getByTitle(chip.text.toString()))
                    }
                }
            }

            getFilteredList()
        }
    }

    private fun getFilteredList() {
        if(viewModel.selectedFilters.isEmpty()) {
            getTaskList()
        } else {
            getTaskFromStatus(viewModel.selectedFilters.toList())
        }
    }
}