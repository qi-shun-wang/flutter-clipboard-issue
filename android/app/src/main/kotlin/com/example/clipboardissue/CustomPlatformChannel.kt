package com.example.clipboardissue

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.systemchannels.PlatformChannel

class CustomPlatformChannel(dartExecutor: DartExecutor,  val activity: Activity) : PlatformChannel(dartExecutor) {
    override fun setPlatformMessageHandler(platformMessageHandler: PlatformMessageHandler?) {
        super.setPlatformMessageHandler(object : PlatformMessageHandler {

            override fun getClipboardData(format: ClipboardContentFormat?): CharSequence? {
                val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = clipboard.primaryClip
                if (clip == null || clip.itemCount < 1) {
                    return null
                }
                var output = ""
                val item = clip.getItemAt(0)
                if (item.text != null) {
                    output += item.text.toString()
                }

                if (item.htmlText != null) {
                    output += item.htmlText.toString()
                }

                return output
            }

            override fun setClipboardData(text: String) {
                val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    clipboard.clearPrimaryClip()
                }
                val clip = ClipData.newPlainText("text/*", text)
                clipboard.setPrimaryClip(clip)
            }

            override fun clipboardHasStrings(): Boolean {
                val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = clipboard.primaryClip
                if (clip == null || clip.itemCount < 1) {
                    return false
                }
                val mimeTypes = clip.description.filterMimeTypes("*/*").toList()
                return mimeTypes.isNotEmpty()
            }

            override fun playSystemSound(soundType:  SoundType) {
                platformMessageHandler?.playSystemSound(soundType)
            }

            override fun vibrateHapticFeedback(feedbackType:  HapticFeedbackType) {
                platformMessageHandler?.vibrateHapticFeedback(feedbackType)
            }

            override fun setPreferredOrientations(androidOrientation: Int) {
                platformMessageHandler?.setPreferredOrientations(androidOrientation)
            }

            override fun setApplicationSwitcherDescription(description:  AppSwitcherDescription) {
                platformMessageHandler?.setApplicationSwitcherDescription(description)
            }

            override fun showSystemOverlays(overlays: MutableList< SystemUiOverlay>) {
                platformMessageHandler?.showSystemOverlays(overlays)
            }

            override fun showSystemUiMode(mode:  SystemUiMode) {
                platformMessageHandler?.showSystemUiMode(mode)
            }

            override fun setSystemUiChangeListener() {
                platformMessageHandler?.setSystemUiChangeListener()
            }

            override fun restoreSystemUiOverlays() {
                platformMessageHandler?.restoreSystemUiOverlays()
            }

            override fun setSystemUiOverlayStyle(systemUiOverlayStyle:  SystemChromeStyle) {
                platformMessageHandler?.setSystemUiOverlayStyle(systemUiOverlayStyle)
            }

            override fun popSystemNavigator() {
                platformMessageHandler?.popSystemNavigator()
            }

        })
    }
}