package cf.zpdima.kafkadb.componet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
//@Component
public class ViewBean {
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    void init(){
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            log.info(" --- bean : '{}'", beanName);
        }
    }

}
