package net.croz.liquibase.h2ext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import liquibase.database.core.H2Database;
import liquibase.database.typeconversion.TypeConverter;
import liquibase.database.typeconversion.core.DefaultTypeConverter;
import liquibase.database.typeconversion.core.H2TypeConverter;

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

    @org.junit.Test
    public void testPriority() {
        // prepare list of converters
        List<TypeConverter> allConverters = new ArrayList<TypeConverter>();
        H2CustomTypeConverter expected = new H2CustomTypeConverter();
        allConverters.add(expected);
        H2TypeConverter converter1 = new H2TypeConverter();
        allConverters.add(converter1);
        DefaultTypeConverter converter2 = new DefaultTypeConverter();
        allConverters.add(converter2);
        H2Database database = new H2Database();
        SortedSet<TypeConverter> converters = new TreeSet<TypeConverter>(new Comparator<TypeConverter>() {
            public int compare(final TypeConverter o1, final TypeConverter o2) {
                return Integer.valueOf(o1.getPriority()).compareTo(o2.getPriority());
            }
        });

        for (TypeConverter converter : allConverters) {
            if (converter.supports(database)) {
                converters.add(converter);
            }
        }
        // must pick h2 custom converter
        assertThat(converters.last()).isSameAs(expected);
    }
}
