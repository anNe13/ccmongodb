package teknikfordjupning.ccmongodb.server.db.event

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable


@Document(collection = "Events")
data class Event
(
        @Id
        val id: String,
        val pubdate_iso8601: String,
        @Indexed(direction = IndexDirection.DESCENDING)
        val pubdate_unix: Int,
        val title_type: String,
        val title_location: String,
        val description: String,
        val content_formatted: String,
        val location_string: String,
        val date_human: String,
        val lat: String,
        val lng: String,
        val administrative_area_level_1: String,
        val external_source_link: String

): Serializable{

        constructor() : this("","",0,"","","","","","","","","","")
}