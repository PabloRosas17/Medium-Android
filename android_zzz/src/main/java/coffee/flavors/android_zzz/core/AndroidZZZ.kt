
package coffee.flavors.android_zzz.core

import android.content.Context
import coffee.flavors.android_zzz.core.di.DiShare
import coffee.flavors.common.core.CommonCore
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication

class AndroidZZZ : SplitCompatApplication() {
    val diShare = DiShare()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        println(CommonCore.COMMON_CORE_SHARED_TEST_RESOURCE)
    }
}
