package teknikfordjupning.ccmongodb.db.controller


import org.springframework.web.bind.annotation.*

import teknikfordjupning.ccmongodb.db.event.EventRepository
import teknikfordjupning.ccmongodb.db.event.Event


@RestController
@RequestMapping("/events")
class DBRestController(eventRepository: EventRepository) {
    val repository: EventRepository = eventRepository
    @GetMapping("/all")
    fun getAll(): List<Event>{
    return this.repository.findAll()
    }

    @PostMapping
    fun update(@RequestBody event  : Event){

         this.repository.save(event)
    }
}