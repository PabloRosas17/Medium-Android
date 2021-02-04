/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_zzz.core

import android.content.Context
import coffee.flavors.android_zzz.core.di.DiShare
import coffee.flavors.common.core.CommonCore
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, application module.
 */

/**
 * @desc handles core actions and dependencies.
 */
class AndroidZZZ : SplitCompatApplication() {

    /**
     * will manage the dependencies.
     */
    val diShare = DiShare()

    /**
     *  need to attach the context of the base context.
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    /**
     * entry point for the application.
     */
    override fun onCreate() {
        super.onCreate()
        println(CommonCore.COMMON_CORE_SHARED_TEST_RESOURCE) //acknowledge the common module.
    }
}
