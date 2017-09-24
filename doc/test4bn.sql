-- just for BN Test

-- DB Name
CREATE DATABASE test4bn3;

-- 站點
CREATE TABLE site (
	site_id bigint(20) NOT NULL,
	site_name varchar(255) NOT NULL,  
	crt_datetime datetime DEFAULT NULL,
	upd_datetime datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE site ADD PRIMARY KEY (site_id);
ALTER TABLE site MODIFY site_id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- 護士
CREATE TABLE nurse (
	nurse_id bigint(20) NOT NULL,
	nurse_no varchar(255) NOT NULL,
	nurse_name varchar(255) NOT NULL,
	crt_datetime datetime DEFAULT NULL,
	upd_datetime datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE nurse ADD PRIMARY KEY (nurse_id);
ALTER TABLE nurse MODIFY nurse_id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- 站點護士表
CREATE TABLE site_nurse (
	site_id bigint(20) NOT NULL,
	nurse_id bigint(20) NOT NULL  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
