package teknikfordjupning.ccmongodb.db.schedule

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import teknikfordjupning.ccmongodb.db.event.EventRepository

@Component
class ScheduledTask(eventRepository: EventRepository ) {
    private val repository = eventRepository

    @Scheduled(fixedDelay = 60_000)
    fun populateWithLatest() {
     
        val latest = getLastEvents()
        this.repository.save(latest)

    }

}