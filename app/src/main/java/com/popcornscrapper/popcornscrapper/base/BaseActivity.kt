package com.popcornscrapper.popcornscrapper

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast

abstract class BaseActivity : Activity(), BaseView {
    private var progress: ProgressDialog? = null
    private var dialogCloseButton: ImageView? = null
    private var isCloseableOnBackButton = false

    override val activityContext: Context
        get() = this

    override fun onStart() {
        super.onStart()
        initExtras(providePresenter())
    }

    override fun startProgressDialog(message: String?) {
        if ((progress == null || progress?.isShowing!!) && !isFinishing) {
            progress = ProgressDialog.show(
                this, getString(R.string.progress_loading_text),
                message, true
            )
            Handler().postDelayed({
                if (progress != null && !isFinishing && progress?.isShowing!!) {
                    progress?.cancel()
                }
            }, 10000)
        }
    }

    override fun stopProgressDialog() {
        if (progress != null && !isFinishing && progress!!.isShowing) {
            progress?.dismiss()
        }
    }

    internal fun initExtras(presenter: BasePresenter?) {
        presenter?.initExtras(intent)
    }

    abstract fun providePresenter(): BasePresenter?

    override fun performOnBackPressed() {
        onBackPressed()
    }

    override fun onBackPressed() {
        if (!isCloseableOnBackButton && dialogCloseButton != null && dialogCloseButton!!.visibility == View.VISIBLE) {
            dialogCloseButton!!.performClick()
        } else {
            super.onBackPressed()
        }
    }

    override fun showToast(information: String) {
        ToastUtil.show(this, information, Toast.LENGTH_LONG)
    }
}