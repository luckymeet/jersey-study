package com.study.resolver;

import org.glassfish.jersey.internal.inject.Injectee;
import org.glassfish.jersey.internal.inject.InjectionResolver;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;

import javax.inject.Singleton;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Singleton
public class AutowiredInjectResolver
        implements InjectionResolver<Autowired>
{
    private static final Logger LOGGER = Logger.getLogger(AutowiredInjectResolver.class.getName());
    private volatile ApplicationContext ctx;

    public AutowiredInjectResolver(ApplicationContext ctx)
    {
        this.ctx = ctx;
    }

    public Object resolve(Injectee injectee)
    {
        AnnotatedElement parent = injectee.getParent();
        String beanName = null;
        if (parent != null) {
            Qualifier an = parent.getAnnotation(Qualifier.class);
            if (an != null) {
                beanName = an.value();
            }
        }
        boolean required = (parent != null) ? (parent.getAnnotation(Autowired.class)).required() : false;
        return getBeanFromSpringContext(beanName, injectee, required);
    }

    private Object getBeanFromSpringContext(String beanName, Injectee injectee, boolean required) {
        try {
            DependencyDescriptor dependencyDescriptor = createSpringDependencyDescriptor(injectee);
            Set autowiredBeanNames = new HashSet(1);
            autowiredBeanNames.add(beanName);
            return this.ctx.getAutowireCapableBeanFactory().resolveDependency(dependencyDescriptor, null, autowiredBeanNames, null);
        }
        catch (NoSuchBeanDefinitionException e) {
            if (required) {
                LOGGER.warning(e.getMessage());
                throw e; }
        }
        return null;
    }

    private DependencyDescriptor createSpringDependencyDescriptor(Injectee injectee)
    {
        AnnotatedElement annotatedElement = injectee.getParent();

        if (annotatedElement.getClass().isAssignableFrom(Field.class))
            return new DependencyDescriptor((Field)annotatedElement, !(injectee.isOptional()));
        if (annotatedElement.getClass().isAssignableFrom(Method.class)) {
            return
                    new DependencyDescriptor(new MethodParameter((Method)annotatedElement, injectee
                            .getPosition()), !(injectee.isOptional()));
        }
        return
                new DependencyDescriptor(new MethodParameter((Constructor)annotatedElement, injectee
                        .getPosition()), !(injectee.isOptional()));
    }

    public boolean isConstructorParameterIndicator()
    {
        return false;
    }

    public boolean isMethodParameterIndicator()
    {
        return false;
    }

    public Class<Autowired> getAnnotation()
    {
        return Autowired.class;
    }
}