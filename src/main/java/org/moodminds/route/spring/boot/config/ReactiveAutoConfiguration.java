package org.moodminds.route.spring.boot.config;

import org.moodminds.route.reactive.Routes;
import org.moodminds.spring.ReactiveAdapterRegistration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.REACTIVE;

/**
 * The {@link Routes} autoconfiguration bean.
 */
@Configuration
@ConditionalOnWebApplication(type = REACTIVE)
@ConditionalOnClass(Routes.class)
@Import(ReactiveAdapterRegistration.class)
public class ReactiveAutoConfiguration {

    /**
     * Create the concurrent {@link Routes} bean.
     *
     * @return the concurrent {@link Routes} bean
     */
    @Bean
    @ConditionalOnMissingBean
    public Routes reactiveRoutes() {
        return new Routes();
    }
}
