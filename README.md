# Library
1) Для работы используйте БД MySQL версии 8.0.19.Убедитесь, что не существует БД с именем task;
2) Для создания таблиц выполните скрипт schema.sql;
3) Для заполнения таблиц исходными данными выполните скрипт demo_data.sql;
4) В файле database.properties установите username и password в соответствии с вашей БД;
5) В файле mail.properties установите ваш email и password;
6) В папке maven/conf в файле settings.xml задекларировать сервер;
7) В папке tomcat/conf в файле tomcat-users.xml задекларировать user и roles;
8) Выполните maven clean install;
9) В папке tomcat/bin запустите startup.bat;
10) В папке с проектом пропишите в cmd следующее: mvn tomcat7:deploy;
11) В адресной стркое браузера введите localhost:8080;
12) Have fun:)

