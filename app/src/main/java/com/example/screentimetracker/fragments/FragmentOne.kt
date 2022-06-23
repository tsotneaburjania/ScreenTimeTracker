package com.example.screentimetracker.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.screentimetracker.MainActivity
import com.example.screentimetracker.R
import com.example.screentimetracker.data.ScreenTimeRecord
import java.util.concurrent.Executors

class FragmentOne : Fragment(R.layout.fragment_one) {
    private var records: List<ScreenTimeRecord> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_one, container, false)
//    }

    private fun init(view: View){
        var sumOfTime: Long = 0
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        val totalMinutesTv = view.findViewById<TextView>(R.id.totalMinutesTv)
        executor.execute {
            records = MainActivity.appDatabase?.recordDao()?.getAll() ?: ArrayList()
            for (record: ScreenTimeRecord in records) {
                sumOfTime += record.minutes?.toLong() ?: 0
            }
            if (totalMinutesTv != null) {
                totalMinutesTv.text = sumOfTime.toString()
            }
        }
    }
}