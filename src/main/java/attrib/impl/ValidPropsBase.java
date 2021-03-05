package attrib.impl;

import attrib.iface.PropertyEnum;
import attrib.iface.IValidProps;
import validator.Validators;

import java.util.Properties;

public abstract class ValidPropsBase implements IValidProps {
    protected PropertyEnum propertyEnum;
    protected String key, val;

    public ValidPropsBase() {
    }

    protected abstract boolean findPropertyEnum(String key);

    @Override
    public boolean put(String keyValPair, Properties properties) {
        if(
            Validators.VALID_KEY_VAL.isGood(this, keyValPair) &&
            this.findPropertyEnum(key) &&
            this.propertyEnum.validator().isGood(val)
        ){
            properties.put(key, val);
            return true;
        }
        return false;
    }

//    @Override
//    public void loadDefaults(Properties properties) {
//
//    }

    @Override
    public void receive(String key, String val) {
        this.key = key;
        this.val = val;
    }
}
