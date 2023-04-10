package br.com.fiap.chatgpt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.chatgpt.R
import br.com.fiap.chatgpt.data.AnswerModel

class AnswerAdapter(
    private val answerList: List<AnswerModel>,
    private val shareAction: (String) -> Unit
) : RecyclerView.Adapter<AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_answer_item, parent, false),
            shareAction
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(answerList[position])
    }

    override fun getItemCount() = answerList.size
}