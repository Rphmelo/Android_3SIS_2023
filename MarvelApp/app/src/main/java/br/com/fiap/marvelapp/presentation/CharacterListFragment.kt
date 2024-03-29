package br.com.fiap.marvelapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.com.fiap.marvelapp.databinding.FragmentCharacterListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()

    private val characterListAdapter by lazy {
        CharacterListAdapter()
    }

    private fun getMarvelCharacters() {
        lifecycleScope.launch {
            viewModel.listCharacters()
        }
    }

    private fun setObservers() {
        viewModel.characterErrorState.observe(
            viewLifecycleOwner,
            Observer {
                Snackbar.make(
                    binding.recyclerViewCharacters,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.characterLoading.isVisible = false
                binding.buttonTryAgain.isVisible = true
                binding.buttonTryAgain.setOnClickListener {
                    lifecycleScope.launch {
                        viewModel.listCharacters()
                    }
                }
            }
        )
        viewModel.characterSuccessState.observe(
            viewLifecycleOwner,
            Observer {
                binding.buttonTryAgain.isVisible = false
                binding.characterLoading.isVisible = false
                binding.recyclerViewCharacters.isVisible = true
                characterListAdapter.setData(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setObservers()
        getMarvelCharacters()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCharacters.setHasFixedSize(true)
        binding.recyclerViewCharacters.adapter = characterListAdapter
    }

}