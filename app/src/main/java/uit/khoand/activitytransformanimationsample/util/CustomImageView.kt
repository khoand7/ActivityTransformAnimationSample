package uit.khoand.activitytransformanimationsample.util

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Handler
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class CustomImageView : AppCompatImageView {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val activity: Activity = getContext() as Activity
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val maxR = width / 10f
        val minR = 0f
        val countVertical = width/maxR + 1
        val countHorizontal = height/maxR + 1
        for (i in 0..countHorizontal.toInt()) {
            for (j in 0..countVertical.toInt()) {
                bubbleList.add(
                    Bubble(
                        j * maxR - maxR,
                        i * maxR - maxR,
                        minR - i * 10f,
                        i
                    )
                )
            }
        }
        //var maxY = height
        mHandler = Handler()
        runnable = Runnable {
            for (bubble in bubbleList) {
                bubble.r = bubble.r + 2f
            }
            // stop the animation
            if (bubbleList[(bubbleList.size - 1)].r >= maxR) {
                this.visibility = View.GONE
            } else {
                this.postInvalidate()
                //this.postInvalidate(0, 51*20, maxY - 100, maxY + 20);
                mHandler.postDelayed(
                    runnable, // Runnable
                    20 // Delay in milliseconds
                )
            }
        }
        mHandler.postDelayed(
            runnable, // Runnable
            20 // Delay in milliseconds
        )

    }

    private val paint: Paint = Paint()
    private var bubbleList: ArrayList<Bubble> = ArrayList()
    private lateinit var runnable: Runnable
    private lateinit var mHandler: Handler

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (bubble in bubbleList) {
            if (bubble.r < 0f) {
                continue
            }
            canvas?.drawCircle(bubble.x, bubble.y, bubble.r, paint)
        }
    }
}