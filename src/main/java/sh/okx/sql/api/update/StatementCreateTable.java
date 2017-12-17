package sh.okx.sql.api.update;

import sh.okx.sql.api.clause.ClauseColumn;

public interface StatementCreateTable extends StatementUpdate {
    /**
     * Mark this table as temporary.
     * @return This object.
     */
    StatementCreateTable temporary();

    /**
     * Unmark this table as temporary.
     * @return This object.
     */
    StatementCreateTable permanent();

    /**
     * Set whether this table will be temporary.
     * @param temporary Whether this table will be temporary.
     * @return This object.
     */
    StatementCreateTable setTemporary(boolean temporary);

    /**
     * Returns whether this table will be temporary.
     * @return This object.
     */
    boolean isTemporary();

    /**
     * Only create the table if it doesn't exist already.
     * If you don't call this and the table exists, an error will be thrown.
     * If you call this and the table exists, it will look like it succeeded.
     * @return This object.
     */
    StatementCreateTable ifNotExists();

    /**
     * Set whether this table should only be created if it doesn't exist already.
     * @param ifNotExists Whether this table should only be created if it doesn't exist already.
     * @return This object.
     */
    StatementCreateTable setIfNotExists(boolean ifNotExists);

    /**
     * Returns whether this table should only be created if it doesn't exist already.
     * @return Whether this table should only be created if it doesn't exist already.
     */
    boolean isIfNotExists();

    /**
     * Create this table like another table.
     * @param table The table this table should copy.
     * @return This object.
     */
    StatementCreateTable like(String table);

    /**
     * Returns the table this table will be like.
     * @return The table this table will be like.
     */
    String getLike();

    /**
     * Create a new column for this table.
     * @return An object to assist creating a column for this table.
     */
    ClauseColumn<StatementCreateTable> column();

    /**
     * Add a column for this table in SQL language.
     * @param column The column to be added to this table, represented in SQL.
     * @return This object.
     */
    StatementCreateTable column(String column);
}
