package com.example.screentimetracker

import android.content.Intent
import android.content.IntentFilter
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.screentimetracker.activities.NewsActivity
import com.example.screentimetracker.adapters.RecordListAdapter
import com.example.screentimetracker.broadcastReceivers.OnOffReceiver
import com.example.screentimetracker.data.AppDatabase
import com.example.screentimetracker.data.ScreenTimeRecord
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    private lateinit var onOffReceiver: OnOffReceiver
    private var records: List<ScreenTimeRecord> = ArrayList()

    companion object {
        var appDatabase: AppDatabase? = null
        var onCreateMillis: Long = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        onOffReceiver = OnOffReceiver()
        val lockFilter = IntentFilter()
        lockFilter.addAction(Intent.ACTION_SCREEN_ON)
        lockFilter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(onOffReceiver, lockFilter)

        init()
    }

    private fun createDb() {
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "records"
        ).build()
    }


    private fun init(){
        onCreateMillis = System.currentTimeMillis()
        createDb()
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        executor.execute {
            records = appDatabase?.recordDao()?.getAll() ?: ArrayList()
            recyclerView.adapter = RecordListAdapter(records)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        fab.setOnClickListener {
            val newsIntent = Intent(this@MainActivity, NewsActivity::class.java)
            this.startActivity(newsIntent);
        }

    }



//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onPause() {
//        super.onPause()
//        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
//        val screenOn: Boolean =
//            pm.isInteractive
//        if (!screenOn) {
//            val checkingIntent = Intent(this, MainActivity::class.java)
//            checkingIntent.putExtra("checking", true)
//            checkingIntent.flags =
//                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//
//            recreate()
//        }
//    }
}