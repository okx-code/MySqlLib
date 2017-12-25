package sh.okx.sql.api.update;

import sh.okx.sql.api.StatementWhere;

public interface StatementDeleteFrom extends StatementUpdate, StatementWhere<StatementDeleteFrom> {
    /**
     * Add the low priority modifier to this statement.
     * The server will delay execution of the statement until not other clients are reading from the table.
     * @return This object.
     */
    StatementDeleteFrom lowPriority();

    /**
     * Add the quick modifier to this statement.
     * From the MySQL documentation:
     * <i>For MyISAM tables, if you use the QUICK modifier,
     * the storage engine does not merge index leaves during delete,
     * which may speed up some kinds of delete operations.</i>
     * @return This object.
     */
    StatementDeleteFrom quick();

    /**
     * Add the ignore modifier. to this statement.
     * This will ignore any runtime errors this statement might throw.
     * @return This object.
     */
    StatementDeleteFrom ignore();

    /**
     * A string to join the table names into a table reference in the statement.
     * @param join What to join the tables with into a reference. Default is "JOIN".
     * @return This object.
     */
    StatementDeleteFrom joinTables(String join);
}
