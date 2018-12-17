package cz.muni.fi.pa165.smartphonEShop.mvc.config;

import cz.muni.pa165.smartphonEShop.sampledata.EshopWithSampleDataConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

@EnableWebMvc
@Configuration
@Import({EshopWithSampleDataConfiguration.class})
@ComponentScan(basePackages = "cz.muni.fi.pa165.smartphonEShop.mvc.controllers")
public class MySpringMvcConfig implements WebMvcConfigurer
{
    private static final String TEXTS = "Texts";

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/auth").setViewName("auth");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    @Bean
    public Validator validator()
    {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}
