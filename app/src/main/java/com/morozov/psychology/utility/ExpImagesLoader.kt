package com.morozov.psychology.utility

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.morozov.psychology.R
import java.io.IOException

object ExpImagesLoader {

    private const val EXP_IMG = "experiments_images/"

    fun getImageDescription(context: Context, exp: Int): Drawable {
        return try {
            val stream = context.resources.assets.open("${EXP_IMG}image_${exp}_1.png")
            Drawable.createFromStream(stream, "${EXP_IMG}image_${exp}_1.png")
        } catch (e: IOException) {
            ColorDrawable(context.resources.getColor(R.color.fragment_background))
        }
    }

    fun getImageTest(context: Context, exp: Int): Drawable {
        return try {
            val stream = context.resources.assets.open("${EXP_IMG}image_${exp}_2.png")
            Drawable.createFromStream(stream, "${EXP_IMG}image_${exp}_2.png")
        } catch (e: IOException) {
            ColorDrawable(context.resources.getColor(R.color.fragment_background))
        }
    }

    fun getImageResult(context: Context, exp: Int): Drawable {
        return try {
            val stream = context.resources.assets.open("${EXP_IMG}image_${exp}_3.png")
            Drawable.createFromStream(stream, "${EXP_IMG}image_${exp}_3.png")
        } catch (e: IOException) {
            ColorDrawable(context.resources.getColor(R.color.fragment_background))
        }
    }
}