package com.demo.poker.config;

import com.demo.poker.converter.CardStringFromTextBoxToCardModelMatrixConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Deividas
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new CardStringFromTextBoxToCardModelMatrixConverter());
  }

}
