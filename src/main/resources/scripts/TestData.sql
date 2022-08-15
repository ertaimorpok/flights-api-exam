CREATE TABLE `test`.`new_table` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `flight_number` VARCHAR(10) NOT NULL,
  `airline_code` VARCHAR(5) NOT NULL,
  `departure_port` VARCHAR(5) NOT NULL,
  `arrival_port` VARCHAR(5) NOT NULL,
  `departure_time` DATETIME NOT NULL,
  `arrival_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

commit;

insert into flights (flight_number, airline_code, departure_port, arrival_port, departure_time, arrival_time)
values
('PR220', 'PR', 'MIA', 'PYJ', date_sub(now(), interval 5 day), now()),
('EK750', 'EK', 'LAX', 'KIX', date_sub(now(), interval 6 day), now()),
('QF400', 'QF', 'MEL', 'SYD', date_sub(now(), interval 3 day), now());

commit;