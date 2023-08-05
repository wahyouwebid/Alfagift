package com.ujangwahyu.app.features.detail.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ujangwahyu.app.R
import com.ujangwahyu.app.common.base.BaseFragment
import com.ujangwahyu.app.common.utils.parcelable
import com.ujangwahyu.app.databinding.FragmentDetailBinding
import com.ujangwahyu.app.features.detail.model.DataDetail

class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val dataNews: DataDetail? by lazy {
        arguments?.parcelable("data")
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView(savedInstanceState: Bundle?){
        with(binding) {
            toolbar.setToolbar(dataNews?.title) {
                navigation?.navigateUp()
            }
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.webViewClient = WebViewClient()
            dataNews?.url?.let { webView.loadUrl(it) }
        }
    }

    override fun setupViewModel() {}
}