CREATE TABLE `sample_user` (
  `id` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
INSERT INTO sample_user (id,age,last_name,name) VALUES
('694d3b3b-6a12-4d67-b74e-2a065b656187',30,'da Silva','Joao')
,('5a85ccaa-d35a-41ac-9b84-e4c99fd8d629',39,'das Dores','Maria')
,('d9db6c15-ed17-48cd-9073-35be7265759c',8,'Rodrigues','Luis');