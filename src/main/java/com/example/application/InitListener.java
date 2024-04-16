package com.example.application;

import com.vaadin.flow.server.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InitListener implements VaadinServiceInitListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addSessionInitListener(e-> {
            logger.info("Session init "+e.getSession().getSession().getId());
        });
        event.getSource().addSessionDestroyListener(e -> {
            logger.info("Session destroyed "+e.getSession().getSession().getId());
        });
        event.getSource().addUIInitListener(e -> {
            logger.info("UI inited "+e.getUI().getUIId());
            e.getUI().addHeartbeatListener(e1 -> {
                logger.info("HEARTBEAT for UI "+e1.getSource().getUIId());
            });
            e.getUI().addDetachListener(e2 -> {
                logger.info("UI detach for UI {} in session {}", e2.getUI().getUIId(), e2.getSession().getSession().getId());
            });
        });
        event.getSource().setSystemMessagesProvider(new SystemMessagesProvider() {
            @Override
            public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
                CustomizedSystemMessages messages = new CustomizedSystemMessages();
                messages.setSessionExpiredCaption("Session expired");
                messages.setSessionExpiredMessage("Take note of any unsaved data, and click here or press ESC key to continue.");
                messages.setSessionExpiredURL("https://vaadin.com");
                messages.setSessionExpiredNotificationEnabled(true);
                return messages;
            }
        });
    }
}
