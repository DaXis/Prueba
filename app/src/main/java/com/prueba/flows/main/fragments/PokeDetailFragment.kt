package com.prueba.flows.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentPokeDetailBinding
import com.prueba.db.PokemonObj
import com.prueba.flows.main.actions.ExampleTwoThreeActions
import com.prueba.flows.main.viewmodels.PokeDetailViewModel
import com.prueba.utils.TypesConstants
import com.prueba.utils.UtilsExtensions.orZero
import javax.inject.Inject

class PokeDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: PokeDetailViewModel

    private val binding by viewBinding {
        FragmentPokeDetailBinding.inflate(layoutInflater)
    }

    private val args: PokeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingViewModel()
    }

    private fun bindingViewModel() {
        viewModel.apply {
            getShowErrorMessage().observe(viewLifecycleOwner, Observer(::genToast))
            getShowProgress().observe(viewLifecycleOwner, Observer(::showLoadDialog))
            getAction().observe(viewLifecycleOwner, Observer(::eventListener))
            consumePokeList(args.pokeId)
        }
    }

    private fun eventListener(actions: ExampleTwoThreeActions) {
        when(actions) {
            is ExampleTwoThreeActions.GetPokemon -> initView(actions.poke)
            else -> {}
        }
    }

    private fun initView(pokemon: PokemonObj) {
        binding.apply {
            cardViewAvatar.loadFromUrl(pokemon.sprite)
            textViewName.text = pokemon.name.toUpperCase()
            textViewId.text = "#${pokemon.id}"
            textViewHeight.text = pokemon.height
            textViewWeight.text = pokemon.weight
            imageViewTypeOne.setImageResource(TypesConstants().typesMap[pokemon.typeOne].orZero())
            TypesConstants().typesMap[pokemon.typeTwo]?.let {
                imageViewTypeTwo.setImageResource(it)
            }  ?: run {
                imageViewTypeTwo.visibility = View.GONE
            }
        }
    }
}