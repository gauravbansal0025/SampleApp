package com.sampleapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker




import com.sampleapp.R
import com.sampleapp.databinding.InfowindowBinding
import com.sampleapp.model.PollutionIndexData


class CustomInfoWindowGoogleMap(var context: Context) : GoogleMap.InfoWindowAdapter{
    override fun getInfoContents(p0: Marker?): View {

        var infowindowBinding=
            DataBindingUtil.inflate<InfowindowBinding>(LayoutInflater.from(context), R.layout.infowindow,null,true)
        if (p0 != null) {
            infowindowBinding.dataIndex= p0.tag as PollutionIndexData?
        }
        infowindowBinding.executePendingBindings()
        return infowindowBinding.root

    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }

}