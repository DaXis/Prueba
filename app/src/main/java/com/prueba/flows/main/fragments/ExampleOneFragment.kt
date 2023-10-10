package com.prueba.flows.main.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.prueba.R
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentExampleOneBinding
import com.prueba.flows.main.interfaces.NextStepListener
import top.defaults.colorpicker.ColorPickerPopup
import top.defaults.colorpicker.ColorPickerPopup.ColorPickerObserver

class ExampleOneFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private val binding by viewBinding {
        FragmentExampleOneBinding.inflate(layoutInflater)
    }

    private var listener: NextStepListener? = null

    private var url = ""

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
        initView()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initView() {
        binding.apply {
            buttonBackground.setOnClickListener {
                openColorPicker()
            }

            buttonPlaceholder.setOnClickListener {

            }

            buttonUrl.setOnClickListener {
                cardViewAvatar.loadFromUrl(url)
            }

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.options,
                R.layout.item_option
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUrls.adapter = adapter
                spinnerUrls.onItemSelectedListener = this@ExampleOneFragment
            }
        }
    }

    private fun openColorPicker() {
        ColorPickerPopup.Builder(requireContext())
            .initialColor(Color.RED)
            .enableBrightness(true)
            .enableAlpha(true)
            .okTitle("Seleccionar")
            .cancelTitle("Cancelar")
            .showIndicator(true)
            .showValue(true)
            .build()
            .show(binding.root, object : ColorPickerObserver() {
                override fun onColorPicked(color: Int) {
                    binding.cardViewAvatar.getContainer().setBackgroundColor(color)
                }
            })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        url = requireContext().resources.getStringArray(R.array.options)[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        binding.cardViewAvatar.default = "prueba default"
    }
}