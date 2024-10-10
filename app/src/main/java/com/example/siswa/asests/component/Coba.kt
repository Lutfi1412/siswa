package com.example.siswa.asests.component

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import android.util.Base64
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
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
    var componentWidth by remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    // Create a custom ImageGetter to handle base64 images
    val imageGetter = remember {
        object : Html.ImageGetter {
            override fun getDrawable(source: String): Drawable? {
                return if (source.startsWith("data:image/")) {
                    val base64Image = source.substringAfter(",")
                    val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
                    val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

                    // Create a Drawable from the Bitmap
                    val drawable = BitmapDrawable(context.resources, bitmap)
                    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                    drawable
                } else {
                    null // Handle non-base64 images with default logic
                }
            }
        }
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
        modifier = modifier
            .onGloballyPositioned {
                componentWidth = it.size.width
            }
            .testTag("html-text"),
        update = { textView ->
            textView.text = HtmlCompat.fromHtml(
                text,
                htmlFlags,
                imageGetter,
                htmlTagHandler
            )
            textView.gravity = Gravity.START
            textView.setTextColor(Color.parseColor("#FF0000FF"))
            TextViewCompat.setTextAppearance(textView, style)
            textView.setLinkTextColor(Color.BLUE) // Use constant
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
      <p>jawablah soal berikut ini</p>
      <p><img src="data:image/png;base64,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"></p>
       """.trimIndent()

    HtmlText(
        html = exampleHtml,
        color = android.R.color.black,
        style = android.R.style.TextAppearance_Material_Body1
    )
}
