package com.prueba.flows.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentExampleFourDetailBinding
import com.prueba.flows.main.actions.ExampleFourActions
import com.prueba.flows.main.interfaces.NextStepListener
import com.prueba.flows.main.viewmodels.ExampleFourDetailViewModel
import javax.inject.Inject

class ExampleFourDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ExampleFourDetailViewModel

    private val binding by viewBinding {
        FragmentExampleFourDetailBinding.inflate(layoutInflater)
    }

    private var listener: NextStepListener? = null

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

    private fun eventListener(actions: ExampleFourActions) {

    }
}