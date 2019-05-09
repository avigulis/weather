package org.assignment.weather.timezone;

import java.util.Arrays;
import java.util.TimeZone;

import liquibase.change.custom.CustomSqlChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.executor.ExecutorService;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.InsertSetStatement;
import liquibase.statement.core.InsertStatement;
import liquibase.statement.core.RawSqlStatement;
import lombok.SneakyThrows;

public class LoadTimeZone implements CustomSqlChange {

    private String tableName;

    @Override
    public SqlStatement[] generateStatements(Database database) throws CustomChangeException {
        String[] tzIds = TimeZone.getAvailableIDs();

        InsertSetStatement statement = new InsertSetStatement(
                database.getLiquibaseCatalogName(),
                database.getLiquibaseSchemaName(),
                tableName,
                tzIds.length
        );


        Arrays.stream(tzIds).forEach(tz -> {
            TimeZone timeZone = TimeZone.getTimeZone(tz);

            InsertStatement insert = new InsertStatement(
                    database.getLiquibaseCatalogName(),
                    database.getLiquibaseSchemaName(),
                    tableName
            );

            insert.addColumnValue("time_zone_id", timeZoneSeqValue(database));
            insert.addColumnValue("zone_id", timeZone.getID());
            insert.addColumnValue("display_name", timeZone.getDisplayName());
            insert.addColumnValue("dst_enabled", timeZone.useDaylightTime());
            insert.addColumnValue("dst_offset", timeZone.getDSTSavings());
            insert.addColumnValue("raw_offset", timeZone.getRawOffset());
            statement.addInsertStatement(insert);
        });

        return new SqlStatement[]{ statement };
    }

    @SneakyThrows
    private long timeZoneSeqValue(Database database) {
        RawSqlStatement nextSeqStatement = new RawSqlStatement("select nextval('time_zones_seq')");
        return ExecutorService.getInstance()
                .getExecutor(database)
                .queryForLong(nextSeqStatement);
    }

    @Override
    public String getConfirmationMessage() {
        return "Time zone data updated";
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return new ValidationErrors();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
