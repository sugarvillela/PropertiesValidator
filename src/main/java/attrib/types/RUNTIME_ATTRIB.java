package attrib.types;

import attrib.iface.PropertyEnum;
import attrib.iface.IValidProps;
import attrib.impl.ValidPropsBase;
import validator.Validators;
import validator.iface.IValid;

import java.util.Properties;

public enum RUNTIME_ATTRIB implements PropertyEnum {
    PROJ_NAME       (Validators.VALID_STRING,   "MyProject"),
    SOME_BOOLEAN    (Validators.VALID_BOOL,     "true"),
    SOME_NUMBER     (Validators.VALID_INT,      "75")
    ;

    private final IValid validator;
    private final String defValue;

    RUNTIME_ATTRIB(IValid validator, String defValue) {
        this.validator = validator;
        this.defValue = defValue;
    }

    @Override
    public String defKey() {
        return this.toString();
    }

    @Override
    public String defValue() {
        return this.defValue;
    }

    @Override
    public IValid validator() {
        return this.validator;
    }

    public static IValidProps props = new ValidPropsBase() {
        @Override
        protected boolean findPropertyEnum(String key) {
            try{
                this.propertyEnum = RUNTIME_ATTRIB.valueOf(key);
                return true;
            }
            catch(IllegalArgumentException | NullPointerException e){
                return false;
            }
        }

        @Override
        public Properties getDefaults() {
            Properties properties = new Properties();
            for(PropertyEnum propertyEnum : RUNTIME_ATTRIB.values()){
                properties.put(propertyEnum.defKey(), propertyEnum.defValue());
            }
            return properties;
        }
    };
}
