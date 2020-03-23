package me.shellbell.morse.controller

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.f2prateek.rx.preferences2.Preference

/**
 * Created by Shailesh351 on 23/6/19.
 */

class FlashController(private val context: Context, pref: Preference<Boolean>) : BaseController(pref) {

    companion object {
        private const val TAG: String = "FLASH_CONTROLLER"
    }

    private var camManager: CameraManager? = null
    private var cameraId: String? = null

    private var mCamera: Camera? = null
    private var parameters: Camera.Parameters? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            camManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            cameraId = camManager?.cameraIdList?.get(0)
        } else {
            mCamera = Camera.open()
            mCamera?.let {
                parameters = mCamera!!.parameters
                parameters!!.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                mCamera!!.parameters = parameters
            }
        }
    }

    override fun play(string: String) {
        if (!hasFlash()) {
            Toast.makeText(context, "FlashLight Not Available", Toast.LENGTH_SHORT).show()
            return
        }
        super.play(string)
    }

    override fun generateDot() {
        flashOn()
        Thread.sleep(morseTime.DOT_TIME_INTERVAL.toLong())
        flashOff()
    }

    override fun generateDash() {
        flashOn()
        Thread.sleep(morseTime.DASH_TIME_INTERVAL.toLong())
        flashOff()
    }

    private fun hasFlash(): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }

    private fun flashOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                if (camManager != null && cameraId != null) {
                    camManager!!.setTorchMode(cameraId!!, true)
                }
            } catch (e: CameraAccessException) {
                Log.e(TAG, e.toString())
            }
        } else {
            mCamera?.let {
                mCamera!!.startPreview()
            }
        }
    }

    private fun flashOff() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                if (camManager != null && cameraId != null) {
                    camManager!!.setTorchMode(cameraId!!, false)
                }
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        } else {
            mCamera?.let {
                mCamera!!.stopPreview()
            }
        }
    }

}