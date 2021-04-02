package softuni.judgev2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
    public ApplicationBeanConfiguration() {
    }
    @Bean
    public ModelMapper modelMapper(){
         return new ModelMapper();
    }
}
