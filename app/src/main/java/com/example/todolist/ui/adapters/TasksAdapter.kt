package com.example.todolist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.R
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.data.models.TaskData

class TasksAdapter : Adapter<TasksAdapter.TasksViewHolder>() {
    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
        ViewHolder(binding.root) {
        fun bind(taskData: TaskData) {
            binding.apply {


                ivDelete.setOnClickListener {
                    onDeleteClick.invoke(taskData, adapterPosition)
                }

                root.setOnClickListener {
                    onItemClick.invoke(taskData, adapterPosition)
                }
            }
        }
    }

    var models = mutableListOf<TaskData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val binding = ItemTaskBinding.bind(v)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onDeleteClick: (taskData: TaskData, position: Int) -> Unit = { _, _ -> }
    fun setOnDeleteClickListener(onDeleteClick: (taskData: TaskData, position: Int) -> Unit) {
        this.onDeleteClick = onDeleteClick
    }



    private var onItemClick: (taskData: TaskData, position: Int) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (taskData: TaskData, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    fun removeAtPosition(position: Int) {
        models.removeAt(position)
        notifyItemRemoved(position)
    }
}
