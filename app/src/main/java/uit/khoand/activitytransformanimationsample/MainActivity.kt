package uit.khoand.activitytransformanimationsample

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.PixelCopy
import android.view.View
import uit.khoand.activitytransformanimationsample.util.ActivityTranformAnimationHelper
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openSub(view: View) {
        var intent = Intent(this, ActivitySub::class.java)
        ActivityTranformAnimationHelper().startActivity(view, this, intent)
    }


}
