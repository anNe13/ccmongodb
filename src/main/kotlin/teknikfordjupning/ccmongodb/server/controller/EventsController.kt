package teknikfordjupning.ccmongodb.server.controller

import org.springframework.stereotype.Controller
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import teknikfordjupning.ccmongodb.db.event.Event


@Controller
class EventsController {

    @MessageMapping("/getevent")
    @SendTo("/topic/latest")
    @Throws(Exception::class)
    fun greeting(): Unit {
        Thread.sleep(1000) // simulated delay
       // return Greeting("Hello, " + message.getName() + "!")
    }
}