package com.example.siswa.asests.component

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.ImageLoader
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.text.parseAsHtml
import androidx.core.widget.TextViewCompat
import coil.request.ImageRequest
import coil.size.Scale
import com.example.siswa.asests.component.ListTagHandler
import org.xml.sax.XMLReader

class ListTagHandler : Html.TagHandler {
    companion object {
        fun changeListTag(html: String): String {
            // Example logic to modify the HTML for list tags if necessary
            return html.replace("<ul>", "<ul style='list-style-type:disc;'>")
        }
    }

    override fun handleTag(
        opening: Boolean,
        tag: String?,
        output: Editable?,
        xmlReader: XMLReader?
    ) {
        // Implement logic to handle HTML list tags
    }
}



class CoilImageGetter(
    private val textView: TextView,
    private val imageLoader: ImageLoader = Coil.imageLoader(textView.context),
    private val sourceModifier: ((source: String) -> String)? = null,
    private val fixedImageWidth: Int,
) : Html.ImageGetter {

    override fun getDrawable(source: String): Drawable {
        val finalSource = sourceModifier?.invoke(source) ?: source
        val placeholder = ColorDrawable(Color.LTGRAY).toBitmap(100, 100)
        val drawablePlaceholder = DrawablePlaceHolder(textView.resources, placeholder)
        imageLoader.enqueue(
            ImageRequest.Builder(textView.context).data(finalSource).apply {
                target { drawable ->
                    drawablePlaceholder.updateDrawable(fixedImageWidth, drawable)
                    // invalidating the drawable doesn't seem to be enough...
                    textView.text = textView.text
                }
                scale(Scale.FIT)
            }.build()
        )
        // Since this loads async, we return a "blank" drawable, which we update later
        return drawablePlaceholder
    }

    private class DrawablePlaceHolder(
        resource: Resources,
        bitmap: Bitmap,
    ) : BitmapDrawable(resource, bitmap) {

        private var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        fun updateDrawable(width: Int, drawable: Drawable) {
            this.drawable = drawable
            val aspectRatio = drawable.intrinsicHeight.toFloat() / drawable.intrinsicWidth.toFloat()
            val height = (width.toFloat() * aspectRatio).toInt()
            drawable.setBounds(0, 0, width, height)
            setBounds(0, 0, width, height)
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
    htmlFlags: Int = FROM_HTML_MODE_LEGACY,
    htmlTagHandler: Html.TagHandler = ListTagHandler(),
    imageSourceModifier: ((source: String) -> String)? = null,
    @ColorRes color: Int,
    @StyleRes style: Int,
    typeface: Typeface? = null,
    onClicked: ((String) -> Unit)? = null
) {
    // This is used to determine the bounds of Drawable inside htmlText
    // which is handling by CoilImageGetter.
    var componentWidth by remember {
        mutableIntStateOf(0)
    }

    val text = remember(html) {
        if (htmlTagHandler is ListTagHandler) {
            ListTagHandler.changeListTag(html)
        } else {
            html
        }
    }

    AndroidView(
        factory = {
            AppCompatTextView(it)
        },
        modifier.onGloballyPositioned {
            componentWidth = it.size.width
        }.testTag("html-text"),
        update = { textView ->
            textView.text = text.parseAsHtml(
                htmlFlags,
                tagHandler = htmlTagHandler,
                imageGetter = CoilImageGetter(
                    textView,
                    sourceModifier = imageSourceModifier,
                    fixedImageWidth = componentWidth
                )
            )
            textView.gravity = Gravity.START
            textView.setTextColor(Color.parseColor("#FF0000FF"))
            TextViewCompat.setTextAppearance(textView, style)
            textView.setLinkTextColor(Color.BLUE)  // Menggunakan konstanta
            textView.typeface = typeface
            textView.handleUrlClicks(onClicked)
        },
    )
}

private fun AppCompatTextView.handleUrlClicks(onClicked: ((String) -> Unit)? = null) {
    linksClickable = true
    text = SpannableStringBuilder.valueOf(text).apply {
        getSpans(0, length, URLSpan::class.java).forEach {
            setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        onClicked?.invoke(it.url.trim())
                    }
                },
                getSpanStart(it),
                getSpanEnd(it),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            removeSpan(it)
        }
    }
    movementMethod = LinkMovementMethod.getInstance()
}

@Preview(showBackground = true)
@Composable
fun HtmlTextPreview() {
    val exampleHtml = """
        <p>This is a paragraph with an image (external src)</p>
        <img src="https://ijerrslgidofqvsfecgx.supabase.co/storage/v1/object/public/banners/banners/1728120650233_Desain%20tanpa%20judul.png" width="380" height="200">
        <p style="color: black; text-align: justify;">Some more text as an example</p>
    """.trimIndent()

    HtmlText(
        html = exampleHtml,
        color = android.R.color.black,
        style = android.R.style.TextAppearance_Material_Body1
    )
}
