package br.com.fiap.chatgpt.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.chatgpt.data.TalkModel
import br.com.fiap.chatgpt.databinding.ViewQuestionItemBinding

class QuestionViewHolder(
    itemView: View,
    private val onCardClick: (TalkModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewQuestionItemBinding.bind(itemView)

    fun bind(item: TalkModel) {
        binding.questionTitle.text = item.question
        binding.root.setOnClickListener {
            onCardClick.invoke(item)
        }
    }
}