package com.ujangwahyu.app.common.utils

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ujangwahyu.app.R
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun ImageView.getDrawableIdFromFileName(drawable: String?): Int {
    return context.resources.getIdentifier(drawable, "drawable", this.context.packageName)
}

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.bg_placeholder)
        .error(R.drawable.ic_no_image)
        .into(this)
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String?.dateTimeHourAgo(): String? {
    val prettyTime = PrettyTime(Locale.US)
    var isTime: String? = null
    try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val date = simpleDateFormat.parse(this!!)
        isTime = prettyTime.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return isTime
}

fun String?.dateFormat(): String? {
    val isDate: String?
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy - HH:mm:ss", Locale(getCountry()))
    isDate = try {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this!!)
        dateFormat.format(date!!)
    } catch (e: ParseException) {
        e.printStackTrace()
        this
    }
    return isDate
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

private fun getCountry(): String {
    val locale = Locale.getDefault()
    val strCountry = locale.country
    return strCountry.toLowerCase(Locale.ROOT)
}