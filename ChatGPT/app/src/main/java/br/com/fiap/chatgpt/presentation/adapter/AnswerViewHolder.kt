package br.com.fiap.chatgpt.presentation.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.chatgpt.data.AnswerModel
import br.com.fiap.chatgpt.databinding.ViewAnswerItemBinding

class AnswerViewHolder(
    itemView: View,
    private val shareAction: (String) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewAnswerItemBinding.bind(itemView)

    fun bind(item: AnswerModel) {
        binding.answerValue.text = item.answer
        binding.buttonShareMessage.isVisible = item.hasShareAction
        binding.buttonShareMessage.setOnClickListener {
            shareAction.invoke(item.answer)
        }
    }
}