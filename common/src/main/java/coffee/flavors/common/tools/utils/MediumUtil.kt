
package coffee.flavors.common.tools.utils

import android.app.Activity
import android.content.Intent

class MediumUtil {
    fun <T : Activity> fireIntent(activity: Activity, type: Class<T>) {
        val mIntent = Intent(activity, type)
        activity.startActivity(mIntent)
        activity.finish()
    }
}