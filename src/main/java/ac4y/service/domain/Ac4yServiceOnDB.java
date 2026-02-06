package ac4y.service.domain;

import java.io.IOException;
import java.sql.SQLException;

import ac4y.base.Ac4yContext;
import ac4y.base.Ac4yException;
import ac4y.base.database.DBConnection;

public class Ac4yServiceOnDB {

	public DBConnection getDBConnection() throws ClassNotFoundException, SQLException, IOException, Ac4yException{
		return new DBConnection();
	}
	
} // Ac4yServiceOnDB