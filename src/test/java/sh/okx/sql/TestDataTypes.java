package sh.okx.sql;

import org.junit.Test;
import sh.okx.sql.api.data.NumericDataType;

public class TestDataTypes {
    @Test
    public void numeric() {
        String bigint = new NumericDataType(NumericDataType.Type.REAL, 3, 5)
                .zeroFill().unsigned().toString();

        System.out.println(bigint);
    }
}
