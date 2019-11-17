package com.sampleapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PollutionData(
    @Json(name = "region_metadata")
    var regioData: List<RegionData>? = null,
    @Json(name = "items")
    var items: List<Items>? = null
)

@JsonClass(generateAdapter = true)
class RegionData(
    @Json(name = "name")
    var name: String,
    @Json(name = "label_location")
    var labelLocation: LabelLocation
)

@JsonClass(generateAdapter = true)
class LabelLocation(
    @Json(name = "latitude")
    var latitude: Double?=null,
    @Json(name = "longitude")
    var longitude: Double?=null
)

@JsonClass(generateAdapter = true)
class Items(
    @Json(name = "readings")
    var reading: Reading
)

@JsonClass(generateAdapter = true)
class Reading(
    @Json(name = "psi_twenty_four_hourly")
    var psi_twenty_four_hourly: PsiTwentyFourHourly?=null,
    @Json(name = "psi_three_hourly")
    var psi_three_hourly: PsiThreeHourly?=null,
    @Json(name = "pm10_sub_index")
    var pm10_sub_index: Pm10SubIndex?=null,
    @Json(name = "pm25_sub_index")
    var pm25_sub_index: Pm25SubIndex?=null,
    @Json(name = "so2_sub_index")
    var so2_sub_index: So2SubIndex?=null,
    @Json(name = "o3_sub_index")
    var o3_sub_index: o3SubIndex?=null,
    @Json(name = "co_sub_index")
    var co_sub_index: coSubIndex?=null,
    @Json(name = "pm10_twenty_four_hourly")
    var pm10_twenty_four_hourly: Pm10TwentyFourHourly?=null,
    @Json(name = "pm25_twenty_four_hourly")
    var pm25_twenty_four_hourly: Pm25TwentyFourHourly?=null,
    @Json(name = "no2_one_hour_max")
    var no2_one_hour_max: No2OneHourMax?=null,
    @Json(name = "so2_twenty_four_hourly")
    var so2_twenty_four_hourly: So2TwentyFourHourly?=null,
    @Json(name = "co_eight_hour_max")
    var co_eight_hour_max: co8HourMax?=null,
    @Json(name = "o3_eight_hour_max")
    var o3_eight_hour_max: O38HourMax?=null
)

@JsonClass(generateAdapter = true)
class PsiTwentyFourHourly(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class PsiThreeHourly(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class Pm10SubIndex(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class Pm25SubIndex(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class So2SubIndex(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class o3SubIndex(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class coSubIndex(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class Pm10TwentyFourHourly(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class Pm25TwentyFourHourly(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class No2OneHourMax(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class So2TwentyFourHourly(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class co8HourMax(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)

@JsonClass(generateAdapter = true)
class O38HourMax(
    @Json(name = "national")
    var national: Double?=null,
    @Json(name = "north")
    var north: Double?=null,
    @Json(name = "south")
    var south: Double?=null,
    @Json(name = "east")
    var east: Double?=null,
    @Json(name = "west")
    var west: Double?=null,
    @Json(name = "central")
    var central: Double?=null
)