package br.com.fiap.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.intents.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding
    private val sharedMessage by lazy {
        intent.extras?.getString(Intent.EXTRA_TEXT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSharedMessage()
        setupButtons()
    }

    private fun setupSharedMessage() {
        sharedMessage?.let {
            binding.textInputEditTextMessage.setText(it)
        }
    }

    private fun setupButtons() {
        binding.buttonSendMessage.setOnClickListener {
            binding.messageValue.text = binding.textInputEditTextMessage.text
            clearText()
        }

        binding.buttonShareMessage.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.messageValue.text.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, getString(R.string.chooser_title))

            startActivity(shareIntent)
        }
    }

    private fun clearText() {
        binding.textInputEditTextMessage.text?.clear()
    }
}