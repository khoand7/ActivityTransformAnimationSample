package uit.khoand.activitytransformanimationsample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class ActivitySub : AppCompatActivity() {

    var startUpBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val bs: ByteArray = intent.getByteArrayExtra("trans_image")
        startUpBitmap = BitmapFactory.decodeByteArray(bs, 0, bs.size)
        val transImage: ImageView = findViewById(R.id.trans_image)
        transImage.setImageBitmap(startUpBitmap)
        transImage.visibility = View.VISIBLE
    }


}
