package com.prueba.flows.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentExampleOneBinding
import com.prueba.flows.main.actions.ExampleOneActions
import com.prueba.flows.main.interfaces.NextStepListener
import com.prueba.flows.main.viewmodels.ExampleOneViewModel
import javax.inject.Inject

class ExampleOneFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ExampleOneViewModel

    private val binding by viewBinding {
        FragmentExampleOneBinding.inflate(layoutInflater)
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

    private fun eventListener(actions: ExampleOneActions) {

    }

    private companion object {

    }
}