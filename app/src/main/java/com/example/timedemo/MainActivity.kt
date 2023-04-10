package com.example.timedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.timedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

private lateinit var countDownTimer: CountDownTimer
private var timerDuration: Long = 100000
private var pauseOffset: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTime.text = "${(timerDuration/1000).toString()}"
        binding.btnStart.setOnClickListener {
            startTime(pauseOffset)
        }
        binding.btnPause.setOnClickListener {
            pauseTime()
        }
        binding.btnRestart.setOnClickListener {
            resetTime()
        }
    }

    private fun startTime(pauseOffsetL: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                binding.tvTime.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {

            }
        }.start()

    }

    private fun pauseTime(){
        if (countDownTimer != null){
            countDownTimer!!.cancel()
        }
    }

    private fun resetTime(){
        if (countDownTimer != null){
            countDownTimer!!.cancel()
            binding.tvTime.text = "${(timerDuration/1000).toString()}"
            pauseOffset = 0
        }
    }


}