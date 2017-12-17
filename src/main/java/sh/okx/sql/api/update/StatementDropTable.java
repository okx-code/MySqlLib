package sh.okx.sql.api.update;

public interface StatementDropTable extends StatementUpdate {
    /**
     * Make this statement delete only temporary tables.
     * This does not cause an implicit commit.
     * No access rights are checked.
     * @return This object.
     */
    StatementDropTable temporary();

    /**
     * Delete all tables specified.
     * This is default.
     * @return This object.
     */
    StatementDropTable permanent();

    /**
     * Set whether this statement should only delete temporary tables.
     * @param temporary Whether this statement should only delete temporary tables.
     * @return This object.
     */
    StatementDropTable setTemporary(boolean temporary);

    /**
     * Returns whether this object only drops temporary tables.
     * @return Whether this object only drops temporary tables.
     */
    boolean isTemporary();

    /**
     * Only delete tables if they exist.
     * @return This object.
     */
    StatementDropTable ifExists();

    /**
     * Set whether this should only delete tables if they exist.
     * @param ifExists Whether this should only delete tables if they exist.
     * @return
     */
    StatementDropTable setIfExists(boolean ifExists);

    /**
     * Returns whether this will only delete tables if they exist.
     * @return Whether this will only delete tables if they exist.
     */
    boolean isIfExists();
}
