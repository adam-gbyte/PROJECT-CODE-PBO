-- Buat Database
CREATE DATABASE IF NOT EXISTS toko;
USE toko;

-- Buat Tabel barang
CREATE TABLE barang (
    kode CHAR(5) PRIMARY KEY,
    nama NVARCHAR(100),
    harga INT,
    stok INT
);

-- Buat Tabel log_insert_barang
CREATE TABLE log_insert_barang (
    kode CHAR(5),
    waktu CHAR(20)
);

-- Procedure untuk insert
DELIMITER //
CREATE PROCEDURE insert_barang(
    IN p_kode CHAR(5),
    IN p_nama NVARCHAR(100),
    IN p_harga INT,
    IN p_stok INT
)
BEGIN
    INSERT INTO barang(kode, nama, harga, stok)
    VALUES (p_kode, p_nama, p_harga, p_stok);
END;
//
DELIMITER ;

-- Trigger insert log
DELIMITER //
CREATE TRIGGER trg_log_insert
AFTER INSERT ON barang
FOR EACH ROW
BEGIN
    INSERT INTO log_insert_barang(kode, waktu)
    VALUES (NEW.kode, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'));
END;
//
DELIMITER ;

-- View untuk menampilkan data + total_nilai
CREATE OR REPLACE VIEW view_barang AS
SELECT 
    kode,
    nama,
    harga,
    stok,
    (harga * stok) AS total_nilai
FROM barang;
