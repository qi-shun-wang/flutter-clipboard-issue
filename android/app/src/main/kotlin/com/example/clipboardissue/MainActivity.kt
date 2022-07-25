package com.example.clipboardissue

import android.app.Activity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.platform.PlatformPlugin

class MainActivity : FlutterActivity() {
    override fun providePlatformPlugin(
        activity: Activity?,
        flutterEngine: FlutterEngine
    ): PlatformPlugin {
        return PlatformPlugin(
            getActivity(),
            CustomPlatformChannel(flutterEngine.dartExecutor, getActivity())
        )
    }
}
