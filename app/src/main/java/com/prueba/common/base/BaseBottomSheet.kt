package com.prueba.common.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentManager
import com.prueba.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet : BottomSheetDialogFragment() {

    protected open val fullScreenBottomSheet: Boolean = false

    protected open val isDraggable: Boolean = true

    protected open val isVisibleBackground: Boolean = true

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { _dialog ->
            val bottomSheetDialog = _dialog as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

            if (fullScreenBottomSheet) {
                bottomSheet.updateLayoutParams {
                    height = WindowManager.LayoutParams.MATCH_PARENT
                }
            }

            if (isVisibleBackground.not()) {
                (view?.parent as? ViewGroup)?.background = ResourcesCompat.getDrawable(
                    resources,
                    R.color.general_bg,
                    null
                )
            }

            bottomSheetBehavior.apply {
                isDraggable = this@BaseBottomSheet.isDraggable
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }
}

fun BottomSheetDialogFragment.show(fragmentManager: FragmentManager) =
    show(fragmentManager, this::class.java.name)
