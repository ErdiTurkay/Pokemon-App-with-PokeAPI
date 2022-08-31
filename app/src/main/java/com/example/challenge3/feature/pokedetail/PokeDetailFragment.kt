package com.example.challenge3.feature.pokedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.challenge3.activities.MainActivity
import com.example.challenge3.R
import com.example.challenge3.databinding.FragmentPokeDetailBinding
import com.example.challenge3.models.Pokemon
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokeDetailFragment(private var poke: Pokemon) : Fragment() {

    private val viewModel: PokeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPokeDetailBinding.inflate(inflater, container, false)

        viewModel.initialize(poke)

        binding.flexibleExampleCollapsing.title = poke.name.uppercase()
        binding.pokeDetailHeight.text = viewModel.height
        binding.pokeDetailWeight.text = viewModel.weight
        binding.pokeDetailBaseExperience.text = poke.pokeDetail.baseExperience.toString()

        Glide.with(requireContext())
            .load(poke.pokeDetail.sprites?.other?.home?.frontDefault)
            .placeholder(R.drawable.poke)
            .fitCenter()
            .into(binding.imageView)

        binding.flexibleExampleToolbar.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }

        binding.goToWiki.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.link))
            startActivity(browserIntent)
        }

        return binding.root
    }
}