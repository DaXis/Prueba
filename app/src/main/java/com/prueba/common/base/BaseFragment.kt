package com.prueba.common.base

import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.prueba.common.custom.LoadDialog
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    private var loadDialog: LoadDialog? = null

    fun showLoadDialog(show: Boolean) {
        if (show) {
            showLoadDialog()
        } else {
            dismissLoadDialog()
        }
    }

    private fun showLoadDialog(): LoadDialog? {
        if (loadDialog == null) {
            synchronized(Application::class.java) {
                if (loadDialog == null) {
                    loadDialog = LoadDialog.newInstance()
                    loadDialog!!.isCancelable = false
                    try {
                        loadDialog!!.show(childFragmentManager, "load dialog")
                    } catch (e: NullPointerException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return loadDialog
    }

    private fun dismissLoadDialog() {
        if (loadDialog != null) {
            try {
                loadDialog!!.dismiss()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }

            loadDialog = null
        }
    }

    /**
     * Metodo para generar mensaje toast
     * @param msn: String texto del mensaje
     */
    fun genToast(msn: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(
                requireActivity(),
                msn.orEmpty(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Metodo para ocultar el teclado
     * @param view: View que tiene el foco en la vista
     */
    fun hideKeyboard(view: View) {
        val manager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}