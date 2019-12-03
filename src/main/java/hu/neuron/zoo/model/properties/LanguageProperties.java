package hu.neuron.zoo.model.properties;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Setter
@Getter
public class LanguageProperties {
    public static Properties props;

    private String propFileName;

    private InputStream inputStream;

    public  LanguageProperties(String propFileName){
        this.propFileName=propFileName;
        props = new Properties();
        try {

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                props.load(inputStream);

            } else {
                System.out.println("property file '" + propFileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            System.out.println("itt a hiba");

        } finally {

            try {
                inputStream.close();

            } catch (IOException e) {
                System.out.println("nem zárt be");
            }
        }
    }
}
