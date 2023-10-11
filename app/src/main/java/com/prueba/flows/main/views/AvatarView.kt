package com.prueba.flows.main.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.prueba.R
import com.prueba.utils.UtilsExtensions.isNotNullOrEmpty

class AvatarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    var default = "default"
    private var attrs: AttributeSet? = null
    private lateinit var container: FrameLayout
    private lateinit var avatarImage: ImageView
    private lateinit var avatarText: TextView

    init {
        this@AvatarView.attrs = attrs
        cardViewParams()
        initLayoutContainer()
        initAvatarImageView()
        initAvatarTextView()
    }

    private fun cardViewParams() {
        if(attrs == null) {
            layoutParams = ViewGroup.LayoutParams(
                convertPixelsToDp(200f).toInt(),
                convertPixelsToDp(200f).toInt()
            )
            radius = convertPixelsToDp(900f)
        }
    }

    private fun initLayoutContainer() {
        container = FrameLayout(context)
        container.apply {
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            params.gravity = Gravity.CENTER
            layoutParams = params
        }
        addView(container)
    }

    fun getContainer() = container

    private fun initAvatarImageView() {
        avatarImage = ImageView(context)
        avatarImage.apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        }
        container.addView(avatarImage)
    }

    private fun initAvatarTextView() {
        avatarText = TextView(context)
        avatarText.apply {
            val params = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            layoutParams = params
            textSize = 120f
        }
        container.addView(avatarText)
    }

    fun setBackgroundColor(color: String) {
        container.setBackgroundColor(Color.parseColor(color))
    }

    fun loadFromUrl(url: String) {
        if(url.isNotNullOrEmpty() && validateUrl(url)){
            avatarImage.visibility = VISIBLE
            avatarText.visibility = GONE
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.angle)
                .into(avatarImage)
        } else {
            avatarImage.visibility = GONE
            avatarText.visibility = VISIBLE
            setAvatarText(url)
        }
    }

    private fun setAvatarText(text: String) {
        var avatar = if (text.isNotNullOrEmpty() && text.length > 2) {
            val aux = text.replace(" ", "|")
            val split = aux.split("|")
            if(split.size >= 2) {
                "${split[0][0]}${split[1][0]}"
            } else {
                "${split[0][0]}"
            }
        } else {
            text
        }
        avatarText.text = avatar.toUpperCase()
    }

    private fun convertPixelsToDp(px: Float): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    private fun validateUrl(url: String): Boolean {
        val urlRegex = Regex(URL_REGEX)
        return urlRegex.containsMatchIn(url)
    }

    private companion object {
        private const val URL_REGEX = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)"
    }

}