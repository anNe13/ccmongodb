package teknikfordjupning.ccmongodb.server.controller

import org.springframework.stereotype.Controller
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import teknikfordjupning.ccmongodb.db.event.Event
import teknikfordjupning.ccmongodb.db.event.EventRepository
import teknikfordjupning.ccmongodb.db.schedule.getAreas


@Controller
class ClientConnController(eventRepository: EventRepository) {

    val repository: EventRepository = eventRepository
    @Autowired
    private lateinit var template: SimpMessagingTemplate

    var localIdList: List<String> = emptyList()

    @MessageMapping("/init")
    @SendTo("/topic/all")
    @Throws(Exception::class)
    fun init(x: String): String {
        val events = repository.findAll()
        template.convertAndSend("/topic/new_event", events.reversed())
        template.convertAndSend("/topic/areas", getAreas())
println("INIIIIT")
        return "hej " + x
    }


    fun sendAll() {
        println("sends now")
        val events = repository.findAll()
        template.convertAndSend("/topic/new_event", events.reversed())
    }

    fun sendToSpecifiedArea(event: Event) {

        template.convertAndSend("/topic/new_event/${event.administrative_area_level_1}", arrayListOf(event))

    }

    @Scheduled(fixedDelay = 15_000)
    private fun checkNew() {
        sendAll()
        val dbIds = repository.findAll().map { it.id }

        if (!localIdList.containsAll(dbIds)) {
            repository.findAll()
                    .filter { it.id !in localIdList }
                    .forEach { sendToSpecifiedArea(it) }
        }

        localIdList = dbIds.toMutableList()

    }
}