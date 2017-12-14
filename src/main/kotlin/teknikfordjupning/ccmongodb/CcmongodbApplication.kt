package teknikfordjupning.ccmongodb

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CcmongodbApplication

fun main(args: Array<String>) {
    SpringApplication.run(CcmongodbApplication::class.java, *args)
}
