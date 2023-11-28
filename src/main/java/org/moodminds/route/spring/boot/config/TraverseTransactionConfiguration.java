package org.moodminds.route.spring.boot.config;

import org.springframework.transaction.interceptor.TraverseSupportTransactionAdvisory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.TransactionManager;

/**
 * The conditional on {@link TransactionManager} {@link TraverseSupportTransactionAdvisory} configuration bean.
 */
@Configuration
@ConditionalOnClass(TransactionManager.class)
@Import(TraverseSupportTransactionAdvisory.class)
public class TraverseTransactionConfiguration {}
