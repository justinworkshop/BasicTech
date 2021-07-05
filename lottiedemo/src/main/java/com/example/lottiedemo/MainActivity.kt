package com.example.lottiedemo

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var lottieViewEnterAdvance: LottieAnimationView
    private lateinit var lottieViewCountDown: LottieAnimationView
    private lateinit var lottieViewCountDownBg: LottieAnimationView

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        lottieViewEnterAdvance = findViewById(R.id.lottie_ready_enter_advance_state) as LottieAnimationView
        lottieViewCountDown = findViewById(R.id.lottie_countdown_5_seconds) as LottieAnimationView
        lottieViewCountDownBg = findViewById(R.id.lottie_countdown_bg) as LottieAnimationView

//        playBeforeEnterAdvanceLottie()
        playCountdownLottie()
    }

    private fun playBeforeEnterAdvanceLottie() {
        lottieViewEnterAdvance.imageAssetsFolder = LottieFileConstants.READY_ENTER_ADVANCE_STATE_IMAGE_FOLDER
        lottieViewEnterAdvance.setAnimation(LottieFileConstants.READY_ENTER_ADVANCE_STATE_ANIMATION_NAME)
        lottieViewEnterAdvance.repeatCount = 10
        playLottie(lottieViewEnterAdvance, 3000, 0.5f)
    }

    private fun playCountdownLottie() {
        playFrontLottie()
        playBgLottie()
    }

    private fun playFrontLottie() {
        lottieViewCountDown.imageAssetsFolder = LottieFileConstants.COUNTDOWN_5_SECONDS_IMAGE_FOLDER
        lottieViewCountDown.setAnimation(LottieFileConstants.COUNTDOWN_5_SECONDS_ANIMATION_NAME)
        lottieViewCountDown.repeatCount = 0
        playLottie(lottieViewCountDown, 5000, 0.5f)
    }

    private fun playBgLottie() {
        lottieViewCountDownBg.imageAssetsFolder = LottieFileConstants.COUNTDOWN_BG_IMAGE_FOLDER
        lottieViewCountDownBg.setAnimation(LottieFileConstants.COUNTDOWN_BG_ANIMATION_NAME)
        lottieViewCountDownBg.repeatCount = 10
        lottieViewCountDownBg.progress = 0f
        playLottie(lottieViewCountDownBg, 5000, 0.5f)
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    private fun playLottie(lottieAnimationView: LottieAnimationView, duration: Long, progress: Float) {
        lottieAnimationView.playAnimation()
        val animator = ObjectAnimator.ofFloat(0f, 1f).setDuration(duration)
        animator.interpolator = LinearInterpolator()
        var p: Float
        animator.addUpdateListener { animation ->
            run {
                p = animation.animatedValue as Float + progress
                if (p >= 1f) {
                    animator.cancel()
                    lottieAnimationView.visibility = View.GONE
                } else {
                    lottieAnimationView.progress = p
                }
            }
        }
        animator.start()
    }
}
