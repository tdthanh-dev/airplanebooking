# Airplane Booking System

Hệ thống đặt vé máy bay online hiện đại, cho phép người dùng tìm kiếm, đặt vé và quản lý chuyến bay một cách dễ dàng và tiện lợi.

## Công nghệ sử dụng

- **Backend**: Java, Spring Boot, Spring Security, Spring Data JPA
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Token)
- **Documentation**: Swagger / OpenAPI
- **Build Tool**: Maven

## Cài đặt và chạy dự án

### Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- Maven 3.8 hoặc cao hơn
- MySQL 8.0 hoặc cao hơn

### Các bước cài đặt

1. Clone repository
```bash
git clone https://github.com/tdthanh-dev/airplanebooking.git
cd airplanebooking
```

2. Cấu hình cơ sở dữ liệu
- Tạo database MySQL mới với tên `airplanebooking`
- Cập nhật thông tin kết nối database trong file `src/main/resources/application.properties`

3. Build và chạy ứng dụng
```bash
mvn clean install
mvn spring-boot:run
```

Ứng dụng sẽ khởi chạy tại: http://localhost:8080

## Cấu trúc dự án

Dự án được tổ chức theo mô hình nhiều lớp (layered architecture):

- **Model**: Định nghĩa các entity và quan hệ giữa chúng
- **DTO**: Các đối tượng truyền dữ liệu giữa client và server
- **Repository**: Giao diện truy xuất dữ liệu từ cơ sở dữ liệu
- **Service**: Lớp xử lý nghiệp vụ
- **Controller**: Cung cấp các API endpoints
- **Security**: Cấu hình bảo mật và xác thực
- **Configuration**: Cấu hình cho ứng dụng
- **Exception**: Xử lý ngoại lệ
- **Util**: Các tiện ích chung

## API Endpoints chính

Ứng dụng cung cấp các API RESTful cho các tính năng sau:

- **Authentication**: Đăng ký, đăng nhập
- **Airlines**: Quản lý thông tin hãng hàng không
- **Airports**: Quản lý thông tin sân bay
- **Airplanes**: Quản lý thông tin máy bay
- **Flights**: Tìm kiếm và quản lý chuyến bay
- **Seats**: Quản lý ghế ngồi trên máy bay
- **Bookings**: Đặt vé và quản lý đơn hàng
- **Tickets**: Quản lý vé máy bay
- **Payments**: Xử lý thanh toán

Tài liệu API chi tiết có thể truy cập tại: http://localhost:8080/swagger-ui.html sau khi khởi chạy ứng dụng.

## Database Schema

Hệ thống sử dụng các bảng chính sau:
- User: Thông tin người dùng
- Airline: Thông tin hãng hàng không
- Airport: Thông tin sân bay
- Airplane: Thông tin máy bay
- Flight: Thông tin chuyến bay
- Seat: Thông tin ghế ngồi
- Booking: Thông tin đặt vé
- Passenger: Thông tin hành khách
- Ticket: Thông tin vé
- PaymentMethod: Phương thức thanh toán
- PaymentTransaction: Giao dịch thanh toán

## Liên hệ & Đóng góp

Dự án được phát triển bởi Trịnh Đình Thành - tdthanh.dev2025@gmail.com

Đóng góp và phản hồi luôn được chào đón! 