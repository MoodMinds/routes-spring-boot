package org.moodminds.route.spring.boot.config;

import org.moodminds.route.traverse.Routes;
import org.moodminds.spring.web.TraverseSupportMappingHandlerAdaptation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

/**
 * The {@link Routes} autoconfiguration bean.
 */
@Configuration
@ConditionalOnWebApplication(type = SERVLET)
@ConditionalOnClass(Routes.class)
@Import({TraverseTransactionConfiguration.class, TraverseSupportMappingHandlerAdaptation.class})
public class TraverseAutoConfiguration {

    /**
     * Create the concurrent {@link Routes} bean.
     *
     * @return the concurrent {@link Routes} bean
     */
    @Bean
    @ConditionalOnMissingBean
    public Routes traverseRoutes() {
        return new Routes(true);
    }
}
