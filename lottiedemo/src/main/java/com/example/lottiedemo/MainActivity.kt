package com.example.lottiedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    private lateinit var lottieViewEnterAdvance: LottieAnimationView
    private lateinit var lottieViewCountDown: LottieAnimationView
    private lateinit var lottieViewCountDownBg: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottieViewEnterAdvance = findViewById(R.id.lottie_ready_enter_advance_state) as LottieAnimationView
        lottieViewCountDown = findViewById(R.id.lottie_countdown_5_seconds) as LottieAnimationView
        lottieViewCountDownBg = findViewById(R.id.lottie_countdown_bg) as LottieAnimationView

        playBeforeEnterAdvanceLottie()
        playCountdownLottie()
    }

    private fun playBeforeEnterAdvanceLottie() {
        lottieViewEnterAdvance.imageAssetsFolder = LottieFileConstants.READY_ENTER_ADVANCE_STATE_IMAGE_FOLDER
        lottieViewEnterAdvance.setAnimation(LottieFileConstants.READY_ENTER_ADVANCE_STATE_ANIMATION_NAME)
        lottieViewEnterAdvance.repeatCount = 10
        lottieViewEnterAdvance.progress = 0f
        lottieViewEnterAdvance.playAnimation()
    }

    private fun playCountdownLottie() {
//        playFrontLottie()
        playBgLottie()
    }

    private fun playFrontLottie() {
        lottieViewCountDown.imageAssetsFolder = LottieFileConstants.COUNTDOWN_5_SECONDS_IMAGE_FOLDER
        lottieViewCountDown.setAnimation(LottieFileConstants.COUNTDOWN_5_SECONDS_ANIMATION_NAME)
        lottieViewCountDown.repeatCount = 10
        lottieViewCountDown.progress = /*lottieData.progress*/ 0.5f
        lottieViewCountDown.playAnimation()
//        val animator = ValueAnimator.ofFloat(0f, 1f)
//        animator.addUpdateListener {
//            lottieViewCountDown.progress = animator.animatedValue as Float
//        }
//        animator.setCurrentFraction(0.5f)
//        animator.start()
    }

    private fun playBgLottie() {
        lottieViewCountDownBg.imageAssetsFolder = LottieFileConstants.COUNTDOWN_BG_IMAGE_FOLDER
        lottieViewCountDownBg.setAnimation(LottieFileConstants.COUNTDOWN_BG_ANIMATION_NAME)
        lottieViewCountDownBg.repeatCount = 10
        lottieViewCountDownBg.progress = 0f
        lottieViewCountDownBg.playAnimation()
    }
}
