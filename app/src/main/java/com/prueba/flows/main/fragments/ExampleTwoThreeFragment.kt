package com.prueba.flows.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentExampleTwoThreeBinding
import com.prueba.db.PokemonObj
import com.prueba.flows.main.actions.ExampleTwoThreeActions
import com.prueba.flows.main.interfaces.NextStepListener
import com.prueba.flows.main.viewmodels.ExampleTwoThreeViewModel
import com.prueba.flows.main.views.PokedexListAdapter
import javax.inject.Inject

class ExampleTwoThreeFragment : BaseFragment(), PokedexListAdapter.ListItemClickListener {

    @Inject
    lateinit var viewModel: ExampleTwoThreeViewModel

    private val binding by viewBinding {
        FragmentExampleTwoThreeBinding.inflate(layoutInflater)
    }

    private var listener: NextStepListener? = null
    private lateinit var pokeAdapter: PokedexListAdapter
    private var isLoading = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireContext() as? NextStepListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingViewModel()
        initView()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun bindingViewModel() {
        viewModel.apply {
            getShowErrorMessage().observe(viewLifecycleOwner, Observer(::genToast))
            getShowProgress().observe(viewLifecycleOwner, Observer(::showLoadDialog))
            getAction().observe(viewLifecycleOwner, Observer(::eventListener))
        }
    }

    private fun eventListener(actions: ExampleTwoThreeActions) {
        when (actions) {
            is ExampleTwoThreeActions.GetAllPokemon -> {
                pokeAdapter.differ.submitList(actions.list)
                isLoading = false
            }

            else -> {}
        }
    }

    private fun initView() {
        pokeAdapter = PokedexListAdapter(this@ExampleTwoThreeFragment)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.apply {
            recyclerPokedex.apply {
                layoutManager = linearLayoutManager
                adapter = pokeAdapter

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!isLoading && linearLayoutManager.findLastCompletelyVisibleItemPosition() == pokeAdapter.itemCount - 1) {
                            viewModel.consumePokeList(pokeAdapter.itemCount.toString())
                            isLoading = true
                        }
                    }
                })

                pokeAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        linearLayoutManager.scrollToPositionWithOffset(positionStart, 0)
                    }
                })
            }
        }

        viewModel.consumePokeList(INITIAL_OFFSET)
    }

    override fun onItemClick(item: PokemonObj, position: Int) {
        listener?.onGoToPokeDetail(item.id)
    }

    private companion object {
        const val INITIAL_OFFSET = "0"
    }
}