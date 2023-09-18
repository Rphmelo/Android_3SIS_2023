package br.com.fiap.todoapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.children
import br.com.fiap.todoapp.database.TaskStatus
import br.com.fiap.todoapp.databinding.ActivityMainBinding
import br.com.fiap.todoapp.databinding.ViewFilterItemBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

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
        setObservers()
    }

    private fun setObservers() {
        viewModel.snackBarStatus.observe(this) {
            Snackbar.make(
                binding.recyclerViewTasks,
                "Filtrando tarefas ${it.title}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupRecycler() {
        binding.recyclerViewTasks.setHasFixedSize(true)
        binding.recyclerViewTasks.adapter = taskAdapter
    }

    private fun getTaskList() {
        taskAdapter.setData(viewModel.selectAll())
    }

    private fun getTaskFromStatus(status: TaskStatus) {
        taskAdapter.setData(viewModel.selectByStatus(status))
    }

    private fun setupFilters() {
        TaskStatus.values().forEach {
            val filterOption = ViewFilterItemBinding.inflate(
                layoutInflater,
                binding.taskFilters,
                false
            ).root as? Chip
            filterOption?.id = ViewCompat.generateViewId()
            filterOption?.text = it.title
            filterOption?.isChecked = it.title == viewModel.selectedFilter?.title
            binding.taskFilters.addView(filterOption)
        }

        binding.taskFilters.setOnCheckedChangeListener { group, checkedId ->
            val checkedChip = group.children.find { it.id == checkedId } as? Chip

            if(checkedChip == null) {
                viewModel.selectedFilter = null
            } else {
                val taskStatusCheckedChip = TaskStatus.getByTitle(checkedChip?.text.toString())
                viewModel.selectedFilter = taskStatusCheckedChip
            }

            getFilteredList()
        }
    }

    private fun getFilteredList() {
        if(viewModel.selectedFilter == null) {
            getTaskList()
        } else {
            getTaskFromStatus(viewModel.selectedFilter!!)
        }
    }
}