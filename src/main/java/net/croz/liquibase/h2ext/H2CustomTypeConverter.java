package net.croz.liquibase.h2ext;

import liquibase.database.structure.type.BlobType;
import liquibase.database.structure.type.ClobType;
import liquibase.database.typeconversion.core.H2TypeConverter;

/**
 * Override default longvarchar and longvarbinary for clob and blob support
 * 
 * @author mknezic
 * 
 */
public class H2CustomTypeConverter extends H2TypeConverter {

    @Override
    public int getPriority() {
        // put higher priority to override default and database default
        return PRIORITY_DATABASE + 1;
    }

    @Override
    public ClobType getClobType() {
        return new ClobType("CLOB");
    }

    @Override
    public BlobType getBlobType() {
        return new BlobType("BLOB");
    }

}
