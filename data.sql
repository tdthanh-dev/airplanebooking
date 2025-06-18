
SET session_replication_role = 'replica';

DELETE FROM seats;
DELETE FROM flights;
DELETE FROM airplanes;
DELETE FROM airlines;
DELETE FROM airports;

SET session_replication_role = 'origin';

ALTER SEQUENCE IF EXISTS airlines_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS airplanes_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS airports_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS flights_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS seats_id_seq RESTART WITH 1;


INSERT INTO airports (name, city, state, country, iata_code, icao_code, created_at, updated_at) VALUES
-- Sân bay quốc tế chính
('Noi Bai International Airport', 'Hanoi', 'Hanoi', 'Vietnam', 'HAN', 'VVNB', NOW(), NOW()),
('Tan Son Nhat International Airport', 'Ho Chi Minh City', 'Ho Chi Minh City', 'Vietnam', 'SGN', 'VVTS', NOW(), NOW()),
('Da Nang International Airport', 'Da Nang', 'Da Nang', 'Vietnam', 'DAD', 'VVDN', NOW(), NOW()),
('Cam Ranh International Airport', 'Nha Trang', 'Khanh Hoa', 'Vietnam', 'CXR', 'VVCR', NOW(), NOW()),
('Phu Quoc International Airport', 'Phu Quoc', 'Kien Giang', 'Vietnam', 'PQC', 'VVPQ', NOW(), NOW()),

-- Sân bay khu vực quan trọng
('Can Tho International Airport', 'Can Tho', 'Can Tho', 'Vietnam', 'VCA', 'VVCT', NOW(), NOW()),
('Cat Bi International Airport', 'Hai Phong', 'Hai Phong', 'Vietnam', 'HPH', 'VVCI', NOW(), NOW()),
('Lien Khuong Airport', 'Da Lat', 'Lam Dong', 'Vietnam', 'DLI', 'VVDL', NOW(), NOW()),
('Pleiku Airport', 'Pleiku', 'Gia Lai', 'Vietnam', 'PXU', 'VVPK', NOW(), NOW()),
('Quy Nhon Airport', 'Quy Nhon', 'Binh Dinh', 'Vietnam', 'UIH', 'VVQN', NOW(), NOW()),

-- Sân bay nội địa
('Vinh Airport', 'Vinh', 'Nghe An', 'Vietnam', 'VII', 'VVVH', NOW(), NOW()),
('Dong Hoi Airport', 'Dong Hoi', 'Quang Binh', 'Vietnam', 'VDH', 'VVDH', NOW(), NOW()),
('Hue Airport', 'Hue', 'Thua Thien-Hue', 'Vietnam', 'HUI', 'VVPB', NOW(), NOW()),
('Chu Lai Airport', 'Tam Ky', 'Quang Nam', 'Vietnam', 'VCL', 'VVCL', NOW(), NOW()),
('Buon Ma Thuot Airport', 'Buon Ma Thuot', 'Dak Lak', 'Vietnam', 'BMV', 'VVBM', NOW(), NOW()),

-- Sân bay miền Nam
('Ca Mau Airport', 'Ca Mau', 'Ca Mau', 'Vietnam', 'CAH', 'VVCM', NOW(), NOW()),
('Rach Gia Airport', 'Rach Gia', 'Kien Giang', 'Vietnam', 'VKG', 'VVRG', NOW(), NOW()),
('Con Dao Airport', 'Con Dao', 'Ba Ria-Vung Tau', 'Vietnam', 'VCS', 'VVCS', NOW(), NOW()),
('Tuy Hoa Airport', 'Tuy Hoa', 'Phu Yen', 'Vietnam', 'TBB', 'VVTH', NOW(), NOW()),

-- Sân bay miền Bắc
('Dien Bien Phu Airport', 'Dien Bien Phu', 'Dien Bien', 'Vietnam', 'DIN', 'VVDB', NOW(), NOW()),
('Na San Airport', 'Son La', 'Son La', 'Vietnam', 'SQH', 'VVNS', NOW(), NOW());


INSERT INTO airlines (name, iata_code, icao_code, call_sign, country, website, hotline, created_at, updated_at) VALUES
-- Hãng bay chính
('Vietnam Airlines', 'VN', 'HVN', 'Vietnam', 'Vietnam', 'https://vietnamairlines.com', '1900-1100', NOW(), NOW()),
('VietJet Air', 'VJ', 'VJC', 'Vietjet', 'Vietnam', 'https://vietjetair.com', '1900-1886', NOW(), NOW()),
('Bamboo Airways', 'QH', 'BAV', 'Bamboo', 'Vietnam', 'https://bambooairways.com', '1900-1166', NOW(), NOW()),
('Jetstar Pacific', 'BL', 'PIC', 'Pacific', 'Vietnam', 'https://jetstar.com/vn', '1900-1550', NOW(), NOW()),

-- Hãng bay khu vực và chuyên dụng
('VASCO (Vietnam Air Service Company)', 'VC', 'VAS', 'Vasco', 'Vietnam', 'https://vasco.com.vn', '1900-1177', NOW(), NOW()),
('Vietnam Air Services Company', 'VU', 'VUS', 'Vus Air', 'Vietnam', 'https://vus.com.vn', '1900-1188', NOW(), NOW()),
('Hai Au Aviation', 'HA', 'HAU', 'Hai Au', 'Vietnam', 'https://haiauaviation.com', '1900-1199', NOW(), NOW()),
('Sky Viet Aviation', 'SV', 'SVN', 'Sky Viet', 'Vietnam', 'https://skyviet.com.vn', '1900-1122', NOW(), NOW()),

-- Hãng bay cargo và dịch vụ
('Vietnam Cargo Airlines', 'VR', 'VCG', 'Viet Cargo', 'Vietnam', 'https://vietnamcargo.com.vn', '1900-1133', NOW(), NOW()),
('Air Mekong', 'MK', 'AMK', 'Air Mekong', 'Vietnam', 'https://airmekong.com.vn', '1900-1144', NOW(), NOW()),

-- Hãng bay thêm
('Pacific Airlines', 'PA', 'PAC', 'Pacific Air', 'Vietnam', 'https://pacificairlines.com.vn', '1900-1155', NOW(), NOW()),
('Star Aviation', 'SA', 'SVA', 'Star Air', 'Vietnam', 'https://staraviation.com.vn', '1900-1166', NOW(), NOW());


INSERT INTO airplanes (model, airline_id, registration_number, seat_capacity, created_at, updated_at) VALUES
-- Vietnam Airlines (ID: 1) - 20 máy bay
('Boeing 787-9', 1, 'VN-A861', 280, NOW(), NOW()),
('Boeing 787-9', 1, 'VN-A862', 280, NOW(), NOW()),
('Boeing 787-9', 1, 'VN-A863', 280, NOW(), NOW()),
('Boeing 787-9', 1, 'VN-A864', 280, NOW(), NOW()),
('Airbus A350-900', 1, 'VN-A891', 305, NOW(), NOW()),
('Airbus A350-900', 1, 'VN-A892', 305, NOW(), NOW()),
('Airbus A350-900', 1, 'VN-A893', 305, NOW(), NOW()),
('Airbus A350-900', 1, 'VN-A894', 305, NOW(), NOW()),
('Airbus A321', 1, 'VN-A621', 184, NOW(), NOW()),
('Airbus A321', 1, 'VN-A622', 184, NOW(), NOW()),
('Airbus A321', 1, 'VN-A623', 184, NOW(), NOW()),
('Airbus A321', 1, 'VN-A624', 184, NOW(), NOW()),
('Airbus A321', 1, 'VN-A625', 184, NOW(), NOW()),
('Boeing 737-800', 1, 'VN-A571', 162, NOW(), NOW()),
('Boeing 737-800', 1, 'VN-A572', 162, NOW(), NOW()),
('Boeing 737-800', 1, 'VN-A573', 162, NOW(), NOW()),
('Boeing 737-800', 1, 'VN-A574', 162, NOW(), NOW()),
('Airbus A320', 1, 'VN-A501', 174, NOW(), NOW()),
('Airbus A320', 1, 'VN-A502', 174, NOW(), NOW()),
('Airbus A320', 1, 'VN-A503', 174, NOW(), NOW()),

-- VietJet Air (ID: 2) - 15 máy bay
('Airbus A320', 2, 'VN-A671', 180, NOW(), NOW()),
('Airbus A320', 2, 'VN-A672', 180, NOW(), NOW()),
('Airbus A320', 2, 'VN-A673', 180, NOW(), NOW()),
('Airbus A320', 2, 'VN-A674', 180, NOW(), NOW()),
('Airbus A320', 2, 'VN-A675', 180, NOW(), NOW()),
('Airbus A321', 2, 'VN-A681', 230, NOW(), NOW()),
('Airbus A321', 2, 'VN-A682', 230, NOW(), NOW()),
('Airbus A321', 2, 'VN-A683', 230, NOW(), NOW()),
('Airbus A321neo', 2, 'VN-A684', 230, NOW(), NOW()),
('Airbus A321neo', 2, 'VN-A685', 230, NOW(), NOW()),
('Airbus A321neo', 2, 'VN-A686', 230, NOW(), NOW()),
('Airbus A321neo', 2, 'VN-A687', 230, NOW(), NOW()),
('Airbus A320neo', 2, 'VN-A688', 186, NOW(), NOW()),
('Airbus A320neo', 2, 'VN-A689', 186, NOW(), NOW()),
('Airbus A320neo', 2, 'VN-A690', 186, NOW(), NOW()),

