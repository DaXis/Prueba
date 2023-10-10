package com.prueba.flows.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentMainBinding
import com.prueba.flows.main.interfaces.NextStepListener

class MainFragment : BaseFragment() {

    private val binding by viewBinding {
        FragmentMainBinding.inflate(layoutInflater)
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
        binding.apply {
            buttonExampleOne.setOnClickListener {
                listener?.onGoToExampleOne()
            }

            buttonExampleTwo.setOnClickListener {
                listener?.onGoToExampleTwoThree()
            }

            buttonExampleFour.setOnClickListener {
                listener?.onGoToExampleFour()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}