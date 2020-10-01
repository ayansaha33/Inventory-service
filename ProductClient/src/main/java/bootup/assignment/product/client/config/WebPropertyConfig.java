package bootup.assignment.product.client.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;


/**
 * Property file configurations
 */
@Configuration
@PropertySources({@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)})
public class WebPropertyConfig {

    @Autowired
    private Environment         itsProperties;

    /**
     * Get Compound Property
     *
     * @param inKey the value against which we need to find the value
     * @param inValues values corresponding to the key
     * @return the value corresponding from the properties to the key provided
     */
    public String getCompoundProperty(final String inKey, final String... inValues) {
        String theMessage = getProperty(inKey);
        if (StringUtils.isNotBlank(theMessage) && inValues != null) {
            final StringBuilder theStringBuilder = new StringBuilder();
            for (int i = 0; i < inValues.length; i++) {
                theStringBuilder.setLength(0);
                final String thePlaceHolder = theStringBuilder.append("\\{").append(i).append("\\}").toString();
                theMessage = theMessage.replaceFirst(thePlaceHolder, inValues[i]);
            }
        }
        return theMessage;
    }

    /**
     * Returns a List of properties
     *
     * @param inKey the value against which we need to find the value
     * @return value corresponding from the properties to the key provided
     */
    public List<String> getProperties(final String inKey) {
        final String theValue = getProperty(inKey);
        return preparePropertyList(theValue);
    }



    /**
     * Returns properties Map
     *
     * @param inKey the value against which we need to find the value
     * @return MAP values corresponding from the properties to the keys provided
     */
    public Map<String, String> getPropertiesMap(final String inKey) {
        final List<String> theValues = getProperties(inKey);
        return preparePropertyMap(theValues);
    }



    /**
     * Returns a property
     *
     * @param inKey the value against which we need to find the value
     * @return  the value corresponding from the properties to the keys provided
     */
    public String getProperty(final String inKey) {
       
        return itsProperties.getProperty(inKey);
    }


    /**
     * Prepare Property List
     *
     * @param the value against which we need to find the value
     * @return List the values corresponding from the properties to the key provided
     */
    private List<String> preparePropertyList(final String theValue) {
        if (!StringUtils.isEmpty(theValue)) {
            return Arrays.asList(theValue.split(","));
        }
        return new ArrayList<>(0);
    }

    /**
     * Prepare Mapped Properties List
     *
     * @param the value against which we need to find the value
     * @return Map the Values the values corresponding from the properties to the key provided
     */
    private Map<String, String> preparePropertyMap(final List<String> inValues) {
        if (!inValues.isEmpty()) {
            final Map<String, String> theMappedProperties = new LinkedHashMap<>();
            for (final String theProperty : inValues) {
                final String[] theKeyValue = theProperty.split("-");
                theMappedProperties.put(theKeyValue[0], theKeyValue[1]);
            }
            return theMappedProperties;
        }
        return new LinkedHashMap<>(0);
    }
}