-- Bamboo Airways (ID: 3) - 12 máy bay
('Boeing 787-9', 3, 'VN-A819', 294, NOW(), NOW()),
('Boeing 787-9', 3, 'VN-A820', 294, NOW(), NOW()),
('Boeing 787-9', 3, 'VN-A821', 294, NOW(), NOW()),
('Airbus A320', 3, 'VN-A589', 174, NOW(), NOW()),
('Airbus A320', 3, 'VN-A590', 174, NOW(), NOW()),
('Airbus A320', 3, 'VN-A591', 174, NOW(), NOW()),
('Airbus A321neo', 3, 'VN-A592', 203, NOW(), NOW()),
('Airbus A321neo', 3, 'VN-A593', 203, NOW(), NOW()),
('Airbus A321neo', 3, 'VN-A594', 203, NOW(), NOW()),
('Airbus A321neo', 3, 'VN-A595', 203, NOW(), NOW()),
('Embraer E190', 3, 'VN-A789', 97, NOW(), NOW()),
('Embraer E190', 3, 'VN-A790', 97, NOW(), NOW()),

-- Jetstar Pacific (ID: 4) - 8 máy bay
('Airbus A320', 4, 'VN-A561', 180, NOW(), NOW()),
('Airbus A320', 4, 'VN-A562', 180, NOW(), NOW()),
('Airbus A320', 4, 'VN-A563', 180, NOW(), NOW()),
('Airbus A320', 4, 'VN-A564', 180, NOW(), NOW()),
('Airbus A321', 4, 'VN-A565', 230, NOW(), NOW()),
('Airbus A321', 4, 'VN-A566', 230, NOW(), NOW()),
('Airbus A321', 4, 'VN-A567', 230, NOW(), NOW()),
('Airbus A321', 4, 'VN-A568', 230, NOW(), NOW()),

-- VASCO (ID: 5) - 5 máy bay
('ATR 72-500', 5, 'VN-B221', 72, NOW(), NOW()),
('ATR 72-500', 5, 'VN-B222', 72, NOW(), NOW()),
('ATR 72-600', 5, 'VN-B223', 78, NOW(), NOW()),
('DHC-6 Twin Otter', 5, 'VN-B301', 19, NOW(), NOW()),
('DHC-6 Twin Otter', 5, 'VN-B302', 19, NOW(), NOW());


INSERT INTO flights (flight_no, airline_id, airplane_id, departure_airport_id, arrival_airport_id, 
                    departure_time, arrival_time, duration_minutes, base_fare, status, current_price, 
                    is_full, available_seats, delay_minutes, flight_type, created_at, updated_at) VALUES

