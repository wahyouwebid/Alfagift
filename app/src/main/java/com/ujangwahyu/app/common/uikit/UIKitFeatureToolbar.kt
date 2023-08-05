package com.ujangwahyu.app.common.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ujangwahyu.app.databinding.UikitToolbarBinding

class UIKitFeatureToolbar(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitToolbarBinding = UikitToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setToolbar(res: String?, action: () -> Unit) = with(binding){
        tvToolbar.text = res
        ivBack.setOnClickListener {
            action.invoke()
        }
    }
}