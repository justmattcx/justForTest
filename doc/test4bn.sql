-- just for BN Test

-- DB Name
-- DROP DATABASE IF EXISTS test4bn99;
CREATE DATABASE test4bn99;

-- 站點
-- DROP TABLE IF EXISTS site;
CREATE TABLE site (
	site_id bigint(20) NOT NULL,
	site_name varchar(50) NOT NULL,  
	crt_datetime datetime DEFAULT NULL,
	upd_datetime datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE site ADD PRIMARY KEY (site_id);
ALTER TABLE site MODIFY site_id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- 護士
-- DROP TABLE IF EXISTS nurse;
CREATE TABLE nurse (
	nurse_id bigint(20) NOT NULL,
	nurse_no varchar(50) NOT NULL,
	nurse_name varchar(50) NOT NULL,
	crt_datetime datetime DEFAULT NULL,
	upd_datetime datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE nurse ADD PRIMARY KEY (nurse_id);
ALTER TABLE nurse MODIFY nurse_id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- 站點護士表
-- DROP TABLE IF EXISTS sitenurse;
CREATE TABLE sitenurse (
	sitenurse_id bigint(20) NOT NULL,
	nurse_id bigint(20) NOT NULL,
	site_id bigint(20) NOT NULL,
	crt_datetime datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE sitenurse ADD PRIMARY KEY (sitenurse_id);
ALTER TABLE sitenurse MODIFY sitenurse_id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;