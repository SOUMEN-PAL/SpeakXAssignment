package com.example.speakxassignment

import android.app.Application
import com.example.speakxassignment.data.api.MockApi
import com.example.speakxassignment.domain.ItemRepository

class SpeakXAssignmentApplication : Application() {

    lateinit var repository: ItemRepository

    override fun onCreate() {
        initialize()
        super.onCreate()
    }

    private fun initialize(){
        val api : MockApi = MockApi()
        repository = ItemRepository(api)
    }

}