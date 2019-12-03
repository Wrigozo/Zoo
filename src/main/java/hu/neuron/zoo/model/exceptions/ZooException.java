package hu.neuron.zoo.model.exceptions;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;


/**
 * An exception class that handles the {@code exception.properties} file.
 */
public class ZooException extends Exception {

    protected Collection<String> messages;

    protected static Properties props;

    protected String propFileName;

    protected InputStream inputStream;

    {
        props = new Properties();
        propFileName = "exception.properties";
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

    public ZooException() {
        super();
    }

    public ZooException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        String msg;

        if (this.messages != null && !this.messages.isEmpty()) {
            msg = "[";

            for (String message : this.messages) {
                msg += message + ",";
            }

            msg = StringUtils.removeEnd(msg, ",") + "]";

        } else msg = super.getMessage();

        return msg;
    }

}
