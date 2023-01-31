package com.example.mystudysession

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.mystudysession.databinding.FragmentIntervalBinding
import com.example.mystudysession.databinding.FragmentTimerBinding


class TimerFragment : Fragment() {

    lateinit var binding: FragmentTimerBinding

    lateinit var timer: CountDownTimer
    private var startMilliSeconds = 0L
    private var millisecLeft = 0L
    private var isRunning: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimerBinding.inflate(inflater, container, false)

        binding.backBtn.setOnClickListener {
            findNavController().navigate(TimerFragmentDirections.actionTimerFragmentToIntervalFragment())
        }

        lateinit var time: String

        setFragmentResultListener(IntervalFragment.INT1_KEY){requestKey, bundle ->
            time = "25"
            millisecLeft = time.toLong() * 60000L
            binding.timerTxt.text = requestKey
        }
        setFragmentResultListener(IntervalFragment.INT2_KEY){requestKey, bundle ->
            time = "30"
            millisecLeft = time.toLong() * 60000L
            binding.timerTxt.text = requestKey
        }
        setFragmentResultListener(IntervalFragment.INT3_KEY){requestKey, bundle ->
            time = "45"
            millisecLeft = time.toLong() * 60000L
            binding.timerTxt.text = requestKey
        }

        binding.playBtn.setOnClickListener {
            if(isRunning){
                Toast.makeText(context, "You are already running a session. \nPlease click 'Pause' or 'Stop' instead", Toast.LENGTH_SHORT).show()

            }
            else{
                startTimer(millisecLeft)
            }
        }
        binding.pauseBtn.setOnClickListener {
            pauseTimer()
        }
        binding.stopBtn.setOnClickListener {
            stopTimer()
        }






        return binding.root
    }

    private fun pauseTimer(){
        timer.cancel()
        isRunning = false
    }

    private fun startTimer(time_milliseconds: Long) {

        timer = object : CountDownTimer(time_milliseconds, 1000) {

            override fun onFinish() {
                Toast.makeText(context, "Nice Studying!", Toast.LENGTH_LONG).show()
            }

            override fun onTick(p0: Long) {

                millisecLeft = p0

                val minutes = (p0/60000).toString()
                val seconds = (p0/1000 % 60).toString()
                val timeFormat = "${minutes.padStart(2,'0')}:${seconds.padStart(2, '0')}"
                binding.timerTxt.text = timeFormat
            }
        }
        timer.start()
        isRunning = true


    }

    private fun stopTimer() {

        if(isRunning){
            timer.cancel()
            isRunning = false

            val minutes = (startMilliSeconds/60000).toString()
            val seconds = (startMilliSeconds/1000 % 60).toString()
            val timeFormat = "${minutes.padStart(2,'0')}:${seconds.padStart(2, '0')}"
            binding.timerTxt.text = timeFormat

            Toast.makeText(context, "Session Stopped", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context, "The session is not running. Please press 'Play' first.", Toast.LENGTH_LONG)
        }

    }





}