/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.common.tools.utils

import android.app.Activity
import android.content.Intent

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, Collection of utils for this application.
 */

/**
 * @desc drop helper utils under this governor.
 */
class MediumUtil {

    /**
     * generalized methods to fire intents @param activity is the calling activity
     * , type T is the class type of the caller
     * , this method also destroys the back stack.
     */
    fun <T : Activity> fireIntent(activity: Activity, type: Class<T>) {
        val mIntent = Intent(activity, type)
        activity.startActivity(mIntent)
        activity.finish()
    }
}