package teknikfordjupning.ccmongodb.server.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : AbstractWebSocketMessageBrokerConfigurer() {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        //config.enableStompBrokerRelay("/topic").setRelayHost("192.168.0.101")
        config.enableSimpleBroker("/topic") //BRINGS TO CLIENT
        config.setApplicationDestinationPrefixes("/app")// RECIVE FROM CLIENT WHEN /app/lastevent
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*")//.withSockJS()
    }

}