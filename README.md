# TokoApp - Project PBO MySQL JDBC

## Struktur Folder
- `src/` : Source code program Java.
- `lib/` : JDBC Connector untuk MySQL.
- `sql/` : File SQL untuk membuat database `toko`, tabel, trigger, procedure, dan view.

## Cara Menjalankan
1. Import `mysql-connector-j-9.3.0.jar` ke classpath.
2. Jalankan `sql/setup.sql` di MySQL.
3. Compile dan jalankan program Java:
```bash
javac -cp ".;lib/mysql-connector-j-9.3.0.jar" src/Main.java
java -cp ".;lib/mysql-connector-j-9.3.0.jar;src" Main
```