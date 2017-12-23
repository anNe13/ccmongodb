package teknikfordjupning.ccmongodb.server.db.schedule

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import teknikfordjupning.ccmongodb.server.FlowController
import teknikfordjupning.ccmongodb.server.db.event.EventRepository

@Component
class ScheduledTask(eventRepository: EventRepository ) {
    private val repository = eventRepository
    val flow: FlowController = FlowController(repository)



    @Scheduled(fixedDelay = 60_000)
    fun getLatest() {
     flow.fetchAndSave()
       // val latest = getLastEvents()
        //this.repository.save(latest)

    }

}