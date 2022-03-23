
package coffee.flavors.android_1.core

import android.app.Application
import coffee.flavors.android_zzz.core.di.DiShare
import coffee.flavors.common.core.CommonCore

class Android1 : Application() {
    val diShare = DiShare()
    override fun onCreate() {
        super.onCreate()
        println(CommonCore.COMMON_CORE_SHARED_TEST_RESOURCE)
    }
}