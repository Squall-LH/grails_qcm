dataSource {
	pooled = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
	logSql = true
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			//dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			//url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			pooled = true
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/qcm_grails"
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			username = "root"
			password = ""
			logSql = false
		}
	}
	test {
		dataSource {
			//dbCreate = "update"
			//url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			pooled = true
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/qcm_grails_test"
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			username = "root"
			password = ""
			logSql = true
			properties {
				maxActive = 100
				maxIdle = 25
				minIdle = 5
				initialSize = 5
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "SELECT 1"
			}
		}

	}
	production {/*
		dataSource {
			pooled = true
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/qcm_grails"
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			username = "root"
			password = ""
			logSql = false
			properties {
				maxActive = 100
				maxIdle = 25
				minIdle = 5
				initialSize = 5
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "SELECT 1"
			}
		}*/
		dataSource {
			dbCreate = "create-drop"
			pooled = false
			jndiName = "java:comp/env/jdbc/myDataSource"
		}
	}
}
