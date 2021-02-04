/*
 * Feel free to use this file in any way, shape, or form.
 */

package coffee.flavors.android_0.core

import android.app.Application
import coffee.flavors.android_zzz.core.di.DiShare
import coffee.flavors.common.core.CommonCore

/**
 * @author, evolandlupiz
 * @date, 5/5/2020
 * @property, Medium-Android
 * @purpose, application module.
 */

/**
 * @desc handles core actions and dependencies.
 */
class Android0 : Application() {

    /**
     * will manage the dependencies.
     */
    val diShare = DiShare()

    /**
     * entry point for the application.
     */
    override fun onCreate() {
        super.onCreate()
        println(CommonCore.COMMON_CORE_SHARED_TEST_RESOURCE) //acknowledge the common module.
    }
}