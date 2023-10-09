package com.prueba.common.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prueba.common.custom.CustomDialog
import com.prueba.common.custom.LoadDialog

open class BaseActivity : AppCompatActivity() {

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
            synchronized(BaseActivity::class.java) {
                if (loadDialog == null) {
                    loadDialog = LoadDialog.newInstance()
                    loadDialog?.isCancelable = false
                    try {
                        loadDialog!!.show(supportFragmentManager, "load dialog")
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
        runOnUiThread { Toast.makeText(this, msn.orEmpty(), Toast.LENGTH_LONG).show() }
    }

}