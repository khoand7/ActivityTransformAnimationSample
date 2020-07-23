package uit.khoand.activitytransformanimationsample.util

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.view.PixelCopy
import android.view.View
import java.io.ByteArrayOutputStream

open class ActivityTranformAnimationHelper {
    companion object {
        const val BITMAP_KEY = "trans_image"
    }

    // for api level 28
    fun startActivity(view: View, srcActivity: Activity, destActivity: Class<Any>) {
        getScreenShotFromView(view.findViewById(android.R.id.content), srcActivity) { bitmap ->
            run {
                val intent = Intent(srcActivity, destActivity).apply {
                    val bs = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs)
                    putExtra(ActivityTranformAnimationHelper.BITMAP_KEY, bs.toByteArray()) // put image
                }
                srcActivity.startActivity(intent)
                srcActivity.overridePendingTransition(0, 0)
            }
        }
    }

    fun startActivity(view: View, srcActivity: Activity, intent: Intent) {
        getScreenShotFromView(srcActivity.findViewById(android.R.id.content), srcActivity) { bitmap ->
            run {
                intent.apply {
                    val bs = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs)
                    putExtra(ActivityTranformAnimationHelper.BITMAP_KEY, bs.toByteArray()) // put image
                }
                srcActivity.startActivity(intent)
                srcActivity.overridePendingTransition(0, 0)
            }
        }
    }

    protected fun getScreenShotFromView(view: View, activity: Activity, callback: (Bitmap) -> Unit) {
        activity.window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    PixelCopy.request(
                        window,
                        Rect(
                            locationOfViewInWindow[0],
                            locationOfViewInWindow[1],
                            locationOfViewInWindow[0] + view.width,
                            locationOfViewInWindow[1] + view.height
                        ), bitmap, { copyResult ->
                            if (copyResult == PixelCopy.SUCCESS) {
                                callback(bitmap)
                            }
                            // possible to handle other result codes ...
                        },
                        Handler()
                    )
                }
            } catch (e: IllegalArgumentException) {
                // PixelCopy may throw IllegalArgumentException, make sure to handle it
                e.printStackTrace()
            }
        }
    }

    //deprecated version
    /*  Method which will return Bitmap after taking screenshot. We have to pass the view which we want to take screenshot.  */
    protected fun getScreenShot(view: View): Bitmap {
        val screenView = view.rootView
        screenView.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(screenView.drawingCache)
        screenView.isDrawingCacheEnabled = false
        return bitmap
    }
}