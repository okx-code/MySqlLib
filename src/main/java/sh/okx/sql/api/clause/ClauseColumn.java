package sh.okx.sql.api.clause;

import sh.okx.sql.api.data.DataType;

public interface ClauseColumn<E> extends Clause<E> {
    /**
     * Set the name of this column.
     * This method must be called before {@link ClauseColumn#then()}!
     * @param name The name to set this column too
     * @return This object.
     */
    ClauseColumn<E> name(String name);

    /**
     * Return the name of this column.
     * @return The name of this column
     */
    String getName();

    /**
     * Require this column to have a value that is not null.
     * @return This object.
     */
    ClauseColumn<E> notNull();

    /**
     * Set whether this column should have a value that is not null.
     * @param notNull Whether this column should have a value that is not null.
     * @return This object.
     */
    ClauseColumn<E> setNotNull(boolean notNull);

    /**
     * Return whether this column must have a value that is not null.
     * @return Whether this column must have a value that is not null.
     */
    boolean isNotNull();

    /**
     * Set the default value of this column.
     * @param value The new default value of this column.
     * @return This object.
     */
    ClauseColumn<E> defaultValue(Object value);

    ClauseColumn<E> autoIncrement();

    /**
     * Set whether the value of this column should automatically increment (starting from 1)
     * @param autoIncrement Whether the value of this column should automatically increment.
     * @return This object.
     */
    ClauseColumn<E> setAutoIncrement(boolean autoIncrement);

    /**
     * Return whether the value of this column will automatically increment.
     * @return Whether the value fo this column will automatically increment.
     */
    boolean isAutoIncrement();

    // UNIQUE | UNIQUE KEY | KEY | PRIMARY KEY

    /**
     * Mark this column as unique
     * @return This object.
     */
    ClauseColumn<E> unique();

    /**
     * Mark this object as the primary [key].
     * This must be combined with {@link ClauseColumn#key()}.
     * @return This object.
     */
    ClauseColumn<E> primary();

    /**
     * Mark this object as the key.
     * @return This object.
     */
    ClauseColumn<E> key();

    /**
     * Set the column format as any of: FIXED, DYNAMIC or DEFAULT.
     * @param columnFormat The column format for this column.
     * @return This object.
     */
    ClauseColumn<E> columnFormat(ColumnFormat columnFormat);

    /**
     * Return the column format for this column.
     * @return The column format for this column.
     */
    ColumnFormat getColumnFormat();

    /**
     * Set the storage for this column.
     * This can be any of: DISK, MEMORRY or DEFAULT.
     * @param storage The storage for this column.
     * @return
     */
    ClauseColumn<E> storage(Storage storage);

    /**
     * Return the storage for this column.
     * @return The storage for this column.
     */
    Storage getStorage();

    /**
     * Set the data type for this column.
     * @param type The new data type for this column.
     * @return This object.
     */
    ClauseColumn<E> type(DataType type);

    /**
     * Returns the data type for this column.
     * @return The data type for this column.
     */
    DataType getType();


    /**
     * An enum representing the possible values for storage of a column.
     */
    enum Storage {
        DISK,
        MEMORY,
        DEFAULT;
    }

    /**
     * An enum representing the possible values for the column format.
     */
    enum ColumnFormat {
        FIXED,
        DYNAMIC,
        DEFAULT;
    }
}
