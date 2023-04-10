package br.com.fiap.chatgpt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.chatgpt.R
import br.com.fiap.chatgpt.data.TalkModel

class QuestionAdapter(
    private val onCardClick: (TalkModel) -> Unit,
    private val talkList: List<TalkModel>
) : RecyclerView.Adapter<QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_question_item, parent, false),
            onCardClick
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(talkList[position])
    }

    override fun getItemCount() = talkList.size
}