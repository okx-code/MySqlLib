package sh.okx.sql.clause;

import sh.okx.sql.api.clause.ClauseColumn;
import sh.okx.sql.api.data.DataType;
import sh.okx.sql.api.update.StatementCreateTable;

public class ClauseColumnImpl<E extends StatementCreateTable> implements ClauseColumn<E> {
    private E statement;

    private DataType type;
    private String name;
    private boolean notNull = false;
    private Object defaultValue = null;
    private boolean autoIncrement = false;

    private ColumnFormat columnFormat = null;
    private Storage storage = null;

    private boolean unique = false;
    private boolean primary = false;
    private boolean key = false;

    @Override
    public String define() {
        StringBuilder sb = new StringBuilder(name).append(" ");

        sb.append(type);
        if(notNull) {
            sb.append(" NOT NULL");
        }
        if(defaultValue != null) {
            sb.append(" DEFAULT ").append(defaultValue);
        }
        if(autoIncrement) {
            sb.append(" AUTO_INCREMENT");
        }

        assert !(unique && primary);
        if(primary) {
            assert key;
            sb.append(" PRIMARY");
        } else if(unique) {
            sb.append(" UNIQUE");
        }

        if(key) {
            sb.append(" KEY");
        }

        if(columnFormat != null) {
            sb.append(" COLUMN_FORMAT ").append(columnFormat);
        }
        if(storage != null) {
            sb.append(" STORAGE ").append(storage);
        }

        return sb.toString();
    }

    public ClauseColumnImpl(E statement) {
        this.statement = statement;
    }

    @Override
    public ClauseColumn<E> name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ClauseColumn<E> notNull() {
        this.notNull = true;
        return this;
    }

    @Override
    public ClauseColumn<E> setNotNull(boolean notNull) {
        this.notNull = notNull;
        return this;
    }

    @Override
    public boolean isNotNull() {
        return notNull;
    }

    @Override
    public ClauseColumn<E> defaultValue(Object value) {
        this.defaultValue = value;
        return this;
    }

    @Override
    public ClauseColumn<E> autoIncrement() {
        this.autoIncrement = true;
        return this;
    }

    @Override
    public ClauseColumn<E> setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
        return this;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    @Override
    public ClauseColumn<E> unique() {
        assert !primary;
        this.unique = true;
        return this;
    }

    @Override
    public ClauseColumn<E> primary() {
        assert !unique;
        this.primary = true;
        return this;
    }

    @Override
    public ClauseColumn<E> key() {
        this.key = true;
        return this;
    }

    @Override
    public ClauseColumn<E> columnFormat(ColumnFormat columnFormat) {
        this.columnFormat = columnFormat;
        return this;
    }

    @Override
    public ColumnFormat getColumnFormat() {
        return columnFormat;
    }

    @Override
    public ClauseColumn<E> storage(Storage storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public ClauseColumn<E> type(DataType type) {
        this.type = type;
        return this;
    }

    @Override
    public DataType getType() {
        return type;
    }

    @Override
    public E then() {
        statement.column(define());
        return statement;
    }
}
