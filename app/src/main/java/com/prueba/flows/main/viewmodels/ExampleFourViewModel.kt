package com.prueba.flows.main.viewmodels

import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.main.actions.ExampleFourActions
import com.prueba.flows.main.views.models.LocationObj
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

class ExampleFourViewModel @Inject constructor() : BaseViewModel() {

    private val action = BaseSingleLiveEvent<ExampleFourActions>()
    fun getAction(): LiveData<ExampleFourActions> = action

    private val timer = Timer()

    fun checkPermissions(
        context: Context,
        permission: String,
        permissionCode: Int
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initRepeatAction()
        } else {
            action.value = ExampleFourActions.RequestPermissions(
                listOf(permission),
                permissionCode
            )
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initRepeatAction()
        }
    }

    private fun initRepeatAction() {
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    action.postValue(ExampleFourActions.InitLocation)
                }
            }, 1000, REPEAT_TIME
        )
    }

    fun stopRepeatAction() {
        timer.cancel()
    }

    fun sendData(db: FirebaseFirestore, location: Location) {
        val currentTime = System.currentTimeMillis()
        val locationData = hashMapOf(
            "dateDay" to getDateFormat(currentTime, DAY_FORMAT),
            "dateHour" to getDateFormat(currentTime, HOUR_FORMAT),
            "latitude" to "${location.latitude}",
            "longitude" to "${location.longitude}",
        )
        db.collection("myLocations")
            .add(locationData)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getData(db: FirebaseFirestore) {
        db.collection("myLocations")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    val list = ArrayList<LocationObj>()
                    for (document in it.result){
                        list.add(
                            LocationObj(
                                document.data.getValue("dateDay").toString(),
                                document.data.getValue("dateHour").toString(),
                                document.data.getValue("latitude").toString(),
                                document.data.getValue("longitude").toString()
                            )
                        )
                    }
                    action.value = ExampleFourActions.GetData(list)
                }
            }
    }

    private fun getDateFormat(milliSeconds: Long, dateFormat: String?): String? {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    private companion object {
        const val DAY_FORMAT = "dd/MM/yyyy"
        const val HOUR_FORMAT = "hh:mm:ss"
        const val REPEAT_TIME = 120000L
        const val TAG = "Firebase"
    }
}