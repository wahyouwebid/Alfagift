package com.ujangwahyu.app.core.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.app.R
import com.ujangwahyu.app.common.utils.ErrorMapper
import com.ujangwahyu.app.databinding.AdapterNewsFooterBinding
import retrofit2.HttpException
import java.io.IOException

class FooterAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<FooterAdapter.ViewHolder>() {

    class ViewHolder(val binding: AdapterNewsFooterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        with(holder.binding) {

            if (loadState is LoadState.Error) {
                when(val error = loadState.error) {
                    is HttpException -> {
                        val result = ErrorMapper.errorMapper(error)
                        uikitError.setError( null, result?.message ?: root.context.getString(R.string.title_error_des)) {
                            retry.invoke()
                        }
                    }

                    is IOException -> {
                        uikitError.setError(
                            root.context.getString(R.string.title_error_no_internet),
                            root.context.getString(R.string.title_error_no_internet_des)
                        ) {
                            retry.invoke()
                        }
                    }
                }
            }

            progressBar.isVisible = loadState is LoadState.Loading
            uikitError.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = AdapterNewsFooterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }
}