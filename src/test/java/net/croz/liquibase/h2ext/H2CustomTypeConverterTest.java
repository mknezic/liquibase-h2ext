package net.croz.liquibase.h2ext;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Unit test for H2CustomTypeConverter
 */
public class H2CustomTypeConverterTest {


    @org.junit.Test
    public void testApp() {
        H2CustomTypeConverter converter = new H2CustomTypeConverter();
        assertThat(converter.getClobType().getDataTypeName()).isEqualToIgnoringCase("CLOB");
        assertThat(converter.getBlobType().getDataTypeName()).isEqualToIgnoringCase("BLOB");
    }
}
