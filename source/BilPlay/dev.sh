#!/bin/bash

if [ $1 == "run" ]; then
	docker-compose stop bilplay
	docker-compose build bilplay
	docker-compose up -d bilplay
	docker-compose logs -f bilplay
elif [ $1 == "stop" ]; then
	docker-compose stop bilplay
elif [ $1 == "db" ]; then
	docker-compose exec db mysql -uadmin -ppassword -Dbilplay
elif [ $1 == "init-db" ]; then
	docker cp schema.sql bilplay_db_1:/
	docker exec bilplay_db_1 /bin/sh -c 'mysql -uadmin -ppassword -Dbilplay </schema.sql'
else
	echo "unknown command"
fi
