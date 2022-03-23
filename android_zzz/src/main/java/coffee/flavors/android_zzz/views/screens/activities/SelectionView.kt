
package coffee.flavors.android_zzz.views.screens.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coffee.flavors.android_zzz.R
import coffee.flavors.android_zzz.controllers.viewmodel.SelectionViewModel
import coffee.flavors.android_zzz.core.AndroidZZZ
import coffee.flavors.android_zzz.core.factory.SelectionViewModelFactory
import coffee.flavors.android_zzz.databinding.LayoutSelectionViewBinding
import coffee.flavors.android_zzz.views.presenters.activities.PresenterSelection
import coffee.flavors.common.tools.utils.BinderIf
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class SelectionView : AppCompatActivity() ,
    BinderIf<LayoutSelectionViewBinding> {

    private val classAndroid0 by lazy { getString(R.string.class_android0) }
    private val classAndroid1 by lazy { getString(R.string.class_android1) }
    private val moduleAndroid0 by lazy { getString(R.string.module_android0) }
    private val moduleAndroid1 by lazy { getString(R.string.module_android1) }

    private lateinit var manager: SplitInstallManager
    private lateinit var progress: Group
    private lateinit var buttons: Group
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    private val listener = SplitInstallStateUpdatedListener { state ->
        val names = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                displayLoadingState(state, "Downloading $names")
                statusToast("DOWNLOADING",names,state)
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                statusToast("REQUIRES_USER_CONFIRMATION",names,state)
                startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
            }
            SplitInstallSessionStatus.INSTALLED -> {
                statusToast("INSTALLED",names,state)
                onSuccessfulLoad(names)
            }
            SplitInstallSessionStatus.INSTALLING -> {
                displayLoadingState(state, "Installing $names")
                statusToast("INSTALLING",names,state)
            }
            SplitInstallSessionStatus.FAILED -> {
                Toast.makeText(this,"SplitInstallSessionStatus.FAILED... names:$names and state:$${state.errorCode()} for module ${state.moduleNames()}", Toast.LENGTH_LONG).show()
            }
            SplitInstallSessionStatus.CANCELED -> {
                statusToast("CANCELED",names,state)
            }
            SplitInstallSessionStatus.CANCELING -> {
                statusToast("CANCELING",names,state)
            }
            SplitInstallSessionStatus.DOWNLOADED -> {
                statusToast("DOWNLOADED",names,state)
            }
            SplitInstallSessionStatus.PENDING -> {
                statusToast("PENDING",names,state)
            }
            SplitInstallSessionStatus.UNKNOWN -> {
                statusToast("UNKNOWN",names,state)
            }
        }
    }
    private val factory: ViewModelProvider.Factory = SelectionViewModelFactory()
    private val mViewModel by viewModels<SelectionViewModel> { factory }
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        val diShare = (this.application as AndroidZZZ).diShare
        this.fireUiBindings()
        manager = SplitInstallManagerFactory.create(this)
        buttons = findViewById(R.id.buttons)
        progress = findViewById(R.id.progress)
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_text)
    }
    override lateinit var mBinding: LayoutSelectionViewBinding
    override fun fireUiBindings() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.layout_selection_view)
        mBinding.mPresenter = PresenterSelection(this)
        mBinding.executePendingBindings()
    }
    override fun onResume() {
        manager.registerListener(listener)
        super.onResume()
    }
    override fun onPause() {
        manager.unregisterListener(listener)
        super.onPause()
    }
    fun fireLoadAndLaunchModule(name: String){
        updateProgressMessage("fireLoadAndLaunchModule, Loading module $name")
        if (manager.installedModules.contains(name)) {
            updateProgressMessage("fireLoadAndLaunchModule, Already installed")
            onSuccessfulLoad(name)
            return
        }
        val request = SplitInstallRequest.newBuilder().addModule(name).build()
        updateProgressMessage("fireLoadAndLaunchModule, Starting install for $name")
        manager.startInstall(request)
            .addOnSuccessListener {
                updateProgressMessage("fireLoadAndLaunchModule, Success $it status on install for $name")
            }
            .addOnFailureListener {
                updateProgressMessage("fireLoadAndLaunchModule, Error $it status on install for $name")
            }
    }
    private fun onSuccessfulLoad(moduleName: String) {
        when (moduleName) {
            this.moduleAndroid0 -> launchActivity(classAndroid0)
            this.moduleAndroid1 -> launchActivity(classAndroid1)
        }
        displayButtons()
    }
    private fun launchActivity(className: String) {
        when (className) {
            this.classAndroid0 -> {
                Intent().setClassName(packageName, classAndroid0).also { startActivity(it) }
            }
            this.classAndroid1 -> {
                Intent().setClassName(packageName, classAndroid1).also { startActivity(it) }
            }
        }
    }
    private fun statusToast(status: String, names: String, state: SplitInstallSessionState){
        Toast.makeText(this,"SplitInstallSessionStatus.$status... names:$names and state:$state", Toast.LENGTH_LONG).show()
    }
    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()
        progressBar.max = state.totalBytesToDownload().toInt()
        progressBar.progress = state.bytesDownloaded().toInt()
        updateProgressMessage(message)
    }
    private fun updateProgressMessage(message: String) {
        if (progress.visibility != View.VISIBLE) displayProgress()
        progressText.text = message
    }
    private fun displayProgress() {
        progress.visibility = View.VISIBLE
        buttons.visibility = View.GONE
    }
    private fun displayButtons() {
        progress.visibility = View.GONE
        buttons.visibility = View.VISIBLE
    }
}