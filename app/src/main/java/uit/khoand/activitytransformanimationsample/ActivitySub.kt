package uit.khoand.activitytransformanimationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uit.khoand.activitytransformanimationsample.util.ActivityTranformAnimationHelper

class ActivitySub : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        ActivityTranformAnimationHelper().receiveTransitionEffect(this, intent)
    }

}
