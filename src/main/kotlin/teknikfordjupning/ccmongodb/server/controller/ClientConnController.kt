package teknikfordjupning.ccmongodb.server.controller

import org.springframework.stereotype.Controller
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import teknikfordjupning.ccmongodb.server.db.event.Event
import teknikfordjupning.ccmongodb.server.db.schedule.getLastEvents


@Controller
class EventsController() {

    @Autowired
    private lateinit var template: SimpMessagingTemplate

    @MessageMapping("/getevent")
    @SendTo("/topic/latest")
    @Throws(Exception::class)
    fun greeting(x: String): String {
        Thread.sleep(1000) // simulated delay
        template.convertAndSend("/topic/test", "TTTTTTTEST")
         return "hej " + x
    }

    //_________________________________________________TEST
   /* @SubscribeMapping("/geteventt")
    @SendTo("/topic/test")
    @Throws(Exception::class)*/
    fun test(event: Event): Unit {
        Thread.sleep(1000) // simulated delay
       // println(event.id)
        template.convertAndSend("/topic/test", event.description)
      //  return event.title_location
        //return event
    }
    //_________________________________________________TEST


    @Scheduled(fixedDelay = 10_000)
    fun getLatest() {
        val events = getLastEvents()
        template.convertAndSend("/topic/test", events[0].date_human)
        // val latest = getLastEvents()
        //this.repository.save(latest)

    }
}