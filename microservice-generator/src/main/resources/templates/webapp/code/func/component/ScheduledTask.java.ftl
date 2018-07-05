package ${package}.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ScheduledTask {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定时任务，每晚23点启动
     */
    @Scheduled(cron = "0 0 23 * * *")
    @Transactional
    public void doSomeJobs(){
        logger.debug("do some jobs");
    }
}