-- Chuyến bay ngày 25/6/2025 - Vietnam Airlines
('VN250601', 1, 1, 1, 2, '2025-06-25 06:00:00', '2025-06-25 08:15:00', 135, 1650000, 'SCHEDULED', 1650000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN250602', 1, 2, 2, 1, '2025-06-25 10:30:00', '2025-06-25 12:45:00', 135, 1650000, 'SCHEDULED', 1650000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN250603', 1, 3, 1, 2, '2025-06-25 14:00:00', '2025-06-25 16:15:00', 135, 1705000, 'SCHEDULED', 1705000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN250604', 1, 4, 2, 1, '2025-06-25 18:30:00', '2025-06-25 20:45:00', 135, 1705000, 'SCHEDULED', 1705000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN250605', 1, 9, 1, 3, '2025-06-25 08:00:00', '2025-06-25 09:20:00', 80, 1045000, 'SCHEDULED', 1045000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN250606', 1, 10, 3, 1, '2025-06-25 11:45:00', '2025-06-25 13:05:00', 80, 1045000, 'SCHEDULED', 1045000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN250607', 1, 11, 1, 4, '2025-06-25 07:30:00', '2025-06-25 09:15:00', 105, 1320000, 'SCHEDULED', 1320000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN250608', 1, 12, 4, 1, '2025-06-25 15:00:00', '2025-06-25 16:45:00', 105, 1320000, 'SCHEDULED', 1320000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN250609', 1, 13, 1, 5, '2025-06-25 12:15:00', '2025-06-25 14:30:00', 135, 1980000, 'SCHEDULED', 1980000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN250610', 1, 14, 5, 1, '2025-06-25 20:00:00', '2025-06-25 22:15:00', 135, 1980000, 'SCHEDULED', 1980000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN250611', 1, 15, 1, 8, '2025-06-25 09:15:00', '2025-06-25 10:35:00', 80, 1375000, 'SCHEDULED', 1375000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN250612', 1, 16, 8, 1, '2025-06-25 15:45:00', '2025-06-25 17:05:00', 80, 1375000, 'SCHEDULED', 1375000, false, 150, 0, 'DOMESTIC', NOW(), NOW()),
('VN250613', 1, 17, 2, 3, '2025-06-25 13:30:00', '2025-06-25 14:50:00', 80, 880000, 'SCHEDULED', 880000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN250614', 1, 18, 3, 2, '2025-06-25 17:15:00', '2025-06-25 18:35:00', 80, 880000, 'SCHEDULED', 880000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN250615', 1, 19, 1, 6, '2025-06-25 16:30:00', '2025-06-25 17:50:00', 80, 1540000, 'SCHEDULED', 1540000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('VN250616', 1, 20, 6, 1, '2025-06-25 19:15:00', '2025-06-25 20:35:00', 80, 1540000, 'SCHEDULED', 1540000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),

-- VietJet Air ngày 25/6/2025
('VJ250601', 2, 21, 2, 4, '2025-06-25 07:30:00', '2025-06-25 08:45:00', 75, 880000, 'SCHEDULED', 880000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250602', 2, 22, 4, 2, '2025-06-25 11:15:00', '2025-06-25 12:30:00', 75, 880000, 'SCHEDULED', 880000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250603', 2, 23, 1, 5, '2025-06-25 15:20:00', '2025-06-25 17:35:00', 135, 1430000, 'SCHEDULED', 1430000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250604', 2, 24, 5, 1, '2025-06-25 19:45:00', '2025-06-25 22:00:00', 135, 1430000, 'SCHEDULED', 1430000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250605', 2, 25, 2, 3, '2025-06-25 09:30:00', '2025-06-25 10:50:00', 80, 825000, 'SCHEDULED', 825000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250606', 2, 26, 3, 2, '2025-06-25 16:45:00', '2025-06-25 18:05:00', 80, 825000, 'SCHEDULED', 825000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250607', 2, 27, 1, 7, '2025-06-25 14:30:00', '2025-06-25 15:50:00', 80, 1320000, 'SCHEDULED', 1320000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250608', 2, 28, 7, 1, '2025-06-25 18:15:00', '2025-06-25 19:35:00', 80, 1320000, 'SCHEDULED', 1320000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250609', 2, 29, 2, 6, '2025-06-25 13:30:00', '2025-06-25 14:50:00', 80, 1320000, 'SCHEDULED', 1320000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250610', 2, 30, 6, 2, '2025-06-25 17:00:00', '2025-06-25 18:20:00', 80, 1320000, 'SCHEDULED', 1320000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250611', 2, 31, 1, 9, '2025-06-25 16:15:00', '2025-06-25 17:30:00', 75, 1265000, 'SCHEDULED', 1265000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ250612', 2, 32, 9, 1, '2025-06-25 19:30:00', '2025-06-25 20:45:00', 75, 1265000, 'SCHEDULED', 1265000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),

-- Bamboo Airways ngày 25/6/2025
('QH250601', 3, 36, 2, 3, '2025-06-25 08:45:00', '2025-06-25 10:05:00', 80, 990000, 'SCHEDULED', 990000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH250602', 3, 37, 3, 2, '2025-06-25 13:30:00', '2025-06-25 14:50:00', 80, 990000, 'SCHEDULED', 990000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH250603', 3, 38, 1, 4, '2025-06-25 10:15:00', '2025-06-25 12:00:00', 105, 1485000, 'SCHEDULED', 1485000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH250604', 3, 39, 4, 1, '2025-06-25 17:30:00', '2025-06-25 19:15:00', 105, 1485000, 'SCHEDULED', 1485000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH250605', 3, 40, 1, 8, '2025-06-25 11:45:00', '2025-06-25 13:05:00', 80, 1375000, 'SCHEDULED', 1375000, false, 195, 0, 'DOMESTIC', NOW(), NOW()),
('QH250606', 3, 41, 8, 1, '2025-06-25 16:30:00', '2025-06-25 17:50:00', 80, 1375000, 'SCHEDULED', 1375000, false, 200, 0, 'DOMESTIC', NOW(), NOW()),
('QH250607', 3, 42, 2, 5, '2025-06-25 12:00:00', '2025-06-25 14:15:00', 135, 1650000, 'SCHEDULED', 1650000, false, 185, 0, 'DOMESTIC', NOW(), NOW()),
('QH250608', 3, 46, 5, 2, '2025-06-25 18:45:00', '2025-06-25 21:00:00', 135, 1650000, 'SCHEDULED', 1650000, false, 90, 0, 'DOMESTIC', NOW(), NOW()),

-- Jetstar Pacific ngày 25/6/2025
('BL250601', 4, 44, 1, 6, '2025-06-25 12:00:00', '2025-06-25 13:20:00', 80, 1540000, 'SCHEDULED', 1540000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('BL250602', 4, 45, 6, 1, '2025-06-25 18:30:00', '2025-06-25 19:50:00', 80, 1540000, 'SCHEDULED', 1540000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('BL250603', 4, 46, 2, 7, '2025-06-25 14:15:00', '2025-06-25 15:35:00', 80, 1210000, 'SCHEDULED', 1210000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('BL250604', 4, 47, 7, 2, '2025-06-25 19:45:00', '2025-06-25 21:05:00', 80, 1210000, 'SCHEDULED', 1210000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('BL250605', 4, 48, 1, 10, '2025-06-25 15:30:00', '2025-06-25 16:50:00', 80, 1375000, 'SCHEDULED', 1375000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('BL250606', 4, 49, 10, 1, '2025-06-25 20:15:00', '2025-06-25 21:35:00', 80, 1375000, 'SCHEDULED', 1375000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),

-- VASCO ngày 25/6/2025
('VC250601', 5, 49, 1, 11, '2025-06-25 14:45:00', '2025-06-25 16:00:00', 75, 1980000, 'SCHEDULED', 1980000, false, 65, 0, 'DOMESTIC', NOW(), NOW()),
('VC250602', 5, 50, 11, 1, '2025-06-25 17:15:00', '2025-06-25 18:30:00', 75, 1980000, 'SCHEDULED', 1980000, false, 70, 0, 'DOMESTIC', NOW(), NOW()),
('VC250603', 5, 51, 1, 13, '2025-06-25 11:30:00', '2025-06-25 12:30:00', 60, 1760000, 'SCHEDULED', 1760000, false, 75, 0, 'DOMESTIC', NOW(), NOW()),
('VC250604', 5, 49, 13, 1, '2025-06-25 16:00:00', '2025-06-25 17:00:00', 60, 1760000, 'SCHEDULED', 1760000, false, 70, 0, 'DOMESTIC', NOW(), NOW());

INSERT INTO flights (flight_no, airline_id, airplane_id, departure_airport_id, arrival_airport_id, 
                    departure_time, arrival_time, duration_minutes, base_fare, status, current_price, 
                    is_full, available_seats, delay_minutes, flight_type, created_at, updated_at) VALUES

-- Vietnam Airlines ngày 26/6/2025
('VN260601', 1, 5, 1, 2, '2025-06-26 06:30:00', '2025-06-26 08:45:00', 135, 1650000, 'SCHEDULED', 1650000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN260602', 1, 6, 2, 1, '2025-06-26 11:00:00', '2025-06-26 13:15:00', 135, 1650000, 'SCHEDULED', 1650000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('VN260603', 1, 7, 1, 2, '2025-06-26 15:30:00', '2025-06-26 17:45:00', 135, 1705000, 'SCHEDULED', 1705000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN260604', 1, 8, 2, 1, '2025-06-26 19:15:00', '2025-06-26 21:30:00', 135, 1705000, 'SCHEDULED', 1705000, false, 300, 0, 'DOMESTIC', NOW(), NOW()),
('VN260605', 1, 1, 1, 3, '2025-06-26 08:30:00', '2025-06-26 09:50:00', 80, 1045000, 'SCHEDULED', 1045000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN260606', 1, 2, 3, 1, '2025-06-26 12:15:00', '2025-06-26 13:35:00', 80, 1045000, 'SCHEDULED', 1045000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN260607', 1, 3, 1, 4, '2025-06-26 08:00:00', '2025-06-26 09:45:00', 105, 1320000, 'SCHEDULED', 1320000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN260608', 1, 4, 4, 1, '2025-06-26 15:30:00', '2025-06-26 17:15:00', 105, 1320000, 'SCHEDULED', 1320000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN260609', 1, 9, 1, 5, '2025-06-26 12:45:00', '2025-06-26 15:00:00', 135, 1980000, 'SCHEDULED', 1980000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN260610', 1, 10, 5, 1, '2025-06-26 20:30:00', '2025-06-26 22:45:00', 135, 1980000, 'SCHEDULED', 1980000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN260611', 1, 11, 2, 8, '2025-06-26 09:45:00', '2025-06-26 11:05:00', 80, 1210000, 'SCHEDULED', 1210000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN260612', 1, 12, 8, 2, '2025-06-26 16:15:00', '2025-06-26 17:35:00', 80, 1210000, 'SCHEDULED', 1210000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN260613', 1, 13, 1, 7, '2025-06-26 10:30:00', '2025-06-26 11:50:00', 80, 1320000, 'SCHEDULED', 1320000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN260614', 1, 14, 7, 1, '2025-06-26 17:45:00', '2025-06-26 19:05:00', 80, 1320000, 'SCHEDULED', 1320000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN260615', 1, 15, 2, 6, '2025-06-26 14:00:00', '2025-06-26 15:20:00', 80, 1210000, 'SCHEDULED', 1210000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN260616', 1, 16, 6, 2, '2025-06-26 18:30:00', '2025-06-26 19:50:00', 80, 1210000, 'SCHEDULED', 1210000, false, 150, 0, 'DOMESTIC', NOW(), NOW()),

-- VietJet Air ngày 26/6/2025
('VJ260601', 2, 33, 2, 4, '2025-06-26 08:00:00', '2025-06-26 09:15:00', 75, 880000, 'SCHEDULED', 880000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260602', 2, 34, 4, 2, '2025-06-26 12:45:00', '2025-06-26 14:00:00', 75, 880000, 'SCHEDULED', 880000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260603', 2, 35, 1, 5, '2025-06-26 16:45:00', '2025-06-26 19:00:00', 135, 1430000, 'SCHEDULED', 1430000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260604', 2, 21, 5, 1, '2025-06-26 21:15:00', '2025-06-26 23:30:00', 135, 1430000, 'SCHEDULED', 1430000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260605', 2, 22, 2, 3, '2025-06-26 10:15:00', '2025-06-26 11:35:00', 80, 825000, 'SCHEDULED', 825000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260606', 2, 23, 3, 2, '2025-06-26 17:30:00', '2025-06-26 18:50:00', 80, 825000, 'SCHEDULED', 825000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260607', 2, 24, 1, 7, '2025-06-26 15:15:00', '2025-06-26 16:35:00', 80, 1320000, 'SCHEDULED', 1320000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260608', 2, 25, 7, 1, '2025-06-26 19:00:00', '2025-06-26 20:20:00', 80, 1320000, 'SCHEDULED', 1320000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260609', 2, 26, 1, 9, '2025-06-26 13:45:00', '2025-06-26 15:00:00', 75, 1265000, 'SCHEDULED', 1265000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260610', 2, 27, 9, 1, '2025-06-26 18:15:00', '2025-06-26 19:30:00', 75, 1265000, 'SCHEDULED', 1265000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260611', 2, 28, 2, 10, '2025-06-26 11:30:00', '2025-06-26 12:50:00', 80, 1100000, 'SCHEDULED', 1100000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ260612', 2, 29, 10, 2, '2025-06-26 16:45:00', '2025-06-26 18:05:00', 80, 1100000, 'SCHEDULED', 1100000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),

-- Bamboo Airways ngày 26/6/2025
('QH260601', 3, 39, 2, 3, '2025-06-26 10:15:00', '2025-06-26 11:35:00', 80, 990000, 'SCHEDULED', 990000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH260602', 3, 40, 3, 2, '2025-06-26 14:30:00', '2025-06-26 15:50:00', 80, 990000, 'SCHEDULED', 990000, false, 195, 0, 'DOMESTIC', NOW(), NOW()),
('QH260603', 3, 41, 1, 4, '2025-06-26 11:00:00', '2025-06-26 12:45:00', 105, 1485000, 'SCHEDULED', 1485000, false, 200, 0, 'DOMESTIC', NOW(), NOW()),
('QH260604', 3, 42, 4, 1, '2025-06-26 18:15:00', '2025-06-26 20:00:00', 105, 1485000, 'SCHEDULED', 1485000, false, 185, 0, 'DOMESTIC', NOW(), NOW()),
('QH260605', 3, 36, 1, 6, '2025-06-26 13:30:00', '2025-06-26 14:50:00', 80, 1540000, 'SCHEDULED', 1540000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH260606', 3, 37, 6, 1, '2025-06-26 17:15:00', '2025-06-26 18:35:00', 80, 1540000, 'SCHEDULED', 1540000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH260607', 3, 38, 2, 8, '2025-06-26 12:30:00', '2025-06-26 13:50:00', 80, 1100000, 'SCHEDULED', 1100000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH260608', 3, 46, 8, 2, '2025-06-26 16:00:00', '2025-06-26 17:20:00', 80, 1100000, 'SCHEDULED', 1100000, false, 90, 0, 'DOMESTIC', NOW(), NOW()),

-- Jetstar Pacific ngày 26/6/2025
('BL260601', 4, 47, 1, 6, '2025-06-26 12:30:00', '2025-06-26 13:50:00', 80, 1540000, 'SCHEDULED', 1540000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('BL260602', 4, 48, 6, 1, '2025-06-26 19:00:00', '2025-06-26 20:20:00', 80, 1540000, 'SCHEDULED', 1540000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('BL260603', 4, 44, 2, 7, '2025-06-26 14:45:00', '2025-06-26 16:05:00', 80, 1210000, 'SCHEDULED', 1210000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('BL260604', 4, 45, 7, 2, '2025-06-26 20:30:00', '2025-06-26 21:50:00', 80, 1210000, 'SCHEDULED', 1210000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('BL260605', 4, 46, 1, 11, '2025-06-26 16:15:00', '2025-06-26 17:35:00', 80, 1980000, 'SCHEDULED', 1980000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('BL260606', 4, 47, 11, 1, '2025-06-26 21:00:00', '2025-06-26 22:20:00', 80, 1980000, 'SCHEDULED', 1980000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),

-- VASCO ngày 26/6/2025
('VC260601', 5, 50, 1, 11, '2025-06-26 15:15:00', '2025-06-26 16:30:00', 75, 1980000, 'SCHEDULED', 1980000, false, 70, 0, 'DOMESTIC', NOW(), NOW()),
('VC260602', 5, 51, 11, 1, '2025-06-26 17:45:00', '2025-06-26 19:00:00', 75, 1980000, 'SCHEDULED', 1980000, false, 75, 0, 'DOMESTIC', NOW(), NOW()),
('VC260603', 5, 49, 1, 13, '2025-06-26 12:00:00', '2025-06-26 13:00:00', 60, 1760000, 'SCHEDULED', 1760000, false, 65, 0, 'DOMESTIC', NOW(), NOW()),
('VC260604', 5, 50, 13, 1, '2025-06-26 16:30:00', '2025-06-26 17:30:00', 60, 1760000, 'SCHEDULED', 1760000, false, 70, 0, 'DOMESTIC', NOW(), NOW());


INSERT INTO flights (flight_no, airline_id, airplane_id, departure_airport_id, arrival_airport_id, 
                    departure_time, arrival_time, duration_minutes, base_fare, status, current_price, 
                    is_full, available_seats, delay_minutes, flight_type, created_at, updated_at) VALUES

-- Vietnam Airlines ngày 27/6/2025 (Thứ 6 - giá tăng 20%)
('VN270601', 1, 17, 1, 2, '2025-06-27 06:00:00', '2025-06-27 08:15:00', 135, 1980000, 'SCHEDULED', 1980000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN270602', 1, 18, 2, 1, '2025-06-27 10:30:00', '2025-06-27 12:45:00', 135, 1980000, 'SCHEDULED', 1980000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN270603', 1, 19, 1, 2, '2025-06-27 14:00:00', '2025-06-27 16:15:00', 135, 2046000, 'SCHEDULED', 2046000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('VN270604', 1, 20, 2, 1, '2025-06-27 18:30:00', '2025-06-27 20:45:00', 135, 2046000, 'SCHEDULED', 2046000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VN270605', 1, 5, 1, 3, '2025-06-27 08:00:00', '2025-06-27 09:20:00', 80, 1254000, 'SCHEDULED', 1254000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN270606', 1, 6, 3, 1, '2025-06-27 11:45:00', '2025-06-27 13:05:00', 80, 1254000, 'SCHEDULED', 1254000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('VN270607', 1, 7, 1, 4, '2025-06-27 07:30:00', '2025-06-27 09:15:00', 105, 1584000, 'SCHEDULED', 1584000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN270608', 1, 8, 4, 1, '2025-06-27 15:00:00', '2025-06-27 16:45:00', 105, 1584000, 'SCHEDULED', 1584000, false, 300, 0, 'DOMESTIC', NOW(), NOW()),
('VN270609', 1, 1, 1, 5, '2025-06-27 12:15:00', '2025-06-27 14:30:00', 135, 2376000, 'SCHEDULED', 2376000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN270610', 1, 2, 5, 1, '2025-06-27 20:00:00', '2025-06-27 22:15:00', 135, 2376000, 'SCHEDULED', 2376000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN270611', 1, 3, 1, 8, '2025-06-27 09:15:00', '2025-06-27 10:35:00', 80, 1650000, 'SCHEDULED', 1650000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN270612', 1, 4, 8, 1, '2025-06-27 15:45:00', '2025-06-27 17:05:00', 80, 1650000, 'SCHEDULED', 1650000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN270613', 1, 9, 2, 3, '2025-06-27 13:30:00', '2025-06-27 14:50:00', 80, 1056000, 'SCHEDULED', 1056000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN270614', 1, 10, 3, 2, '2025-06-27 17:15:00', '2025-06-27 18:35:00', 80, 1056000, 'SCHEDULED', 1056000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN270615', 1, 11, 1, 6, '2025-06-27 16:30:00', '2025-06-27 17:50:00', 80, 1848000, 'SCHEDULED', 1848000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN270616', 1, 12, 6, 1, '2025-06-27 19:15:00', '2025-06-27 20:35:00', 80, 1848000, 'SCHEDULED', 1848000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),

-- VietJet Air ngày 27/6/2025 (Thứ 6 - giá tăng 20%)
('VJ270601', 2, 30, 2, 4, '2025-06-27 07:30:00', '2025-06-27 08:45:00', 75, 1056000, 'SCHEDULED', 1056000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270602', 2, 31, 4, 2, '2025-06-27 11:15:00', '2025-06-27 12:30:00', 75, 1056000, 'SCHEDULED', 1056000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270603', 2, 32, 1, 5, '2025-06-27 15:20:00', '2025-06-27 17:35:00', 135, 1716000, 'SCHEDULED', 1716000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270604', 2, 33, 5, 1, '2025-06-27 19:45:00', '2025-06-27 22:00:00', 135, 1716000, 'SCHEDULED', 1716000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270605', 2, 34, 2, 3, '2025-06-27 09:30:00', '2025-06-27 10:50:00', 80, 990000, 'SCHEDULED', 990000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270606', 2, 35, 3, 2, '2025-06-27 16:45:00', '2025-06-27 18:05:00', 80, 990000, 'SCHEDULED', 990000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270607', 2, 21, 1, 7, '2025-06-27 14:30:00', '2025-06-27 15:50:00', 80, 1584000, 'SCHEDULED', 1584000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270608', 2, 22, 7, 1, '2025-06-27 18:15:00', '2025-06-27 19:35:00', 80, 1584000, 'SCHEDULED', 1584000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270609', 2, 23, 2, 6, '2025-06-27 13:30:00', '2025-06-27 14:50:00', 80, 1584000, 'SCHEDULED', 1584000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270610', 2, 24, 6, 2, '2025-06-27 17:00:00', '2025-06-27 18:20:00', 80, 1584000, 'SCHEDULED', 1584000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270611', 2, 25, 1, 9, '2025-06-27 16:15:00', '2025-06-27 17:30:00', 75, 1518000, 'SCHEDULED', 1518000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('VJ270612', 2, 26, 9, 1, '2025-06-27 19:30:00', '2025-06-27 20:45:00', 75, 1518000, 'SCHEDULED', 1518000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),

-- Bamboo Airways ngày 27/6/2025 (Thứ 6 - giá tăng 20%)
('QH270601', 3, 43, 2, 3, '2025-06-27 08:45:00', '2025-06-27 10:05:00', 80, 1188000, 'SCHEDULED', 1188000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('QH270602', 3, 44, 3, 2, '2025-06-27 13:30:00', '2025-06-27 14:50:00', 80, 1188000, 'SCHEDULED', 1188000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('QH270603', 3, 45, 1, 4, '2025-06-27 10:15:00', '2025-06-27 12:00:00', 105, 1782000, 'SCHEDULED', 1782000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('QH270604', 3, 46, 4, 1, '2025-06-27 17:30:00', '2025-06-27 19:15:00', 105, 1782000, 'SCHEDULED', 1782000, false, 90, 0, 'DOMESTIC', NOW(), NOW()),
('QH270605', 3, 47, 1, 8, '2025-06-27 11:45:00', '2025-06-27 13:05:00', 80, 1650000, 'SCHEDULED', 1650000, false, 95, 0, 'DOMESTIC', NOW(), NOW()),
('QH270606', 3, 36, 8, 1, '2025-06-27 16:30:00', '2025-06-27 17:50:00', 80, 1650000, 'SCHEDULED', 1650000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH270607', 3, 37, 2, 5, '2025-06-27 12:00:00', '2025-06-27 14:15:00', 135, 1980000, 'SCHEDULED', 1980000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH270608', 3, 38, 5, 2, '2025-06-27 18:45:00', '2025-06-27 21:00:00', 135, 1980000, 'SCHEDULED', 1980000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),

-- Jetstar Pacific ngày 27/6/2025 (Thứ 6 - giá tăng 20%)
('BL270601', 4, 44, 1, 6, '2025-06-27 12:00:00', '2025-06-27 13:20:00', 80, 1848000, 'SCHEDULED', 1848000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('BL270602', 4, 45, 6, 1, '2025-06-27 18:30:00', '2025-06-27 19:50:00', 80, 1848000, 'SCHEDULED', 1848000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('BL270603', 4, 46, 2, 7, '2025-06-27 14:15:00', '2025-06-27 15:35:00', 80, 1452000, 'SCHEDULED', 1452000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('BL270604', 4, 47, 7, 2, '2025-06-27 19:45:00', '2025-06-27 21:05:00', 80, 1452000, 'SCHEDULED', 1452000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('BL270605', 4, 48, 1, 10, '2025-06-27 15:30:00', '2025-06-27 16:50:00', 80, 1650000, 'SCHEDULED', 1650000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('BL270606', 4, 44, 10, 1, '2025-06-27 20:15:00', '2025-06-27 21:35:00', 80, 1650000, 'SCHEDULED', 1650000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),

-- VASCO ngày 27/6/2025 (Thứ 6 - giá tăng 20%)
('VC270601', 5, 49, 1, 11, '2025-06-27 14:45:00', '2025-06-27 16:00:00', 75, 2376000, 'SCHEDULED', 2376000, false, 65, 0, 'DOMESTIC', NOW(), NOW()),
('VC270602', 5, 50, 11, 1, '2025-06-27 17:15:00', '2025-06-27 18:30:00', 75, 2376000, 'SCHEDULED', 2376000, false, 70, 0, 'DOMESTIC', NOW(), NOW()),
('VC270604', 5, 49, 13, 1, '2025-06-27 16:00:00', '2025-06-27 17:00:00', 60, 2112000, 'SCHEDULED', 2112000, false, 65, 0, 'DOMESTIC', NOW(), NOW());


INSERT INTO flights (flight_no, airline_id, airplane_id, departure_airport_id, arrival_airport_id, 
                    departure_time, arrival_time, duration_minutes, base_fare, status, current_price, 
                    is_full, available_seats, delay_minutes, flight_type, created_at, updated_at) VALUES

-- Vietnam Airlines ngày 28/6/2025 (Thứ 7 - giá tăng 30%)
('VN280601', 1, 13, 1, 2, '2025-06-28 07:00:00', '2025-06-28 09:15:00', 135, 2145000, 'SCHEDULED', 2145000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN280602', 1, 14, 2, 1, '2025-06-28 12:30:00', '2025-06-28 14:45:00', 135, 2145000, 'SCHEDULED', 2145000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN280603', 1, 15, 1, 2, '2025-06-28 16:00:00', '2025-06-28 18:15:00', 135, 2216500, 'SCHEDULED', 2216500, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN280604', 1, 16, 2, 1, '2025-06-28 19:30:00', '2025-06-28 21:45:00', 135, 2216500, 'SCHEDULED', 2216500, false, 150, 0, 'DOMESTIC', NOW(), NOW()),
('VN280605', 1, 17, 1, 3, '2025-06-28 08:30:00', '2025-06-28 09:50:00', 80, 1358500, 'SCHEDULED', 1358500, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN280606', 1, 18, 3, 1, '2025-06-28 12:15:00', '2025-06-28 13:35:00', 80, 1358500, 'SCHEDULED', 1358500, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN280607', 1, 19, 1, 4, '2025-06-28 08:00:00', '2025-06-28 09:45:00', 105, 1716000, 'SCHEDULED', 1716000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('VN280608', 1, 20, 4, 1, '2025-06-28 15:30:00', '2025-06-28 17:15:00', 105, 1716000, 'SCHEDULED', 1716000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VN280609', 1, 5, 1, 5, '2025-06-28 12:45:00', '2025-06-28 15:00:00', 135, 2574000, 'SCHEDULED', 2574000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN280610', 1, 6, 5, 1, '2025-06-28 20:30:00', '2025-06-28 22:45:00', 135, 2574000, 'SCHEDULED', 2574000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('VN280611', 1, 7, 2, 8, '2025-06-28 09:45:00', '2025-06-28 11:05:00', 80, 1573000, 'SCHEDULED', 1573000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN280612', 1, 8, 8, 2, '2025-06-28 16:15:00', '2025-06-28 17:35:00', 80, 1573000, 'SCHEDULED', 1573000, false, 300, 0, 'DOMESTIC', NOW(), NOW()),
('VN280613', 1, 1, 1, 7, '2025-06-28 10:30:00', '2025-06-28 11:50:00', 80, 1716000, 'SCHEDULED', 1716000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN280614', 1, 2, 7, 1, '2025-06-28 17:45:00', '2025-06-28 19:05:00', 80, 1716000, 'SCHEDULED', 1716000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN280615', 1, 3, 2, 6, '2025-06-28 14:00:00', '2025-06-28 15:20:00', 80, 1573000, 'SCHEDULED', 1573000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN280616', 1, 4, 6, 2, '2025-06-28 18:30:00', '2025-06-28 19:50:00', 80, 1573000, 'SCHEDULED', 1573000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),

-- VietJet Air ngày 28/6/2025 (Thứ 7 - giá tăng 30%)
('VJ280601', 2, 27, 2, 4, '2025-06-28 08:30:00', '2025-06-28 09:45:00', 75, 1144000, 'SCHEDULED', 1144000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280602', 2, 28, 4, 2, '2025-06-28 13:15:00', '2025-06-28 14:30:00', 75, 1144000, 'SCHEDULED', 1144000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280603', 2, 29, 1, 5, '2025-06-28 17:15:00', '2025-06-28 19:30:00', 135, 1859000, 'SCHEDULED', 1859000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280604', 2, 30, 5, 1, '2025-06-28 21:45:00', '2025-06-29 00:00:00', 135, 1859000, 'SCHEDULED', 1859000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280605', 2, 31, 2, 3, '2025-06-28 11:00:00', '2025-06-28 12:20:00', 80, 1072500, 'SCHEDULED', 1072500, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280606', 2, 32, 3, 2, '2025-06-28 14:45:00', '2025-06-28 16:05:00', 80, 1072500, 'SCHEDULED', 1072500, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280607', 2, 33, 1, 7, '2025-06-28 16:00:00', '2025-06-28 17:20:00', 80, 1716000, 'SCHEDULED', 1716000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280608', 2, 34, 7, 1, '2025-06-28 19:30:00', '2025-06-28 20:50:00', 80, 1716000, 'SCHEDULED', 1716000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280609', 2, 35, 1, 9, '2025-06-28 13:45:00', '2025-06-28 15:00:00', 75, 1644500, 'SCHEDULED', 1644500, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280610', 2, 21, 9, 1, '2025-06-28 18:15:00', '2025-06-28 19:30:00', 75, 1644500, 'SCHEDULED', 1644500, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280611', 2, 22, 2, 10, '2025-06-28 11:30:00', '2025-06-28 12:50:00', 80, 1430000, 'SCHEDULED', 1430000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ280612', 2, 23, 10, 2, '2025-06-28 16:45:00', '2025-06-28 18:05:00', 80, 1430000, 'SCHEDULED', 1430000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),

-- Bamboo Airways ngày 28/6/2025 (Thứ 7 - giá tăng 30%)
('QH280601', 3, 39, 2, 3, '2025-06-28 11:00:00', '2025-06-28 12:20:00', 80, 1287000, 'SCHEDULED', 1287000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH280602', 3, 40, 3, 2, '2025-06-28 14:45:00', '2025-06-28 16:05:00', 80, 1287000, 'SCHEDULED', 1287000, false, 195, 0, 'DOMESTIC', NOW(), NOW()),
('QH280603', 3, 41, 1, 4, '2025-06-28 09:30:00', '2025-06-28 11:15:00', 105, 1930500, 'SCHEDULED', 1930500, false, 200, 0, 'DOMESTIC', NOW(), NOW()),
('QH280604', 3, 42, 4, 1, '2025-06-28 15:45:00', '2025-06-28 17:30:00', 105, 1930500, 'SCHEDULED', 1930500, false, 185, 0, 'DOMESTIC', NOW(), NOW()),
('QH280605', 3, 43, 1, 6, '2025-06-28 13:30:00', '2025-06-28 14:50:00', 80, 2002000, 'SCHEDULED', 2002000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('QH280606', 3, 44, 6, 1, '2025-06-28 17:15:00', '2025-06-28 18:35:00', 80, 2002000, 'SCHEDULED', 2002000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('QH280607', 3, 45, 2, 8, '2025-06-28 12:30:00', '2025-06-28 13:50:00', 80, 1430000, 'SCHEDULED', 1430000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('QH280608', 3, 36, 8, 2, '2025-06-28 16:00:00', '2025-06-28 17:20:00', 80, 1430000, 'SCHEDULED', 1430000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),

-- Jetstar Pacific ngày 28/6/2025 (Thứ 7 - giá tăng 30%)
('BL280601', 4, 47, 1, 6, '2025-06-28 15:15:00', '2025-06-28 16:35:00', 80, 2002000, 'SCHEDULED', 2002000, false, 95, 0, 'DOMESTIC', NOW(), NOW()),
('BL280602', 4, 48, 6, 1, '2025-06-28 19:45:00', '2025-06-28 21:05:00', 80, 2002000, 'SCHEDULED', 2002000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('BL280603', 4, 44, 2, 7, '2025-06-28 14:30:00', '2025-06-28 15:50:00', 80, 1573000, 'SCHEDULED', 1573000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('BL280604', 4, 45, 7, 2, '2025-06-28 20:15:00', '2025-06-28 21:35:00', 80, 1573000, 'SCHEDULED', 1573000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('BL280605', 4, 46, 1, 11, '2025-06-28 16:45:00', '2025-06-28 18:05:00', 80, 2574000, 'SCHEDULED', 2574000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('BL280606', 4, 47, 11, 1, '2025-06-28 21:30:00', '2025-06-28 22:50:00', 80, 2574000, 'SCHEDULED', 2574000, false, 95, 0, 'DOMESTIC', NOW(), NOW()),

-- VASCO ngày 28/6/2025 (Thứ 7 - giá tăng 30%)
('VC280601', 5, 50, 1, 11, '2025-06-28 15:30:00', '2025-06-28 16:45:00', 75, 2574000, 'SCHEDULED', 2574000, false, 70, 0, 'DOMESTIC', NOW(), NOW()),
('VC280602', 5, 51, 11, 1, '2025-06-28 18:00:00', '2025-06-28 19:15:00', 75, 2574000, 'SCHEDULED', 2574000, false, 75, 0, 'DOMESTIC', NOW(), NOW()),
('VC280603', 5, 49, 1, 13, '2025-06-28 12:30:00', '2025-06-28 13:30:00', 60, 2288000, 'SCHEDULED', 2288000, false, 65, 0, 'DOMESTIC', NOW(), NOW()),
('VC280604', 5, 50, 13, 1, '2025-06-28 17:00:00', '2025-06-28 18:00:00', 60, 2288000, 'SCHEDULED', 2288000, false, 70, 0, 'DOMESTIC', NOW(), NOW());


INSERT INTO flights (flight_no, airline_id, airplane_id, departure_airport_id, arrival_airport_id, 
                    departure_time, arrival_time, duration_minutes, base_fare, status, current_price, 
                    is_full, available_seats, delay_minutes, flight_type, created_at, updated_at) VALUES

-- Vietnam Airlines ngày 29/6/2025 (Chủ nhật - giá tăng 30%)
('VN290601', 1, 9, 1, 2, '2025-06-29 06:15:00', '2025-06-29 08:30:00', 135, 2145000, 'SCHEDULED', 2145000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN290602', 1, 10, 2, 1, '2025-06-29 10:45:00', '2025-06-29 13:00:00', 135, 2145000, 'SCHEDULED', 2145000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN290603', 1, 11, 1, 2, '2025-06-29 14:30:00', '2025-06-29 16:45:00', 135, 2216500, 'SCHEDULED', 2216500, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN290604', 1, 12, 2, 1, '2025-06-29 18:00:00', '2025-06-29 20:15:00', 135, 2216500, 'SCHEDULED', 2216500, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN290605', 1, 13, 1, 3, '2025-06-29 09:00:00', '2025-06-29 10:20:00', 80, 1358500, 'SCHEDULED', 1358500, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN290606', 1, 14, 3, 1, '2025-06-29 13:15:00', '2025-06-29 14:35:00', 80, 1358500, 'SCHEDULED', 1358500, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN290607', 1, 15, 1, 4, '2025-06-29 08:30:00', '2025-06-29 10:15:00', 105, 1716000, 'SCHEDULED', 1716000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN290608', 1, 16, 4, 1, '2025-06-29 16:00:00', '2025-06-29 17:45:00', 105, 1716000, 'SCHEDULED', 1716000, false, 150, 0, 'DOMESTIC', NOW(), NOW()),
('VN290609', 1, 17, 1, 5, '2025-06-29 13:15:00', '2025-06-29 15:30:00', 135, 2574000, 'SCHEDULED', 2574000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN290610', 1, 18, 5, 1, '2025-06-29 21:00:00', '2025-06-29 23:15:00', 135, 2574000, 'SCHEDULED', 2574000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN290611', 1, 19, 2, 8, '2025-06-29 10:15:00', '2025-06-29 11:35:00', 80, 1573000, 'SCHEDULED', 1573000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('VN290612', 1, 20, 8, 2, '2025-06-29 16:45:00', '2025-06-29 18:05:00', 80, 1573000, 'SCHEDULED', 1573000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VN290613', 1, 5, 1, 7, '2025-06-29 11:00:00', '2025-06-29 12:20:00', 80, 1716000, 'SCHEDULED', 1716000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN290614', 1, 6, 7, 1, '2025-06-29 18:30:00', '2025-06-29 19:50:00', 80, 1716000, 'SCHEDULED', 1716000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('VN290615', 1, 7, 2, 6, '2025-06-29 14:45:00', '2025-06-29 16:05:00', 80, 1573000, 'SCHEDULED', 1573000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN290616', 1, 8, 6, 2, '2025-06-29 19:15:00', '2025-06-29 20:35:00', 80, 1573000, 'SCHEDULED', 1573000, false, 300, 0, 'DOMESTIC', NOW(), NOW()),

-- VietJet Air ngày 29/6/2025 (Chủ nhật - giá tăng 30%)
('VJ290601', 2, 24, 2, 4, '2025-06-29 07:45:00', '2025-06-29 09:00:00', 75, 1144000, 'SCHEDULED', 1144000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290602', 2, 25, 4, 2, '2025-06-29 12:00:00', '2025-06-29 13:15:00', 75, 1144000, 'SCHEDULED', 1144000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290603', 2, 26, 1, 5, '2025-06-29 16:00:00', '2025-06-29 18:15:00', 135, 1859000, 'SCHEDULED', 1859000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290604', 2, 27, 5, 1, '2025-06-29 20:30:00', '2025-06-29 22:45:00', 135, 1859000, 'SCHEDULED', 1859000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290605', 2, 28, 2, 3, '2025-06-29 09:30:00', '2025-06-29 10:50:00', 80, 1072500, 'SCHEDULED', 1072500, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290606', 2, 29, 3, 2, '2025-06-29 13:45:00', '2025-06-29 15:05:00', 80, 1072500, 'SCHEDULED', 1072500, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290607', 2, 30, 1, 7, '2025-06-29 15:30:00', '2025-06-29 16:50:00', 80, 1716000, 'SCHEDULED', 1716000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290608', 2, 31, 7, 1, '2025-06-29 19:00:00', '2025-06-29 20:20:00', 80, 1716000, 'SCHEDULED', 1716000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290609', 2, 32, 1, 9, '2025-06-29 14:15:00', '2025-06-29 15:30:00', 75, 1644500, 'SCHEDULED', 1644500, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290610', 2, 33, 9, 1, '2025-06-29 17:45:00', '2025-06-29 19:00:00', 75, 1644500, 'SCHEDULED', 1644500, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290611', 2, 34, 2, 10, '2025-06-29 12:30:00', '2025-06-29 13:50:00', 80, 1430000, 'SCHEDULED', 1430000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ290612', 2, 35, 10, 2, '2025-06-29 17:15:00', '2025-06-29 18:35:00', 80, 1430000, 'SCHEDULED', 1430000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),

-- Bamboo Airways ngày 29/6/2025 (Chủ nhật - giá tăng 30%)
('QH290601', 3, 37, 2, 3, '2025-06-29 09:30:00', '2025-06-29 10:50:00', 80, 1287000, 'SCHEDULED', 1287000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH290602', 3, 38, 3, 2, '2025-06-29 13:45:00', '2025-06-29 15:05:00', 80, 1287000, 'SCHEDULED', 1287000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH290603', 3, 39, 1, 4, '2025-06-29 10:00:00', '2025-06-29 11:45:00', 105, 1930500, 'SCHEDULED', 1930500, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH290604', 3, 40, 4, 1, '2025-06-29 16:30:00', '2025-06-29 18:15:00', 105, 1930500, 'SCHEDULED', 1930500, false, 195, 0, 'DOMESTIC', NOW(), NOW()),
('QH290605', 3, 41, 1, 6, '2025-06-29 14:00:00', '2025-06-29 15:20:00', 80, 2002000, 'SCHEDULED', 2002000, false, 200, 0, 'DOMESTIC', NOW(), NOW()),
('QH290606', 3, 42, 6, 1, '2025-06-29 17:45:00', '2025-06-29 19:05:00', 80, 2002000, 'SCHEDULED', 2002000, false, 185, 0, 'DOMESTIC', NOW(), NOW()),
('QH290607', 3, 43, 2, 8, '2025-06-29 13:00:00', '2025-06-29 14:20:00', 80, 1430000, 'SCHEDULED', 1430000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('QH290608', 3, 46, 8, 2, '2025-06-29 16:15:00', '2025-06-29 17:35:00', 80, 1430000, 'SCHEDULED', 1430000, false, 90, 0, 'DOMESTIC', NOW(), NOW()),

-- Jetstar Pacific ngày 29/6/2025 (Chủ nhật - giá tăng 30%)
('BL290601', 4, 45, 1, 6, '2025-06-29 15:45:00', '2025-06-29 17:05:00', 80, 2002000, 'SCHEDULED', 2002000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('BL290602', 4, 46, 6, 1, '2025-06-29 20:15:00', '2025-06-29 21:35:00', 80, 2002000, 'SCHEDULED', 2002000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('BL290603', 4, 47, 2, 7, '2025-06-29 15:00:00', '2025-06-29 16:20:00', 80, 1573000, 'SCHEDULED', 1573000, false, 95, 0, 'DOMESTIC', NOW(), NOW()),
('BL290604', 4, 48, 7, 2, '2025-06-29 20:45:00', '2025-06-29 22:05:00', 80, 1573000, 'SCHEDULED', 1573000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('BL290605', 4, 44, 1, 11, '2025-06-29 17:15:00', '2025-06-29 18:35:00', 80, 2574000, 'SCHEDULED', 2574000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('BL290606', 4, 45, 11, 1, '2025-06-29 22:00:00', '2025-06-29 23:20:00', 80, 2574000, 'SCHEDULED', 2574000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),

-- VASCO ngày 29/6/2025 (Chủ nhật - giá tăng 30%)
('VC290601', 5, 51, 1, 11, '2025-06-29 16:00:00', '2025-06-29 17:15:00', 75, 2574000, 'SCHEDULED', 2574000, false, 75, 0, 'DOMESTIC', NOW(), NOW()),
('VC290602', 5, 49, 11, 1, '2025-06-29 18:30:00', '2025-06-29 19:45:00', 75, 2574000, 'SCHEDULED', 2574000, false, 65, 0, 'DOMESTIC', NOW(), NOW()),
('VC290603', 5, 50, 1, 13, '2025-06-29 13:00:00', '2025-06-29 14:00:00', 60, 2288000, 'SCHEDULED', 2288000, false, 70, 0, 'DOMESTIC', NOW(), NOW()),
('VC290604', 5, 51, 13, 1, '2025-06-29 17:30:00', '2025-06-29 18:30:00', 60, 2288000, 'SCHEDULED', 2288000, false, 75, 0, 'DOMESTIC', NOW(), NOW());


INSERT INTO flights (flight_no, airline_id, airplane_id, departure_airport_id, arrival_airport_id, 
                    departure_time, arrival_time, duration_minutes, base_fare, status, current_price, 
                    is_full, available_seats, delay_minutes, flight_type, created_at, updated_at) VALUES

-- Vietnam Airlines ngày 30/6/2025 (Thứ hai - giá bình thường)
('VN300601', 1, 1, 1, 2, '2025-06-30 06:45:00', '2025-06-30 09:00:00', 135, 1650000, 'SCHEDULED', 1650000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN300602', 1, 2, 2, 1, '2025-06-30 11:30:00', '2025-06-30 13:45:00', 135, 1650000, 'SCHEDULED', 1650000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN300603', 1, 3, 1, 2, '2025-06-30 15:45:00', '2025-06-30 18:00:00', 135, 1705000, 'SCHEDULED', 1705000, false, 275, 0, 'DOMESTIC', NOW(), NOW()),
('VN300604', 1, 4, 2, 1, '2025-06-30 19:15:00', '2025-06-30 21:30:00', 135, 1705000, 'SCHEDULED', 1705000, false, 270, 0, 'DOMESTIC', NOW(), NOW()),
('VN300605', 1, 5, 1, 3, '2025-06-30 09:00:00', '2025-06-30 10:20:00', 80, 1045000, 'SCHEDULED', 1045000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN300606', 1, 6, 3, 1, '2025-06-30 13:45:00', '2025-06-30 15:05:00', 80, 1045000, 'SCHEDULED', 1045000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('VN300607', 1, 7, 1, 4, '2025-06-30 09:30:00', '2025-06-30 11:15:00', 105, 1320000, 'SCHEDULED', 1320000, false, 295, 0, 'DOMESTIC', NOW(), NOW()),
('VN300608', 1, 8, 4, 1, '2025-06-30 16:30:00', '2025-06-30 18:15:00', 105, 1320000, 'SCHEDULED', 1320000, false, 300, 0, 'DOMESTIC', NOW(), NOW()),
('VN300609', 1, 9, 1, 5, '2025-06-30 13:45:00', '2025-06-30 16:00:00', 135, 1980000, 'SCHEDULED', 1980000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN300610', 1, 10, 5, 1, '2025-06-30 21:30:00', '2025-06-30 23:45:00', 135, 1980000, 'SCHEDULED', 1980000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN300611', 1, 11, 2, 8, '2025-06-30 10:45:00', '2025-06-30 12:05:00', 80, 1210000, 'SCHEDULED', 1210000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN300612', 1, 12, 8, 2, '2025-06-30 17:15:00', '2025-06-30 18:35:00', 80, 1210000, 'SCHEDULED', 1210000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VN300613', 1, 13, 1, 7, '2025-06-30 11:30:00', '2025-06-30 12:50:00', 80, 1320000, 'SCHEDULED', 1320000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VN300614', 1, 14, 7, 1, '2025-06-30 19:00:00', '2025-06-30 20:20:00', 80, 1320000, 'SCHEDULED', 1320000, false, 160, 0, 'DOMESTIC', NOW(), NOW()),
('VN300615', 1, 15, 2, 6, '2025-06-30 15:15:00', '2025-06-30 16:35:00', 80, 1210000, 'SCHEDULED', 1210000, false, 155, 0, 'DOMESTIC', NOW(), NOW()),
('VN300616', 1, 16, 6, 2, '2025-06-30 19:45:00', '2025-06-30 21:05:00', 80, 1210000, 'SCHEDULED', 1210000, false, 150, 0, 'DOMESTIC', NOW(), NOW()),

-- VietJet Air ngày 30/6/2025 (Thứ hai - giá bình thường)
('VJ300601', 2, 21, 2, 4, '2025-06-30 08:15:00', '2025-06-30 09:30:00', 75, 880000, 'SCHEDULED', 880000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300602', 2, 22, 4, 2, '2025-06-30 13:00:00', '2025-06-30 14:15:00', 75, 880000, 'SCHEDULED', 880000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300603', 2, 23, 1, 5, '2025-06-30 17:30:00', '2025-06-30 19:45:00', 135, 1430000, 'SCHEDULED', 1430000, false, 175, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300604', 2, 24, 5, 1, '2025-06-30 22:00:00', '2025-07-01 00:15:00', 135, 1430000, 'SCHEDULED', 1430000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300605', 2, 25, 2, 3, '2025-06-30 10:00:00', '2025-06-30 11:20:00', 80, 825000, 'SCHEDULED', 825000, false, 225, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300606', 2, 26, 3, 2, '2025-06-30 15:00:00', '2025-06-30 16:20:00', 80, 825000, 'SCHEDULED', 825000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300607', 2, 27, 1, 7, '2025-06-30 16:15:00', '2025-06-30 17:35:00', 80, 1320000, 'SCHEDULED', 1320000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300608', 2, 28, 7, 1, '2025-06-30 19:30:00', '2025-06-30 20:50:00', 80, 1320000, 'SCHEDULED', 1320000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300609', 2, 29, 1, 9, '2025-06-30 14:45:00', '2025-06-30 16:00:00', 75, 1265000, 'SCHEDULED', 1265000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300610', 2, 30, 9, 1, '2025-06-30 18:15:00', '2025-06-30 19:30:00', 75, 1265000, 'SCHEDULED', 1265000, false, 220, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300611', 2, 31, 2, 10, '2025-06-30 13:00:00', '2025-06-30 14:20:00', 80, 1100000, 'SCHEDULED', 1100000, false, 215, 0, 'DOMESTIC', NOW(), NOW()),
('VJ300612', 2, 32, 10, 2, '2025-06-30 17:45:00', '2025-06-30 19:05:00', 80, 1100000, 'SCHEDULED', 1100000, false, 180, 0, 'DOMESTIC', NOW(), NOW()),

-- Bamboo Airways ngày 30/6/2025 (Thứ hai - giá bình thường)
('QH300601', 3, 44, 2, 3, '2025-06-30 10:30:00', '2025-06-30 11:50:00', 80, 990000, 'SCHEDULED', 990000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('QH300602', 3, 45, 3, 2, '2025-06-30 15:00:00', '2025-06-30 16:20:00', 80, 990000, 'SCHEDULED', 990000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('QH300603', 3, 46, 1, 4, '2025-06-30 11:30:00', '2025-06-30 13:15:00', 105, 1485000, 'SCHEDULED', 1485000, false, 90, 0, 'DOMESTIC', NOW(), NOW()),
('QH300604', 3, 47, 4, 1, '2025-06-30 18:45:00', '2025-06-30 20:30:00', 105, 1485000, 'SCHEDULED', 1485000, false, 95, 0, 'DOMESTIC', NOW(), NOW()),
('QH300605', 3, 36, 1, 6, '2025-06-30 14:30:00', '2025-06-30 15:50:00', 80, 1540000, 'SCHEDULED', 1540000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH300606', 3, 37, 6, 1, '2025-06-30 18:15:00', '2025-06-30 19:35:00', 80, 1540000, 'SCHEDULED', 1540000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),
('QH300607', 3, 38, 2, 8, '2025-06-30 13:30:00', '2025-06-30 14:50:00', 80, 1100000, 'SCHEDULED', 1100000, false, 165, 0, 'DOMESTIC', NOW(), NOW()),
('QH300608', 3, 39, 8, 2, '2025-06-30 16:45:00', '2025-06-30 18:05:00', 80, 1100000, 'SCHEDULED', 1100000, false, 170, 0, 'DOMESTIC', NOW(), NOW()),

-- Jetstar Pacific ngày 30/6/2025 (Thứ hai - giá bình thường)
('BL300601', 4, 40, 1, 6, '2025-06-30 16:15:00', '2025-06-30 17:35:00', 80, 1540000, 'SCHEDULED', 1540000, false, 195, 0, 'DOMESTIC', NOW(), NOW()),
('BL300602', 4, 41, 6, 1, '2025-06-30 20:45:00', '2025-06-30 22:05:00', 80, 1540000, 'SCHEDULED', 1540000, false, 200, 0, 'DOMESTIC', NOW(), NOW()),
('BL300603', 4, 42, 2, 7, '2025-06-30 15:30:00', '2025-06-30 16:50:00', 80, 1210000, 'SCHEDULED', 1210000, false, 185, 0, 'DOMESTIC', NOW(), NOW()),
('BL300604', 4, 43, 7, 2, '2025-06-30 21:15:00', '2025-06-30 22:35:00', 80, 1210000, 'SCHEDULED', 1210000, false, 290, 0, 'DOMESTIC', NOW(), NOW()),
('BL300605', 4, 44, 1, 11, '2025-06-30 17:45:00', '2025-06-30 19:05:00', 80, 1980000, 'SCHEDULED', 1980000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),
('BL300606', 4, 45, 11, 1, '2025-06-30 22:30:00', '2025-06-30 23:50:00', 80, 1980000, 'SCHEDULED', 1980000, false, 285, 0, 'DOMESTIC', NOW(), NOW()),

-- VASCO ngày 30/6/2025 (Thứ hai - giá bình thường)
('VC300601', 5, 49, 1, 11, '2025-06-30 16:30:00', '2025-06-30 17:45:00', 75, 1980000, 'SCHEDULED', 1980000, false, 65, 0, 'DOMESTIC', NOW(), NOW()),
('VC300602', 5, 50, 11, 1, '2025-06-30 19:00:00', '2025-06-30 20:15:00', 75, 1980000, 'SCHEDULED', 1980000, false, 70, 0, 'DOMESTIC', NOW(), NOW()),
('VC300603', 5, 51, 1, 13, '2025-06-30 13:30:00', '2025-06-30 14:30:00', 60, 1760000, 'SCHEDULED', 1760000, false, 75, 0, 'DOMESTIC', NOW(), NOW()),
('VC300604', 5, 49, 13, 1, '2025-06-30 18:00:00', '2025-06-30 19:00:00', 60, 1760000, 'SCHEDULED', 1760000, false, 65, 0, 'DOMESTIC', NOW(), NOW());



INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(1, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '3A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '3B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '3C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '3D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '4A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '4B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '4C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '4D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '5A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '5B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '5C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '5D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '6A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '6B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '6C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '6D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '7A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '7B', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '7C', 'BUSINESS', 'AISLE', 'AVAILABLE', 15000000.0, NOW(), NOW()),
(1, '7D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 15000000.0, NOW(), NOW()),

(1, '8A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '8B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '8C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '8D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '8E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '8F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '9A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '9B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '9C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '9D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '9E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '9F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '10A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '10B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '10C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '10D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '10E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '10F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '11A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '11B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '11C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '11D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '11E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '11F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '12A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '12B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '12C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '12D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '12E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '12F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '13A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '13B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '13C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '13D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),
(1, '13E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3500000.0, NOW(), NOW()),

(1, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '14B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '14D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '14E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '14F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '15B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '15D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '15E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '15F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),

(1, '16A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '16B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '16C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '16D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '16E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '16F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '17A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '17B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '17C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '17D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '17E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '17F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '18A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '18B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '18C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '18D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '18E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '18F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '19A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '19B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '19C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '19D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '19E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '19F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '20A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '20B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '20C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '20D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '20E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '20F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '21A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '21B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '21C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '21D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '21E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '21F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '22A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '22B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '22C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '22D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '22E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '22F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '23A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '23B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '23C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '23D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '23E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '23F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '24A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '24B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '24C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '24D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '24E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '24F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '25A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '25B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '25C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '25D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(1, '25E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1200000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 2, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 1;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 3, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 1;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 4, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 1;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(5, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '3A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '3B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '3C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '3D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '4A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '4B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '4C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '4D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '5A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '5B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '5C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '5D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '6A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '6B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '6C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '6D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '7A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '7B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '7C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '7D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '8A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '8B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '8C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '8D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '9A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '9B', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '9C', 'BUSINESS', 'AISLE', 'AVAILABLE', 18000000.0, NOW(), NOW()),
(5, '9D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 18000000.0, NOW(), NOW()),

(5, '10A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '10B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '10C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '10D', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '10E', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '10F', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '10G', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),

(5, '11A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '11B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '11C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '11D', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '11E', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '11F', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '11G', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12D', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12E', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12F', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '12G', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13D', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13E', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13F', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 4000000.0, NOW(), NOW()),
(5, '13G', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 4000000.0, NOW(), NOW()),

(5, '18A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '18B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '18C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '18D', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '18E', 'ECONOMY', 'AISLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '18F', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '18G', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19D', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19E', 'ECONOMY', 'AISLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19F', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '19G', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20D', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20E', 'ECONOMY', 'AISLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20F', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1400000.0, NOW(), NOW()),
(5, '20G', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1400000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 6, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 5;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 7, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 5;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 8, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 5;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(9, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '3A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '3B', 'BUSINESS', 'AISLE', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '3C', 'BUSINESS', 'AISLE', 'AVAILABLE', 8000000.0, NOW(), NOW()),
(9, '3D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 8000000.0, NOW(), NOW()),

(9, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),

(9, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1000000.0, NOW(), NOW()),
(9, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1000000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 10, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 9;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 11, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 9;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 12, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 9;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 13, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 9;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(14, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '3A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '3B', 'BUSINESS', 'AISLE', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '3C', 'BUSINESS', 'AISLE', 'AVAILABLE', 7000000.0, NOW(), NOW()),
(14, '3D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 7000000.0, NOW(), NOW()),

(14, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),

(14, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 900000.0, NOW(), NOW()),
(14, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 900000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 15, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 14;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 16, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 14;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 17, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 14;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(18, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 6000000.0, NOW(), NOW()),
(18, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 6000000.0, NOW(), NOW()),

(18, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '3B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '3D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '3E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '3F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),

-- Tiếp tục các hàng Economy (mẫu 10 hàng)
(18, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 800000.0, NOW(), NOW()),
(18, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 800000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 19, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 18;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 20, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 18;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(21, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '1B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '1C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '1D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '1E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '1F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '2B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '2C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '2D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '2E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '2F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '3B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '3D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '3E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '3F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),

(21, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '14B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '14D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '14E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '14F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '15B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '15D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '15E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '15F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '16A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '16B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '16C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '16D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '16E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '16F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '17A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '17B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '17C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '17D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '17E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '17F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '18A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '18B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '18C', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '18D', 'ECONOMY', 'AISLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '18E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 700000.0, NOW(), NOW()),
(21, '18F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 700000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 22, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 23, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 24, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 25, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(26, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '1B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '1C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '1D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '1E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '1F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),

(26, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '2B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '2C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '2D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '2E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '2F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '3B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '3D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '3E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '3F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '14B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '14D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '14E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '14F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '15B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '15D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '15E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '15F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '16A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '16B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '16C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '16D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '16E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '16F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '17A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '17B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '17C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '17D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '17E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '17F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '18A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '18B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '18C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '18D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '18E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '18F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '19A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '19B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '19C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '19D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '19E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '19F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '20A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '20B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '20C', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '20D', 'ECONOMY', 'AISLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '20E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 750000.0, NOW(), NOW()),
(26, '20F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 750000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 27, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 28, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 29, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 30, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 31, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(32, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '1B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '1C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '1D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '1E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '1F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),

(32, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '2B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '2C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '2D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '2E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '2F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '3B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '3D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '3E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '3F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '14B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '14D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '14E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '14F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '15B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '15D', 'ECONOMY', 'AISLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '15E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 720000.0, NOW(), NOW()),
(32, '15F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 720000.0, NOW(), NOW());


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 33, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 32;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 34, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 32;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 35, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 32;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(36, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '3A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '3B', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '3C', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '3D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '4A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '4B', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '4C', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '4D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '5A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '5B', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '5C', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '5D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '6A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '6B', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '6C', 'BUSINESS', 'AISLE', 'AVAILABLE', 16000000.0, NOW(), NOW()),
(36, '6D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 16000000.0, NOW(), NOW()),

(36, '7A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '7B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '7C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '7D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '7E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '7F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),

(36, '8A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '8B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '8C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '8D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '8E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '8F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '9A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '9B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '9C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '9D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '9E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '9F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '10A', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '10B', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '10C', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '10D', 'PREMIUM_ECONOMY', 'AISLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '10E', 'PREMIUM_ECONOMY', 'MIDDLE', 'AVAILABLE', 3800000.0, NOW(), NOW()),
(36, '10F', 'PREMIUM_ECONOMY', 'WINDOW', 'AVAILABLE', 3800000.0, NOW(), NOW()),

(36, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '14B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '14D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '14E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '14F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '15B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '15D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '15E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '15F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '16A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '16B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '16C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '16D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '16E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '16F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '17A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '17B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '17C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '17D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '17E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '17F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '18A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '18B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '18C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '18D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '18E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '18F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '19A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '19B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '19C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '19D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '19E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '19F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '20A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '20B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '20C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '20D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '20E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '20F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '21A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '21B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '21C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '21D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '21E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '21F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '22A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '22B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '22C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '22D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '22E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '22F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '23A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '23B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '23C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '23D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '23E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '23F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '24A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '24B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '24C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '24D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '24E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '24F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '25A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '25B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '25C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '25D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '25E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '25F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '26A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '26B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '26C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '26D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '26E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '26F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '27A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '27B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '27C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '27D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '27E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1300000.0, NOW(), NOW()),
(36, '27F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1300000.0, NOW(), NOW());


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 37, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 36;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 38, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 36;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 39, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 18;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 40, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 18;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 41, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 18;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(42, '1A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '1B', 'BUSINESS', 'AISLE', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '1C', 'BUSINESS', 'AISLE', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '1D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '2A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '2B', 'BUSINESS', 'AISLE', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '2C', 'BUSINESS', 'AISLE', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '2D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '3A', 'BUSINESS', 'WINDOW', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '3B', 'BUSINESS', 'AISLE', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '3C', 'BUSINESS', 'AISLE', 'AVAILABLE', 9000000.0, NOW(), NOW()),
(42, '3D', 'BUSINESS', 'WINDOW', 'AVAILABLE', 9000000.0, NOW(), NOW()),

(42, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '4B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '4D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '4E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '4F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),

(42, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '5B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '5D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '5E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '5F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '6B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '6D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '6E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '6F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '7B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '7D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '7E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '7F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '8B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '8D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '8E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '8F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '9B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '9D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '9E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '9F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '10B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '10D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '10E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '10F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '11B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '11D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '11E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '11F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '12B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '12D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '12E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '12F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '13B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '13D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '13E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '13F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '14B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '14D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '14E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '14F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '15B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '15D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '15E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '15F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '16A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '16B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '16C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '16D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '16E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '16F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '17A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '17B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '17C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '17D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '17E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '17F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '18A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '18B', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '18C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '18D', 'ECONOMY', 'AISLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '18E', 'ECONOMY', 'MIDDLE', 'AVAILABLE', 1100000.0, NOW(), NOW()),
(42, '18F', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1100000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 43, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 42;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 44, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 42;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 45, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 42;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(46, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '1B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '1C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '1D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '2B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '2C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '2D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),

(46, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '3B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '3D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '4B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '4D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '5B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '5D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '6B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '6D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '7B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '7D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '8B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '8D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '9B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '9D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '10B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '10D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '11B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '11D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '12B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '12D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '13B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '13D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '14A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '14B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '14C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '14D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '15A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '15B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '15C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '15D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '16A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '16B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '16C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '16D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '17A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '17B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '17C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1200000.0, NOW(), NOW()),
(46, '17D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1200000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 47, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 46;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 48, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 49, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 50, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 51, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 21;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 52, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 53, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 54, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 55, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 26;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(56, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '1B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '1C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '1D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '2B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '2C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '2D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),

(56, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '3B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '3D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '4B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '4D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '5B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '5D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '6B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '6D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '7B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '7D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '8B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '8D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '9B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '9D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '10B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '10D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '11B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '11D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '12B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1500000.0, NOW(), NOW()),
(56, '12D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1500000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 57, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 56;


INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES

(58, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '1B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '1C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '1D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),

(58, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '2B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '2C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '2D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '3B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '3C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '3D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '4B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '4C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '4D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '5B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '5C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '5D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '6B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '6C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '6D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '7B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '7C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '7D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '8B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '8C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '8D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '9B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '9C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '9D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '10B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '10C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '10D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '11A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '11B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '11C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '11D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '12A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '12B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '12C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '12D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '13A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '13B', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '13C', 'ECONOMY', 'AISLE', 'AVAILABLE', 1600000.0, NOW(), NOW()),
(58, '13D', 'ECONOMY', 'WINDOW', 'AVAILABLE', 1600000.0, NOW(), NOW());



INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) VALUES
(59, '1A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '1B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '2A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '2B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '3A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '3B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '4A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '4B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '5A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '5B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '6A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '6B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '7A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '7B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '8A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '8B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '9A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '9B', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW()),
(59, '10A', 'ECONOMY', 'WINDOW', 'AVAILABLE', 2000000.0, NOW(), NOW());

INSERT INTO seats (airplane_id, seat_number, seat_type, seat_position, status, price, created_at, updated_at) 
SELECT 60, seat_number, seat_type, seat_position, status, price, NOW(), NOW() 
FROM seats WHERE airplane_id = 59;
