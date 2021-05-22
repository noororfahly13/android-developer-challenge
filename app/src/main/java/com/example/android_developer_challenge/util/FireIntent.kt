package com.example.android_developer_challenge.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment
import com.example.android_developer_challenge.ui.details.DetailsActivity
import com.example.android_developer_challenge.ui.details.DetailsActivity.Companion.LOGIN_ARGUMENT
import com.example.android_developer_challenge.ui.home.MainActivity

object FireIntent {

    fun openExternalBrowser(url: String, activity: Activity) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        activity.startActivity(openURL)
    }

    fun fireMainActivity(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.intent.extras?.let {
            intent.putExtras(it)
        }
        activity.startActivity(intent)
        activity.finishAffinity()
    }

    fun fireDetailsActivity(activity: Activity, login: String) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra(LOGIN_ARGUMENT, login)
        activity.startActivity(intent)
    }

    fun fireSystemSettingsForResult(fragment: Fragment, requestCode: Int) {
        val myAppSettings =
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + fragment.context?.packageName)
            )
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
        fragment.startActivityForResult(myAppSettings, requestCode)
    }
}