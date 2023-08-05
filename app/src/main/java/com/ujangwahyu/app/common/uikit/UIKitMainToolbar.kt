package com.ujangwahyu.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ujangwahyu.app.databinding.UikitMainToolbarBinding

class UIKitMainToolbar(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitMainToolbarBinding = UikitMainToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setToolbar(res: String) {
        binding.tvToolbar.text = res
    }
}