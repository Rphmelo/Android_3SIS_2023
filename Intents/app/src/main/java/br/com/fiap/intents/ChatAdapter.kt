package br.com.fiap.intents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {

    private val chatList: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_chat_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount() = chatList.size
}