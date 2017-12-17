package sh.okx.sql.api.update;

import java.util.concurrent.CompletableFuture;

public interface StatementUpdate {
    /**
     * Execute the statement.
     * @return The returned row count or <code>-1</code> on failure.
     */
    int execute();

    /**
     * Asynchronous version of {@link StatementUpdate#execute()}
     * @see StatementUpdate#execute()
     */
    CompletableFuture<Integer> executeAsync();
}
