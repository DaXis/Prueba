package com.prueba.common.custom

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AlertDialog
import android.util.Log
import com.prueba.databinding.DialogLoadBinding

class LoadDialog : DialogFragment() {

    private val binding by lazy {
        DialogLoadBinding.inflate(layoutInflater)
    }

    companion object {
        fun newInstance(): LoadDialog {
            return LoadDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val currentApiVersion = Build.VERSION.SDK_INT
        if (currentApiVersion >= Build.VERSION_CODES.HONEYCOMB) {
            Log.e("solved super error", "solved super error OK")
        } else
            super.onSaveInstanceState(outState)
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        val style = STYLE_NORMAL
        val theme = android.R.style.Theme_Black_NoTitleBar_Fullscreen
        setStyle(style, theme)
    }

    override fun onCreateDialog(saveInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCancelable(false)
        dialog?.window?.setLayout(
            200,
            200
        )
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }
}