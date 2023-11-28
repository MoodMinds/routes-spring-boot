package org.moodminds.route.spring.boot.config;

import org.springframework.web.servlet.mvc.method.annotation.TraverseSupportMappingHandlerAdaptation;
import org.moodminds.route.traverse.Routes;
import org.springframework.beans.factory.BeanCreationException;
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
public class TraverseRoutesAutoConfiguration {

    /**
     * Create the {@link Routes} bean.
     *
     * @return the {@link Routes} bean
     */
    @Bean
    @ConditionalOnMissingBean
    public Routes traverseRoutes() {
        try {
            return new Routes();
        } catch (Exception ex) {
            throw new BeanCreationException("Failed to create Traversable Routes instance.", ex);
        }
    }
}
