package br.com.fiap.todoapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.todoapp.R
import br.com.fiap.todoapp.database.TaskModel
import br.com.fiap.todoapp.database.TaskStatus
import br.com.fiap.todoapp.databinding.ViewTaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ViewTaskItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_task_item,
                parent,
                false
            )
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindView( differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<TaskModel>) {
        differ.submitList(list)
    }

    inner class TaskViewHolder(
        private val view: ViewTaskItemBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bindView(item: TaskModel) {
            view.taskTitle.text = item.title
            view.taskStatus.text = item.status.title

            val statusColor = when(item.status) {
                TaskStatus.PROGRESS -> {
                    R.color.progress
                }
                TaskStatus.PENDING -> {
                    R.color.pending
                }
                TaskStatus.COMPLETED -> {
                    R.color.completed
                }
            }
            view.taskStatus.setTextColor(
                ContextCompat.getColor(view.root.context, statusColor)
            )
        }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<TaskModel>(){
            override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}