package com.prueba.common.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.prueba.databinding.DialogCustomBinding

class CustomDialog() : DialogFragment() {

    private val binding by lazy {
        DialogCustomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            buttonOk.setOnClickListener {
                dismiss()
            }

            buttonCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCancelable(true)
    }

    companion object {
        fun newInstance(): CustomDialog {
            return CustomDialog()
        }
    }
}