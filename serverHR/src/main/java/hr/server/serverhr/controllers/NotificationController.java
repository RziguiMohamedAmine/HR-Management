package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Greeting;
import hr.server.serverhr.entities.HelloMessage;
import hr.server.serverhr.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Notification greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // Simulate delay
        return new Notification("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }



}
