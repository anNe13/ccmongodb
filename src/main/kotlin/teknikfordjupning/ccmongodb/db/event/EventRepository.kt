package teknikfordjupning.ccmongodb.db.event

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : MongoRepository<Event,String>  //MAKE IT ReactiveMongoRepository