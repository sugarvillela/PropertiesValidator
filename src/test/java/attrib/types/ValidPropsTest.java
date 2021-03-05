package attrib.types;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Properties;

class ValidPropsTest {

    String propsToString(Properties properties){
        String[] list = new String[properties.size()];
        int k = 0;
        for (Object key: properties.keySet()) {
            list[k++] = key + "=" + properties.getProperty(key.toString());
            System.out.println(key + ": " + properties.getProperty(key.toString()));
        }
        return String.join(",", list);
    }
    @Test
    void defaultValues() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidKeyVal_shouldReturnFalse() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("Bartholomew", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(false, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidKey_shouldReturnFalse() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("SOME_HOOLIGAN=false", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(false, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidValBool_shouldReturnFalse() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("SOME_BOOLEAN=Bartholomew", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(false, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidValInt_shouldReturnFalse() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("SOME_NUMBER=Bartholomew", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(false, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putInvalidValString_shouldReturnFalse() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("PROJ_NAME=", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(false, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putValidKeyValBool_shouldReturnTrueAndSet() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("SOME_BOOLEAN=false", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=false,SOME_NUMBER=75,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(true, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putValidKeyValString_shouldReturnTrueAndSet() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("PROJ_NAME=MyNewProject", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=75,PROJ_NAME=MyNewProject";
        System.out.println(actual);
        Assertions.assertEquals(true, success);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void putValidKeyValInt_shouldReturnTrueAndSet() {
        Properties runTimeAttrib = RUNTIME_ATTRIB.props.getDefaults();
        boolean success = RUNTIME_ATTRIB.props.put("SOME_NUMBER=24", runTimeAttrib);
        String actual = propsToString(runTimeAttrib);
        String expected = "SOME_BOOLEAN=true,SOME_NUMBER=24,PROJ_NAME=MyProject";
        System.out.println(actual);
        Assertions.assertEquals(true, success);
        Assertions.assertEquals(expected, actual);
    }
}