package br.com.desafio.builder.cliente.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import br.com.desafio.builder.cliente.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPropertiesExtractor {

    private static Properties properties;

    private TestPropertiesExtractor() {
    }

    static {
        properties = new Properties();
        URL url = TestPropertiesExtractor.class.getClassLoader().getResource("application-test.properties");
        try {
            properties.load(new FileInputStream(url.getPath()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
