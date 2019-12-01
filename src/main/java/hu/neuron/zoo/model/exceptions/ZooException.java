package hu.neuron.zoo.model.exceptions;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class ZooException extends Exception {

    private Collection<String> messages;

    public ZooException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage(){
        String msg;

        if(this.messages!=null && !this.messages.isEmpty()){
            msg="[";

            for(String message : this.messages){
                msg+=message+",";
            }

            msg= StringUtils.removeEnd(msg, ",")+"]";

        }else msg= super.getMessage();

        return msg;
    }

}
