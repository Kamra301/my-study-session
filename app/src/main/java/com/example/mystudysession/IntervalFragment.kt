package com.example.mystudysession

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.mystudysession.databinding.FragmentIntervalBinding


class IntervalFragment : Fragment() {

    lateinit var binding: FragmentIntervalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntervalBinding.inflate(inflater, container, false)

        binding.int1Btn.setOnClickListener {

            val interval_request = "25:00"
            setFragmentResult(INT1_KEY, bundleOf(TIME_INTERVAL to interval_request))

            findNavController().navigate(IntervalFragmentDirections.actionIntervalFragment3ToTimerFragment())

        }
        binding.int2Btn.setOnClickListener {

            val interval_request = "30:00"
            setFragmentResult(INT2_KEY, bundleOf(TIME_INTERVAL to interval_request))

            findNavController().navigate(IntervalFragmentDirections.actionIntervalFragment3ToTimerFragment())

        }
        binding.int3Btn.setOnClickListener {

            val interval_request = "45:00"
            setFragmentResult(INT3_KEY, bundleOf(TIME_INTERVAL to interval_request))

            findNavController().navigate(IntervalFragmentDirections.actionIntervalFragment3ToTimerFragment())

        }


        return binding.root
    }

    companion object {
        var TIME_INTERVAL = "interval"
        const val INT1_KEY = "25:00"
        const val INT2_KEY = "30:00"
        const val INT3_KEY = "45:00"

    }
}