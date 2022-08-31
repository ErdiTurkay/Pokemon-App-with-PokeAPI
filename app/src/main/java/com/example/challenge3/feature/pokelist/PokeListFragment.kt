package com.example.challenge3.feature.pokelist

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.challenge3.*
import com.example.challenge3.activities.MainActivity
import com.example.challenge3.adapters.PokeAdapter
import com.example.challenge3.databinding.FragmentPokeListBinding
import com.example.challenge3.interfaces.PokeClickListener
import com.example.challenge3.models.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class PokeListFragment : Fragment(), PokeClickListener {

    private val viewModel: PokeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPokeListBinding.inflate(inflater, container, false)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPokemonFromAPI()
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.pokeLiveData.observe(viewLifecycleOwner) {
            if (viewModel.offset == 0) {
                binding.previousButton.visibility = View.INVISIBLE
            } else {
                binding.previousButton.visibility = View.VISIBLE
            }

            binding.pokeRV.adapter = ScaleInAnimationAdapter(PokeAdapter(requireContext(), it, this))
            binding.pokeCount.text = viewModel.pokemonCountText.value

            binding.shimmerLayout.visibility = View.GONE
            binding.nextButton.isClickable = true
            binding.previousButton.isClickable = true
        }

        viewModel.network.observe(viewLifecycleOwner) {
            if (viewModel.network.value == false) {
                binding.previousButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.INVISIBLE
                binding.pokeCount.setTextColor(Color.parseColor("#ff0000"))
                Toast.makeText(context, getString(R.string.check_your_internet), Toast.LENGTH_LONG).show()
                binding.pokeCount.text = getString(R.string.check_your_internet)
            } else {
                binding.nextButton.visibility = View.VISIBLE
                binding.pokeCount.setTextColor(Color.parseColor("#000000"))
            }
        }

        binding.nextButton.setOnClickListener {
            binding.shimmerLayout.toggleVisibility()
            binding.nextButton.isClickable = false

            viewModel.getNextPageFromAPI()
        }

        binding.previousButton.setOnClickListener {
            binding.shimmerLayout.toggleVisibility()
            binding.previousButton.isClickable = false

            viewModel.getPreviousPageFromAPI()
        }

        viewModel.getPokemonFromAPI()

        return binding.root
    }

    override fun onClickPoke(poke: Pokemon) {
        (activity as MainActivity).goToPokeDetailFragment(poke)
    }
}
