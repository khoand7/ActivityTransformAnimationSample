package uit.khoand.activitytransformanimationsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uit.khoand.activitytransformanimationsample.util.ActivityTranformAnimationHelper

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
