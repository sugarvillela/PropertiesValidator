package attrib.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import validator.ERR_TYPE;

import java.io.File;
import java.util.Properties;

/** Testing:
 *  A class to hold enumerated properties with validation:
 *      Setting individual properties with a single key=val string.
 *      Retrieving all properties.
 *      Loading and saving all props to file.
 *      Defining property keys and default values solely from a Java enum set.
 *      Validation of key=val, existing key and  correct value types.
 *      Defined set of error types, so error handling can be done by the calling class.
 *      Err types: No Err, KeyVal err, Unknown key err or value type err.
 */
class ValidPropsTest {
    public static final String DEFAULT_PATH = "src"+ File.separator+"test"+File.separator+"resources"+File.separator;
    public static boolean SHOW_MESSAGES = true;

    @BeforeEach
    void restoreDefaults(){// for multiple tests
        RUNTIME_ATTRIB.props.restoreDefaults();
    }

    String propsToString(Properties properties){
        String[] list = new String[properties.size()];
        int k = 0;
        for (Object key: properties.keySet()) {
            list[k++] = key + "=" + properties.getProperty(key.toString());
            //System.out.println(key + ": " + properties.getProperty(key.toString()));
        }
        return String.join(",", list);
    }

    void showMessage(ERR_TYPE errType){
        if(SHOW_MESSAGES && errType.isErr()){
            System.out.println(errType.message());
        }
    }

    @Test
    void defaultValues() {
        String actual = propsToString(RUNTIME_ATTRIB.props.getDefaults());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidKeyVal_shouldReturnErr() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("Bartholomew");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.NOT_KEY_VAL, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidKey_shouldReturnErr() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("SOME_HOOLIGAN=false");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.UNKNOWN_KEY, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidValBool_shouldReturnErr() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("SOME_BOOLEAN=Bartholomew");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.INVALID_BOOL, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidValInt_shouldReturnErr() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("SOME_NUMBER=Bartholomew");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.INVALID_INT, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidValString_shouldReturnErr() {// looking for a string err, but keyVal err catches it first
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("PROJ_NAME=");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.NOT_KEY_VAL, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putValidKeyValBool_shouldReturnNoErrAndSet() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("SOME_BOOLEAN=false");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=false,SOME_NUMBER=75,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.NONE, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putValidKeyValString_shouldReturnNoErrAndSet() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("PROJ_NAME=MyNewProject");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyNewProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.NONE, errType);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putValidKeyValInt_shouldReturnNoErrAndSet() {
        ERR_TYPE errType = RUNTIME_ATTRIB.props.put("SOME_NUMBER=24");
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=24,PROJ_NAME=MyProject";
        //System.out.println("\n========\n" + actual);
        showMessage(errType);
        Assertions.assertEquals(ERR_TYPE.NONE, errType);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void propsSaveAndReopenFile_shouldReturnNoErr() {
        ERR_TYPE errType;
        String actual, expected;

        errType = RUNTIME_ATTRIB.props.put("SOME_NUMBER=2001");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);
        errType = RUNTIME_ATTRIB.props.put("PROJ_NAME=AltProjName");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);

        actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        expected = "SOME_BOOLEAN=true,SOME_NUMBER=2001,PROJ_NAME=AltProjName";
        Assertions.assertEquals(expected, actual);

        errType = RUNTIME_ATTRIB.props.saveToFile(DEFAULT_PATH + "RUNTIME_ATTRIB.properties");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);

        RUNTIME_ATTRIB.props.restoreDefaults();

        actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        Assertions.assertEquals(expected, actual);

        errType = RUNTIME_ATTRIB.props.loadFromFile(DEFAULT_PATH + "RUNTIME_ATTRIB.properties");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);

        actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        expected = "SOME_BOOLEAN=true,SOME_NUMBER=2001,PROJ_NAME=AltProjName";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sameTestWithDifferentEnumSet_shouldReturnNoErr() {
        ERR_TYPE errType;
        String actual, expected;

        errType = EXPENDITURES.props.put("WINE=2001");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);
        errType = EXPENDITURES.props.put("TEQUILA=8995");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);

        actual = propsToString(EXPENDITURES.props.getProperties());
        expected = "WINE=2001,ENTERTAINMENT=true,TEQUILA=8995,BEER=5000";
        Assertions.assertEquals(expected, actual);

        errType = EXPENDITURES.props.saveToFile(DEFAULT_PATH + "EXPENDITURES.properties");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);

        EXPENDITURES.props.restoreDefaults();

        actual = propsToString(EXPENDITURES.props.getProperties());
        expected = "WINE=1700,ENTERTAINMENT=true,TEQUILA=2000,BEER=5000";
        Assertions.assertEquals(expected, actual);

        errType = EXPENDITURES.props.loadFromFile(DEFAULT_PATH + "EXPENDITURES.properties");
        Assertions.assertEquals(ERR_TYPE.NONE, errType);

        actual = propsToString(EXPENDITURES.props.getProperties());
        expected = "WINE=2001,ENTERTAINMENT=true,TEQUILA=8995,BEER=5000";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void openFileWithInvalidKey_shouldDiscardChangesAndReturnErr() {
        ERR_TYPE errType;
        errType = RUNTIME_ATTRIB.props.loadFromFile(DEFAULT_PATH + "RUNTIME_ATTRIB_bad.properties");
        Assertions.assertEquals(ERR_TYPE.UNKNOWN_KEY, errType);
        String actual = propsToString(RUNTIME_ATTRIB.props.getProperties());
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        Assertions.assertEquals(expected, actual);
    }
}