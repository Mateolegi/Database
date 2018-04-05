package io.github.mateolegi.database.util;

import java.io.InputStream;

public class PersistenceFile {

	private InputStream schemaIS = this.getClass().getClassLoader().getResourceAsStream("META-INF/persistence.xml");
}
