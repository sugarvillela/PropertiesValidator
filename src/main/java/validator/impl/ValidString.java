package validator.impl;

import attrib.iface.IValidProps;
import validator.iface.IValid;

public class ValidString implements IValid {
    private static ValidString instance;

    public static ValidString initInstance(){
        return (instance == null)? (instance = new ValidString()): instance;
    }

    private ValidString(){}

    @Override
    public boolean isGood(String text) {
        return text != null && !text.isEmpty();
    }

    @Override
    public boolean isGood(IValidProps requester, String text) {
        return this.isGood(text);
    }
}
