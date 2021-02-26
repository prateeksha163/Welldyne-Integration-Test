package scenarios.teardown;

import io.cucumber.java.en.Given;
import scenarios.Context;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TearDown {

    Context context;

    public TearDown(Context context) {
        this.context = context;
    }

    @Given("^Remove the client with code (.+)$")
    public void remove_client_with_id(String code) throws SQLException {
        Statement statement = this.context.connection.createStatement();
        String query = "select id from configdata.client where code='" + code + "'";
        ResultSet obj_rs = statement.executeQuery(query);
        while (obj_rs.next()) {
            String id = obj_rs.getString("id");
                String deleteClient ="select configdata.fn_delete_client('{"+ id + "}')";
            statement.execute(deleteClient);
        }
        statement.close();
    }


}
