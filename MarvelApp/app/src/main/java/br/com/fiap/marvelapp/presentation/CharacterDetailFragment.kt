package br.com.fiap.marvelapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.com.fiap.marvelapp.databinding.FragmentCharacterDetailBinding
import br.com.fiap.marvelapp.domain.MarvelCharacterDataResultModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val character: MarvelCharacterDataResultModel? by lazy {
        arguments?.getParcelable(DETAIL_CHARACTER_KEY) as? MarvelCharacterDataResultModel
    }
    private val viewModel: ComicViewModel by viewModels()

    private val comicsAdapter by lazy {
        ComicListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.characterName.text = character?.name
        setObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewComics.setHasFixedSize(true)
        binding.recyclerViewComics.adapter = comicsAdapter
    }

    private fun setObservers() {
        viewModel.comicSuccessState.observe(
            viewLifecycleOwner,
            Observer {
                comicsAdapter.setData(it)
            }
        )
        viewModel.comicErrorState.observe(
            viewLifecycleOwner,
            Observer {
                Snackbar.make(
                    binding.recyclerViewComics,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        )

        lifecycleScope.launch {
            viewModel.listComics(character?.id ?: 1011334)
        }
    }

    companion object {
        private const val DETAIL_CHARACTER_KEY = "DETAIL_CHARACTER_KEY"

        fun buildBundle(characterDataResultModel: MarvelCharacterDataResultModel): Bundle {
            return bundleOf(DETAIL_CHARACTER_KEY to characterDataResultModel)
        }
    }
}