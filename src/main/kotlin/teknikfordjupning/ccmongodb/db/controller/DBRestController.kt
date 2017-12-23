package teknikfordjupning.ccmongodb.db.controller


import org.springframework.web.bind.annotation.*
import teknikfordjupning.ccmongodb.server.FlowController

import teknikfordjupning.ccmongodb.db.event.EventRepository
import teknikfordjupning.ccmongodb.db.event.Event


@RestController
@RequestMapping("/events")
class DBRestController(eventRepository: EventRepository) {
    val repository: EventRepository = eventRepository
    val flow: FlowController = FlowController(repository)
    @GetMapping("/all")
    fun getAll(): List<Event>{
    return this.repository.findAll()
    }

    @PostMapping
    fun update(@RequestBody event  : Event){
       flow.save(event)
        // this.repository.save(event)
    }
}