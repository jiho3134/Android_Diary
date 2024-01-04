package com.example.chapter03_diary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity(){

    private val handler = Handler(Looper.getMainLooper())

    private val diaryEditText : EditText by lazy {
        findViewById(R.id.diaryEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail", ""))


        val runnable = Runnable{
            getSharedPreferences("diary",Context.MODE_PRIVATE).edit{
                putString("detail",diaryEditText.text.toString())
            }
        }

        diaryEditText.addTextChangedListener {

            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }

        // EditText 가 바뀔때 마다 저장.
//        diaryEditText.addTextChangedListener {
//            detailPreferences.edit {
//                putString("detail",diaryEditText.text.toString())
//            }
//        }

    }
}