package sh.okx.sql.api.data;

/**
 * A class to represent the possible numeric data types of a column.
 */
public class NumericDataType implements DataType {
    private Type type = null;
    private int length = -1;
    private int decimals = -1;

    private boolean unsigned = false;
    private boolean zeroFill = false;

    public NumericDataType(Type type) {
        this.type = type;
    }

    public NumericDataType(Type type, int length) {
        assert !type.isDecimalRequired();

        this.type = type;
        this.length = length;
    }

    public NumericDataType(Type type, int length, int decimals) {
        assert type.isDecimal();

        this.type = type;
        this.length = length;
        this.decimals = decimals;
    }

    /**
     * Make the data type unsigned.
     * The default is signed.
     * @return This object.
     */
    public NumericDataType unsigned() {
        this.unsigned = true;
        return this;
    }

    /**
     * Make the data type signed.
     * The default is signed.
     * @return This object.
     */
    public NumericDataType signed() {
        this.unsigned = false;
        return this;
    }

    /**
     * Set whether this data type is unsigned or signed.
     * The default is signed.
     * @param unsigned Whether this data type should be unsigned or not.
     * @return This object.
     */
    public NumericDataType setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
        return this;
    }

    /**
     * Returns whether this data type is unsigned.
     * @return Whether this data type is unsigned.
     */
    public boolean isUnsigned() {
        return unsigned;
    }

    /**
     * Set this data type as zero fill.
     * The default is no zero fill.
     * @return This object.
     */
    public NumericDataType zeroFill() {
        this.zeroFill = true;
        return this;
    }

    /**
     * Unset this data type as zero fill.
     * This is the default.
     * @return This object.
     */
    public NumericDataType fill() {
        this.zeroFill = false;
        return this;
    }

    /**
     * Set whether this data type is zero fill or not.
     * @param zeroFill Whether this data type is zero fill or not.
     */
    public void setZeroFill(boolean zeroFill) {
        this.zeroFill = zeroFill;
    }

    /**
     * Returns whether this data type is zero fill.
     * @return Whether this data type is zero fill.
     */
    public boolean isZeroFill() {
        return zeroFill;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.name());
        if(length != -1) {
            sb.append("(").append(length);
            if(decimals != -1) {
                sb.append(",").append(decimals);
            }
            sb.append(")");
        }
        if(unsigned) {
            sb.append(" UNSIGNED").append(zeroFill ? " " : "");
        }
        if(zeroFill) {
            sb.append("ZEROFILL");
        }
        return sb.toString();
    }

    public enum Type {
        TINYINT(false, false),
        SMALLINT(false, false),
        MEDIUMINT(false, false),
        INT(false, false),
        INTEGER(false, false),
        BIGINT(false, false),
        REAL(true, true),
        DOUBLE(true, true),
        FLOAT(true, true),
        DECIMAL(true, false),
        NUMERIC(true, false);

        private boolean decimal;
        private boolean decimalRequired;

        Type(boolean decimal, boolean decimalRequired) {
            if(decimalRequired) {
                assert decimal;
            }
            this.decimal = decimal;
            this.decimalRequired = decimalRequired;
        }

        public boolean isDecimal() {
            return decimal;
        }

        public boolean isDecimalRequired() {
            return decimalRequired;
        }
    }
}
