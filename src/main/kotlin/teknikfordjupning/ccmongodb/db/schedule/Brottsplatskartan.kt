package teknikfordjupning.ccmongodb.server.db.schedule

import com.github.kittinunf.fuel.Fuel
import org.json.JSONObject
import teknikfordjupning.ccmongodb.server.db.event.Event

const val APP_PARAMETER = "app=studentproject_nackademin"
const val BASE_URL = "https://brottsplatskartan.se/api"


fun getIdOfLatest(): String {
    val (request, response, result) = Fuel.get("http://brottsplatskartan.se/api/events?limit=1&area=&location=&type=&page=1").responseString()

    return JSONObject(result.get())
            .getJSONArray("data")
            .map {(JSONObject(it.toString()))}[0]
            .getInt("id").toString()


}

fun getLastEvents(): List<Event> {
    val (request, response, result) = Fuel.get("http://brottsplatskartan.se/api/events?limit=20&area=&location=&type=&page=1").responseString()

    val theList = JSONObject(result.get()).getJSONArray("data")
            .map { parse(JSONObject(it.toString())) }

    return (theList)
}

fun parse(j: JSONObject): Event {

    return Event(
            j.getInt("id").toString(),
            j.getString("pubdate_iso8601"),
            j.getString("pubdate_unix").toInt(),
            j.getString("title_type"),
            j.getString("title_location"),
            j.getString("description"),
            j.getString("content_formatted"),
            j.getString("location_string"),
            j.getString("date_human"),
            j.getDouble("lat").toString(),
            j.getDouble("lng").toString(),
            j.getString("administrative_area_level_1"),
            j.getString("external_source_link")
    )


}