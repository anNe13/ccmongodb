package teknikfordjupning.ccmongodb.db

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import teknikfordjupning.ccmongodb.db.data_fetcher.getLastEvents
import teknikfordjupning.ccmongodb.db.event.EventRepository

@Component
class ScheduledTask(eventRepository: EventRepository) {
    private val repository = eventRepository

    @Scheduled(fixedDelay = 60_000)
    fun getLatest() {
        val latest = getLastEvents()
        this.repository.save(latest)

    }

}