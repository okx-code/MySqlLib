package sh.okx.sql.api.data;

public class OtherDataType implements DataType {
    private Type type;
    private Object value;

    public OtherDataType(Type type) {
        this.type = type;
    }

    public OtherDataType(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Set the field for this object.
     * The data type must return true on {@link Type#hasField()}
     * @param value The new field for this object.
     * @return This object.
     */
    public OtherDataType field(Object value) {
        assert type.field;
        this.value = value;
        return this;
    }

    /**
     * Returns the field set for this object.
     * @return The field for this object.
     */
    public Object getField() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.name());
        if(type.field && value != null) {
            sb.append("(").append(value);
            sb.append(")");
        } else {
            assert !type.required;
        }
        return sb.toString();
    }

    public enum Type {
        BIT(true),
        DATE,
        TIME(true),
        TIMESTAMP(true),
        DATETIME(true),
        YEAR,
        BINARY(true),
        VARBINARY(true, true),
        TINYBLOB,
        BLOB(true),
        MEDIUMBLOB,
        LONGBLOB,
        JSON;

        private boolean field = false;
        private boolean required = false;
        private Object value;

        Type() {}

        Type(boolean field) {
            this.field = field;
        }

        Type(boolean field, boolean required) {
            assert !required || field;

            this.field = field;
            this.required = required;
        }

        /**
         * Returns whether this data type has a field.
         * A data type with a field is BLOB: BLOB(4)
         * A data type without a field is TINYBLOB.
         * @return
         */
        public boolean hasField() {
            return field;
        }

        /**
         * Returns whether this data type requires a field.
         * A data type will only require a field if it also has one.
         * @return
         */
        public boolean isRequired() {
            return required;
        }
    }
}