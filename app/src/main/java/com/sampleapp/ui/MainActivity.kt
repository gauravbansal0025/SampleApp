package com.sampleapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sampleapp.R
import com.sampleapp.databinding.ActivityMainBinding
import com.sampleapp.model.PollutionData
import com.sampleapp.model.PollutionIndexData
import com.sampleapp.ui.adapter.CustomInfoWindowGoogleMap
import com.sampleapp.util.TimeUtil
import com.sampleapp.viewmodel.PollutionDataViewModel
import java.util.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onMapReady(p0: GoogleMap?) {
        gmap = p0
        gmap?.setMinZoomPreference(12F)
        gmap?.uiSettings?.isMapToolbarEnabled=true
        val ny = LatLng(1.2789, 103.8536)
        gmap?.moveCamera(CameraUpdateFactory.newLatLng(ny))
    }

    private var activityMainBinding: ActivityMainBinding? = null
    private lateinit var viewModel: PollutionDataViewModel
    private lateinit var pollutionData: PollutionData
    private var gmap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        activityMainBinding =
            DataBindingUtil.setContentView(this, com.sampleapp.R.layout.activity_main)

        activityMainBinding?.lifecycleOwner = this
        activityMainBinding?.mainActivity = this

        viewModel = ViewModelProviders.of(this).get(PollutionDataViewModel::class.java)

        //Map initialization
        val mapFragment: SupportMapFragment? =
            supportFragmentManager.findFragmentById(R.id.mapView4) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

    }



    fun clickSelectDate() {
        // Get Current Date
        val c = Calendar.getInstance()
        var mYear = c.get(Calendar.YEAR)
        var mMonth = c.get(Calendar.MONTH)
        var mDay = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                activityMainBinding?.edtSelectDate?.setText(
                    year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()
                )
            }, mYear, mMonth, mDay
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
    //Select Time Function display in Text view
    fun clickSelectTime() {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->

                activityMainBinding?.edtSelectTime?.setText(
                    "$selectedHour:$selectedMinute"
                )
            }, hour, minute, true
        )//Yes 24 hour time
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }
    //Button Click Get Pollution data
    fun getPollutionData() {
        if(activityMainBinding?.edtSelectDate?.text.toString().equals(""))
        {
            Toast.makeText(this,"Select Date",Toast.LENGTH_LONG).show()
        }
        else if(activityMainBinding?.edtSelectTime?.text.toString().equals(""))
        {
         Toast.makeText(this,"Select Time",Toast.LENGTH_LONG).show()
        }
        else
        {
            activityMainBinding?.progressBar?.visibility= View.VISIBLE
            var date_time =
                TimeUtil.convertDatetoRequiredformat(activityMainBinding?.edtSelectDate?.text.toString() + " " + activityMainBinding?.edtSelectTime?.text.toString())
            viewModel.getpollutionData(date_time, activityMainBinding?.edtSelectDate?.text.toString())
            viewModel.polluList.observe(this, Observer { data ->
                pollutionData = data
                activityMainBinding?.progressBar?.visibility= View.GONE
                gmap?.clear()

                if (pollutionData != null) {
                    for (item in pollutionData.regioData!!) {
                        // body of loop
                        val latLng =
                            LatLng(item.labelLocation.latitude!!, item.labelLocation.longitude!!)
                        if (item.name.equals("east")) {
                            val markerOptions = MarkerOptions()
                            markerOptions.position(latLng)
                                .title(item.name)

                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            var pollutionIndexData = PollutionIndexData()
                            pollutionIndexData.Ps24Index = pollutionData.items?.get(0)?.reading?.psi_twenty_four_hourly?.east
                            pollutionIndexData.Ps3Index = pollutionData.items?.get(0)?.reading?.psi_three_hourly?.east
                            pollutionIndexData.Pm10Index=pollutionData.items?.get(0)?.reading?.pm10_sub_index?.east
                            pollutionIndexData.Pm25Index=pollutionData.items?.get(0)?.reading?.pm25_sub_index?.east
                            pollutionIndexData.SO2SubIndex=pollutionData.items?.get(0)?.reading?.so2_sub_index?.east
                            pollutionIndexData.O3SubIndex=pollutionData.items?.get(0)?.reading?.o3_sub_index?.east
                            pollutionIndexData.COSubIndex=pollutionData.items?.get(0)?.reading?.co_sub_index?.east
                            pollutionIndexData.area="East"
                            var m = gmap?.addMarker(markerOptions)
                            m?.tag = pollutionIndexData
                            m?.showInfoWindow()

                        } else if (item.name.equals("west")) {
                            val markerOptions = MarkerOptions()
                            markerOptions.position(latLng)
                                .title(item.name)

                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            var pollutionIndexData = PollutionIndexData()
                            pollutionIndexData.Ps24Index = pollutionData.items?.get(0)?.reading?.psi_twenty_four_hourly?.west
                            pollutionIndexData.Ps3Index = pollutionData.items?.get(0)?.reading?.psi_three_hourly?.west
                            pollutionIndexData.Pm10Index=pollutionData.items?.get(0)?.reading?.pm10_sub_index?.west
                            pollutionIndexData.Pm25Index=pollutionData.items?.get(0)?.reading?.pm25_sub_index?.west
                            pollutionIndexData.SO2SubIndex=pollutionData.items?.get(0)?.reading?.so2_sub_index?.west
                            pollutionIndexData.O3SubIndex=pollutionData.items?.get(0)?.reading?.o3_sub_index?.west
                            pollutionIndexData.COSubIndex=pollutionData.items?.get(0)?.reading?.co_sub_index?.west
                            pollutionIndexData.area="West"
                            var m = gmap?.addMarker(markerOptions)
                            m?.tag = pollutionIndexData
                            m?.showInfoWindow()
                        } else if (item.name.equals("south")) {
                            val markerOptions = MarkerOptions()
                            markerOptions.position(latLng)
                                .title(item.name)

                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            var pollutionIndexData = PollutionIndexData()
                            pollutionIndexData.Ps24Index = pollutionData.items?.get(0)?.reading?.psi_twenty_four_hourly?.south
                            pollutionIndexData.Ps3Index = pollutionData.items?.get(0)?.reading?.psi_three_hourly?.south
                            pollutionIndexData.Pm10Index=pollutionData.items?.get(0)?.reading?.pm10_sub_index?.south
                            pollutionIndexData.Pm25Index=pollutionData.items?.get(0)?.reading?.pm25_sub_index?.south
                            pollutionIndexData.SO2SubIndex=pollutionData.items?.get(0)?.reading?.so2_sub_index?.south
                            pollutionIndexData.O3SubIndex=pollutionData.items?.get(0)?.reading?.o3_sub_index?.south
                            pollutionIndexData.COSubIndex=pollutionData.items?.get(0)?.reading?.co_sub_index?.south
                            pollutionIndexData.area="South"
                            var m = gmap?.addMarker(markerOptions)
                            m?.tag = pollutionIndexData
                            m?.showInfoWindow()
                        } else if (item.name.equals("north")) {
                            val markerOptions = MarkerOptions()
                            markerOptions.position(latLng)
                                .title(item.name)

                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            var pollutionIndexData = PollutionIndexData()
                            pollutionIndexData.Ps24Index = pollutionData.items?.get(0)?.reading?.psi_twenty_four_hourly?.north
                            pollutionIndexData.Ps3Index = pollutionData.items?.get(0)?.reading?.psi_three_hourly?.north
                            pollutionIndexData.Pm10Index=pollutionData.items?.get(0)?.reading?.pm10_sub_index?.north
                            pollutionIndexData.Pm25Index=pollutionData.items?.get(0)?.reading?.pm25_sub_index?.north
                            pollutionIndexData.SO2SubIndex=pollutionData.items?.get(0)?.reading?.so2_sub_index?.north
                            pollutionIndexData.O3SubIndex=pollutionData.items?.get(0)?.reading?.o3_sub_index?.north
                            pollutionIndexData.COSubIndex=pollutionData.items?.get(0)?.reading?.co_sub_index?.north
                            pollutionIndexData.area="North"
                            var m = gmap?.addMarker(markerOptions)
                            m?.tag = pollutionIndexData
                            m?.showInfoWindow()
                        } else if (item.name.equals("central")) {
                            val markerOptions = MarkerOptions()
                            markerOptions.position(latLng)
                                .title(item.name)

                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            var pollutionIndexData = PollutionIndexData()
                            pollutionIndexData.Ps24Index = pollutionData.items?.get(0)?.reading?.psi_twenty_four_hourly?.central
                            pollutionIndexData.Ps3Index = pollutionData.items?.get(0)?.reading?.psi_three_hourly?.central
                            pollutionIndexData.Pm10Index=pollutionData.items?.get(0)?.reading?.pm10_sub_index?.central
                            pollutionIndexData.Pm25Index=pollutionData.items?.get(0)?.reading?.pm25_sub_index?.central
                            pollutionIndexData.SO2SubIndex=pollutionData.items?.get(0)?.reading?.so2_sub_index?.central
                            pollutionIndexData.O3SubIndex=pollutionData.items?.get(0)?.reading?.o3_sub_index?.central
                            pollutionIndexData.COSubIndex=pollutionData.items?.get(0)?.reading?.co_sub_index?.central
                            pollutionIndexData.area="Central"
                            var m = gmap?.addMarker(markerOptions)
                            m?.tag = pollutionIndexData
                            m?.showInfoWindow()
                        }


                    }
                }
                //Setting custom window to show data
                var customInfoWindow = CustomInfoWindowGoogleMap(this)
                gmap?.setInfoWindowAdapter(customInfoWindow)

                //Redirecting Map to one location
                 gmap?.moveCamera(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            pollutionData.regioData?.get(0)?.labelLocation?.latitude!!,
                            pollutionData.regioData?.get(0)?.labelLocation?.longitude!!
                        )
                    )
                )
            })
        }

    }
}
