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

## Các tính năng chính

- **Xác thực và ủy quyền**: Đăng ký, đăng nhập, quản lý phiên người dùng
- **Quản lý tài khoản người dùng**: Cập nhật thông tin, theo dõi lịch sử đặt vé
- **Hệ thống điểm thưởng**: Tích lũy và sử dụng điểm thưởng cho các đặt vé
- **Tìm kiếm và đặt chuyến bay**: Tìm kiếm đa điều kiện, giá cả động, theo dõi trạng thái
- **Quản lý đặt vé**: Đặt vé, cập nhật, hủy và theo dõi trạng thái
- **Thanh toán trực tuyến**: Xử lý các giao dịch thanh toán an toàn
- **Quản lý hãng, sân bay và máy bay**: Thông tin chi tiết về các đối tượng trong hệ thống hàng không
- **Theo dõi chậm trễ chuyến bay**: Cập nhật thông tin về các chuyến bay bị trễ
- **Ưu đãi và mã khuyến mãi**: Áp dụng mã khuyến mãi cho đặt vé

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
- **Users**: Quản lý thông tin người dùng và điểm thưởng

Tài liệu API chi tiết có thể truy cập tại: http://localhost:8080/swagger-ui.html sau khi khởi chạy ứng dụng.

## Database Schema

Hệ thống sử dụng các bảng chính sau:
- User: Thông tin người dùng, điểm thưởng và tùy chọn ưu tiên
- Airline: Thông tin hãng hàng không
- Airport: Thông tin sân bay
- Airplane: Thông tin máy bay
- Flight: Thông tin chuyến bay, bao gồm giá hiện tại, loại chuyến bay và thông tin chậm trễ
- Seat: Thông tin ghế ngồi
- Booking: Thông tin đặt vé, nguồn đặt vé và mã khuyến mãi
- Passenger: Thông tin hành khách
- Ticket: Thông tin vé
- PaymentMethod: Phương thức thanh toán
- PaymentTransaction: Giao dịch thanh toán

## Liên hệ & Đóng góp

Dự án được phát triển bởi Trịnh Đình Thành - tdthanh.dev2025@gmail.com

Đóng góp và phản hồi luôn được chào đón! 

## Tài liệu API chi tiết

### Authentication API

#### Đăng ký tài khoản
- **Endpoint**: `POST /api/v1/auth/register`
- **Request**:
  ```json
  {
    "firstName": "Nguyen",
    "lastName": "Van A",
    "username": "nguyenvana",
    "email": "nguyenvana@example.com",
    "password": "password123",
    "phone": "0912345678"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Nguyen",
    "lastName": "Van A",
    "username": "nguyenvana",
    "email": "nguyenvana@example.com",
    "phone": "0912345678",
    "role": "USER",
    "loyaltyPoints": 0,
    "lastSearchedRoute": null,
    "preferredAirportId": null,
    "preferredSeatClass": null,
    "preferredAirlineId": null
  }
  ```

#### Đăng nhập
- **Endpoint**: `POST /api/v1/auth/login`
- **Request**:
  ```json
  {
    "username": "nguyenvana",
    "password": "password123"
  }
  ```
- **Response**:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9...",
    "role": "USER",
    "status": "ACTIVE",
    "isActive": true,
    "lastLogin": "2023-04-15T10:30:45",
    "loyaltyPoints": 0,
    "lastSearchedRoute": null,
    "preferredAirportId": null,
    "preferredSeatClass": null,
    "preferredAirlineId": null
  }
  ```

### User API

#### Cập nhật điểm thưởng
- **Endpoint**: `PUT /api/v1/users/{id}/loyalty-points`
- **Request Parameters**:
  - `points`: Số điểm thưởng cần thêm vào tài khoản
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Nguyen",
    "lastName": "Van A",
    "username": "nguyenvana",
    "email": "nguyenvana@example.com",
    "phone": "0912345678",
    "role": "USER",
    "loyaltyPoints": 100,
    "lastSearchedRoute": null,
    "preferredAirportId": null,
    "preferredSeatClass": null,
    "preferredAirlineId": null
  }
  ```

#### Cập nhật tuyến đường tìm kiếm gần đây
- **Endpoint**: `PUT /api/v1/users/{id}/last-searched-route`
- **Request Parameters**:
  - `route`: Tuyến đường tìm kiếm gần đây (ví dụ: "HAN-SGN")
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Nguyen",
    "lastName": "Van A",
    "username": "nguyenvana",
    "email": "nguyenvana@example.com",
    "phone": "0912345678",
    "role": "USER",
    "loyaltyPoints": 100,
    "lastSearchedRoute": "HAN-SGN",
    "preferredAirportId": null,
    "preferredSeatClass": null,
    "preferredAirlineId": null
  }
  ```

#### Cập nhật tùy chọn ưu tiên
- **Endpoint**: `PUT /api/v1/users/{id}/preferences`
- **Request Parameters**:
  - `airportId`: ID của sân bay ưu tiên
  - `seatClass`: Loại ghế ưu tiên (ECONOMY, BUSINESS, FIRST)
  - `airlineId`: ID của hãng hàng không ưu tiên
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Nguyen",
    "lastName": "Van A",
    "username": "nguyenvana",
    "email": "nguyenvana@example.com",
    "phone": "0912345678",
    "role": "USER",
    "loyaltyPoints": 100,
    "lastSearchedRoute": "HAN-SGN",
    "preferredAirportId": 1,
    "preferredSeatClass": "BUSINESS",
    "preferredAirlineId": 1
  }
  ```

### Airlines API

#### Tạo mới hãng hàng không
- **Endpoint**: `POST /api/v1/airlines/`
- **Request**:
  ```json
  {
    "name": "Vietnam Airlines",
    "iataCode": "VN",
    "icaoCode": "HVN",
    "callSign": "VIETNAM AIRLINES",
    "country": "Vietnam",
    "website": "https://www.vietnamairlines.com",
    "hotline": "1900 1100"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Vietnam Airlines",
    "iataCode": "VN",
    "icaoCode": "HVN",
    "callSign": "VIETNAM AIRLINES",
    "country": "Vietnam",
    "website": "https://www.vietnamairlines.com",
    "hotline": "1900 1100",
    "createdAt": "2023-04-15T10:30:45",
    "updatedAt": "2023-04-15T10:30:45"
  }
  ```

#### Lấy tất cả hãng hàng không
- **Endpoint**: `GET /api/v1/airlines/`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Vietnam Airlines",
      "iataCode": "VN",
      "icaoCode": "HVN",
      "callSign": "VIETNAM AIRLINES",
      "country": "Vietnam",
      "website": "https://www.vietnamairlines.com",
      "hotline": "1900 1100",
      "createdAt": "2023-04-15T10:30:45",
      "updatedAt": "2023-04-15T10:30:45"
    },
    {
      "id": 2,
      "name": "Vietjet Air",
      "iataCode": "VJ",
      "icaoCode": "VJC",
      "callSign": "VIETJET",
      "country": "Vietnam",
      "website": "https://www.vietjetair.com",
      "hotline": "1900 1886",
      "createdAt": "2023-04-15T11:20:30",
      "updatedAt": "2023-04-15T11:20:30"
    }
  ]
  ```

#### Lấy thông tin hãng hàng không theo ID
- **Endpoint**: `GET /api/v1/airlines/{id}`
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Vietnam Airlines",
    "iataCode": "VN",
    "icaoCode": "HVN",
    "callSign": "VIETNAM AIRLINES",
    "country": "Vietnam",
    "website": "https://www.vietnamairlines.com",
    "hotline": "1900 1100",
    "createdAt": "2023-04-15T10:30:45",
    "updatedAt": "2023-04-15T10:30:45"
  }
  ```

#### Lấy thông tin hãng hàng không theo mã
- **Endpoint**: `GET /api/v1/airlines/code?code=VN`
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Vietnam Airlines",
    "iataCode": "VN",
    "icaoCode": "HVN",
    "callSign": "VIETNAM AIRLINES",
    "country": "Vietnam",
    "website": "https://www.vietnamairlines.com",
    "hotline": "1900 1100",
    "createdAt": "2023-04-15T10:30:45",
    "updatedAt": "2023-04-15T10:30:45"
  }
  ```

#### Cập nhật thông tin hãng hàng không
- **Endpoint**: `PUT /api/v1/airlines/{id}`
- **Request**:
  ```json
  {
    "name": "Vietnam Airlines",
    "iataCode": "VN",
    "icaoCode": "HVN",
    "callSign": "VIETNAM AIRLINES",
    "country": "Vietnam",
    "website": "https://www.vietnamairlines.com",
    "hotline": "1900 1111"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Vietnam Airlines",
    "iataCode": "VN",
    "icaoCode": "HVN",
    "callSign": "VIETNAM AIRLINES",
    "country": "Vietnam",
    "website": "https://www.vietnamairlines.com",
    "hotline": "1900 1111",
    "createdAt": "2023-04-15T10:30:45",
    "updatedAt": "2023-04-15T14:20:15"
  }
  ```

#### Xóa hãng hàng không
- **Endpoint**: `DELETE /api/v1/airlines/{id}`
- **Response**:
  ```json
  "Airline deleted successfully"
  ```

### Airports API

#### Tạo mới sân bay
- **Endpoint**: `POST /api/v1/airports/`
- **Request**:
  ```json
  {
    "name": "Tân Sơn Nhất International Airport",
    "city": "Ho Chi Minh City",
    "state": "",
    "country": "Vietnam",
    "iataCode": "SGN",
    "icaoCode": "VVTS"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Tân Sơn Nhất International Airport",
    "city": "Ho Chi Minh City",
    "state": "",
    "country": "Vietnam",
    "iataCode": "SGN",
    "icaoCode": "VVTS",
    "createdAt": "2023-04-15T10:40:25",
    "updatedAt": "2023-04-15T10:40:25"
  }
  ```

#### Lấy tất cả sân bay
- **Endpoint**: `GET /api/v1/airports/`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Tân Sơn Nhất International Airport",
      "city": "Ho Chi Minh City",
      "state": "",
      "country": "Vietnam",
      "iataCode": "SGN",
      "icaoCode": "VVTS",
      "createdAt": "2023-04-15T10:40:25",
      "updatedAt": "2023-04-15T10:40:25"
    },
    {
      "id": 2,
      "name": "Nội Bài International Airport",
      "city": "Hanoi",
      "state": "",
      "country": "Vietnam",
      "iataCode": "HAN",
      "icaoCode": "VVNB",
      "createdAt": "2023-04-15T10:45:10",
      "updatedAt": "2023-04-15T10:45:10"
    }
  ]
  ```

#### Lấy thông tin sân bay theo ID
- **Endpoint**: `GET /api/v1/airports/{id}`
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Tân Sơn Nhất International Airport",
    "city": "Ho Chi Minh City",
    "state": "",
    "country": "Vietnam",
    "iataCode": "SGN",
    "icaoCode": "VVTS",
    "createdAt": "2023-04-15T10:40:25",
    "updatedAt": "2023-04-15T10:40:25"
  }
  ```

#### Lấy thông tin sân bay theo mã
- **Endpoint**: `GET /api/v1/airports/code?code=SGN`
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Tân Sơn Nhất International Airport",
    "city": "Ho Chi Minh City",
    "state": "",
    "country": "Vietnam",
    "iataCode": "SGN",
    "icaoCode": "VVTS",
    "createdAt": "2023-04-15T10:40:25",
    "updatedAt": "2023-04-15T10:40:25"
  }
  ```

### Airplanes API

#### Tạo mới máy bay
- **Endpoint**: `POST /api/v1/airplanes/`
- **Request**:
  ```json
  {
    "model": "Boeing 787-9",
    "airlineId": 1,
    "registrationNumber": "VN-A861",
    "seatCapacity": 274
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "model": "Boeing 787-9",
    "airline": {
      "id": 1,
      "name": "Vietnam Airlines",
      "iataCode": "VN",
      "icaoCode": "HVN",
      "callSign": "VIETNAM AIRLINES",
      "country": "Vietnam",
      "website": "https://www.vietnamairlines.com",
      "hotline": "1900 1100"
    },
    "registrationNumber": "VN-A861",
    "seatCapacity": 274,
    "createdAt": "2023-04-15T11:20:35",
    "updatedAt": "2023-04-15T11:20:35"
  }
  ```

#### Lấy tất cả máy bay
- **Endpoint**: `GET /api/v1/airplanes/`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "model": "Boeing 787-9",
      "airline": {
        "id": 1,
        "name": "Vietnam Airlines",
        "iataCode": "VN",
        "icaoCode": "HVN"
      },
      "registrationNumber": "VN-A861",
      "seatCapacity": 274,
      "createdAt": "2023-04-15T11:20:35",
      "updatedAt": "2023-04-15T11:20:35"
    },
    {
      "id": the2,
      "model": "Airbus A321",
      "airline": {
        "id": 2,
        "name": "Vietjet Air",
        "iataCode": "VJ",
        "icaoCode": "VJC"
      },
      "registrationNumber": "VN-A651",
      "seatCapacity": 230,
      "createdAt": "2023-04-15T11:22:15",
      "updatedAt": "2023-04-15T11:22:15"
    }
  ]
  ```

### Flights API

#### Tạo mới chuyến bay
- **Endpoint**: `POST /api/v1/flights/`
- **Request**:
  ```json
  {
    "flightNo": "VN302",
    "airlineCode": "VN",
    "airplaneId": 1,
    "departureAirportCode": "HAN",
    "arrivalAirportCode": "SGN",
    "departureTime": "2023-05-01T06:30:00",
    "arrivalTime": "2023-05-01T08:20:00",
    "baseFare": 1500000,
    "currentPrice": 1500000,
    "status": "SCHEDULED",
    "isFull": false,
    "delayMinutes": 0,
    "flightType": "DIRECT"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "flightNumber": "VN302",
    "departureAirport": {
      "id": 2,
      "name": "Nội Bài International Airport",
      "city": "Hanoi",
      "country": "Vietnam",
      "iataCode": "HAN"
    },
    "arrivalAirport": {
      "id": 1,
      "name": "Tân Sơn Nhất International Airport",
      "city": "Ho Chi Minh City",
      "country": "Vietnam",
      "iataCode": "SGN"
    },
    "airline": {
      "id": 1,
      "name": "Vietnam Airlines",
      "iataCode": "VN"
    },
    "airplane": {
      "id": 1,
      "model": "Boeing 787-9",
      "registrationNumber": "VN-A861"
    },
    "departureTime": "2023-05-01T06:30:00",
    "arrivalTime": "2023-05-01T08:20:00",
    "basePrice": 1500000,
    "currentPrice": 1500000,
    "status": "SCHEDULED",
    "isFull": false,
    "delayMinutes": 0,
    "flightType": "DIRECT",
    "availableSeats": 274,
    "createdAt": "2023-04-15T12:10:20",
    "updatedAt": "2023-04-15T12:10:20"
  }
  ```

#### Tìm kiếm chuyến bay
- **Endpoint**: `GET /api/v1/flights/search?departureAirport=HAN&arrivalAirport=SGN&departureTime=2023-05-01T00:00:00`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "flightNumber": "VN302",
      "departureAirport": {
        "id": 2,
        "name": "Nội Bài International Airport",
        "city": "Hanoi",
        "country": "Vietnam",
        "iataCode": "HAN"
      },
      "arrivalAirport": {
        "id": 1,
        "name": "Tân Sơn Nhất International Airport",
        "city": "Ho Chi Minh City",
        "country": "Vietnam",
        "iataCode": "SGN"
      },
      "airline": {
        "id": 1,
        "name": "Vietnam Airlines",
        "iataCode": "VN"
      },
      "airplane": {
        "id": 1,
        "model": "Boeing 787-9",
        "registrationNumber": "VN-A861"
      },
      "departureTime": "2023-05-01T06:30:00",
      "arrivalTime": "2023-05-01T08:20:00",
      "basePrice": 1500000,
      "status": "SCHEDULED",
      "availableSeats": 274,
      "createdAt": "2023-04-15T12:10:20",
      "updatedAt": "2023-04-15T12:10:20"
    }
  ]
  ```

#### Cập nhật giá hiện tại của chuyến bay
- **Endpoint**: `PUT /api/v1/flights/{id}/current-price`
- **Request Parameters**:
  - `currentPrice`: Giá hiện tại của chuyến bay
- **Response**: Trả về thông tin chuyến bay đã được cập nhật

#### Cập nhật trạng thái đầy chỗ của chuyến bay
- **Endpoint**: `PUT /api/v1/flights/{id}/is-full`
- **Request Parameters**:
  - `isFull`: Trạng thái đầy chỗ của chuyến bay (true/false)
- **Response**: Trả về thông tin chuyến bay đã được cập nhật

#### Cập nhật thông tin chậm trễ
- **Endpoint**: `PUT /api/v1/flights/{id}/delay`
- **Request Parameters**:
  - `delayMinutes`: Số phút chậm trễ
- **Response**: Trả về thông tin chuyến bay đã được cập nhật

#### Cập nhật loại chuyến bay
- **Endpoint**: `PUT /api/v1/flights/{id}/flight-type`
- **Request Parameters**:
  - `flightType`: Loại chuyến bay (DIRECT, CONNECTING, NON_STOP)
- **Response**: Trả về thông tin chuyến bay đã được cập nhật

### Bookings API

#### Tạo đặt vé
- **Endpoint**: `POST /api/v1/bookings/`
- **Request**:
  ```json
  {
    "userId": 1,
    "flightId": 1,
    "totalPrice": 3000000,
    "passengerCount": 2,
    "tripType": "ONE_WAY",
    "status": "PENDING",
    "bookingSource": "WEB",
    "promotionCode": "SUMMER2023"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "bookingReference": "BK12345AB",
    "user": {
      "id": 1,
      "firstName": "Nguyen",
      "lastName": "Van A",
      "username": "nguyenvana",
      "email": "nguyenvana@example.com",
      "phone": "0912345678",
      "role": "USER"
    },
    "flight": {
      "id": 1,
      "flightNumber": "VN302",
      "departureAirport": {
        "id": 2,
        "name": "Nội Bài International Airport",
        "city": "Hanoi",
        "iataCode": "HAN"
      },
      "arrivalAirport": {
        "id": 1,
        "name": "Tân Sơn Nhất International Airport",
        "city": "Ho Chi Minh City",
        "iataCode": "SGN"
      },
      "departureTime": "2023-05-01T06:30:00",
      "arrivalTime": "2023-05-01T08:20:00"
    },
    "status": "PENDING",
    "totalAmount": 3000000,
    "passengers": [],
    "bookingDate": "2023-04-15",
    "bookingSource": "WEB",
    "promotionCode": "SUMMER2023",
    "cancellationReason": null,
    "createdAt": "2023-04-15T14:30:45",
    "updatedAt": "2023-04-15T14:30:45"
  }
  ```

#### Cập nhật nguồn đặt vé
- **Endpoint**: `PUT /api/v1/bookings/{id}/booking-source`
- **Request Parameters**:
  - `bookingSource`: Nguồn đặt vé (WEB, MOBILE, AGENT, COUNTER)
- **Response**: Trả về thông tin đặt vé đã được cập nhật

#### Cập nhật mã khuyến mãi
- **Endpoint**: `PUT /api/v1/bookings/{id}/promotion-code`
- **Request Parameters**:
  - `promotionCode`: Mã khuyến mãi
- **Response**: Trả về thông tin đặt vé đã được cập nhật

#### Cập nhật lý do hủy
- **Endpoint**: `PUT /api/v1/bookings/{id}/cancellation-reason`
- **Request Parameters**:
  - `cancellationReason`: Lý do hủy đặt vé
- **Response**: Trả về thông tin đặt vé đã được cập nhật

#### Lấy đặt vé theo trạng thái
- **Endpoint**: `GET /api/v1/bookings/status/{status}`
- **Response**: Danh sách các đặt vé với trạng thái cụ thể

#### Lấy đặt vé theo chuyến bay
- **Endpoint**: `GET /api/v1/bookings/flight/{flightId}`
- **Response**: Danh sách các đặt vé cho chuyến bay cụ thể

### Tickets API

#### Tạo vé máy bay
- **Endpoint**: `POST /api/v1/tickets/`
- **Request**:
  ```json
  {
    "bookingId": 1,
    "flightId": 1,
    "passengerId": 1,
    "seatId": 10,
    "ticketPrice": 1500000,
    "ticketClass": "ECONOMY",
    "ticketType": "ADULT"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "ticketNumber": "VN302-12345",
    "booking": {
      "id": 1,
      "bookingReference": "BK12345AB",
      "status": "PENDING"
    },
    "flight": {
      "id": 1,
      "flightNumber": "VN302",
      "departureAirport": {
        "iataCode": "HAN"
      },
      "arrivalAirport": {
        "iataCode": "SGN"
      },
      "departureTime": "2023-05-01T06:30:00",
      "arrivalTime": "2023-05-01T08:20:00"
    },
    "passenger": {
      "id": 1,
      "firstName": "Nguyen",
      "lastName": "Van A",
      "passportNumber": "P12345678"
    },
    "seat": {
      "id": 10,
      "seatNumber": "12A",
      "seatClass": "ECONOMY"
    },
    "status": "CONFIRMED",
    "price": 1500000,
    "issueDate": "2023-04-15T15:10:25",
    "createdAt": "2023-04-15T15:10:25",
    "updatedAt": "2023-04-15T15:10:25"
  }
  ```

#### Lấy vé theo mã vé
- **Endpoint**: `GET /api/v1/tickets/number/{ticketNumber}`
- **Response**:
  ```json
  {
    "id": 1,
    "ticketNumber": "VN302-12345",
    "booking": {
      "id": 1,
      "bookingReference": "BK12345AB",
      "status": "PENDING"
    },
    "flight": {
      "id": 1,
      "flightNumber": "VN302",
      "departureAirport": {
        "iataCode": "HAN"
      },
      "arrivalAirport": {
        "iataCode": "SGN"
      },
      "departureTime": "2023-05-01T06:30:00",
      "arrivalTime": "2023-05-01T08:20:00"
    },
    "passenger": {
      "id": 1,
      "firstName": "Nguyen",
      "lastName": "Van A",
      "passportNumber": "P12345678"
    },
    "seat": {
      "id": 10,
      "seatNumber": "12A",
      "seatClass": "ECONOMY"
    },
    "status": "CONFIRMED",
    "price": 1500000,
    "issueDate": "2023-04-15T15:10:25",
    "createdAt": "2023-04-15T15:10:25",
    "updatedAt": "2023-04-15T15:10:25"
  }
  ```

#### Huỷ vé
- **Endpoint**: `POST /api/v1/tickets/{id}/cancel`
- **Response**:
  ```json
  "Ticket cancelled successfully"
  ```

### Seats API

#### Lấy tất cả ghế của chuyến bay
- **Endpoint**: `GET /api/v1/seats/flight/{flightId}`
- **Response**:
  ```json
  [
    {
      "id": 10,
      "seatNumber": "12A",
      "airplane": {
        "id": 1,
        "model": "Boeing 787-9",
        "registrationNumber": "VN-A861"
      },
      "seatClass": "ECONOMY",
      "status": "AVAILABLE",
      "extraPrice": 0,
      "createdAt": "2023-04-15T12:30:45",
      "updatedAt": "2023-04-15T12:30:45"
    },
    {
      "id": 11,
      "seatNumber": "12B",
      "airplane": {
        "id": 1,
        "model": "Boeing 787-9",
        "registrationNumber": "VN-A861"
      },
      "seatClass": "ECONOMY",
      "status": "AVAILABLE",
      "extraPrice": 0,
      "createdAt": "2023-04-15T12:30:45",
      "updatedAt": "2023-04-15T12:30:45"
    }
  ]
  ```

#### Lấy ghế còn trống của chuyến bay
- **Endpoint**: `GET /api/v1/seats/available/flight/{flightId}`
- **Response**:
  ```json
  [
    {
      "id": 10,
      "seatNumber": "12A",
      "airplane": {
        "id": 1,
        "model": "Boeing 787-9",
        "registrationNumber": "VN-A861"
      },
      "seatClass": "ECONOMY",
      "status": "AVAILABLE",
      "extraPrice": 0,
      "createdAt": "2023-04-15T12:30:45",
      "updatedAt": "2023-04-15T12:30:45"
    },
    {
      "id": 11,
      "seatNumber": "12B",
      "airplane": {
        "id": 1,
        "model": "Boeing 787-9",
        "registrationNumber": "VN-A861"
      },
      "seatClass": "ECONOMY",
      "status": "AVAILABLE",
      "extraPrice": 0,
      "createdAt": "2023-04-15T12:30:45",
      "updatedAt": "2023-04-15T12:30:45"
    }
  ]
  ```

### Payment API

#### Tạo giao dịch thanh toán
- **Endpoint**: `POST /api/v1/payment-transactions/`
- **Request**:
  ```json
  {
    "bookingId": 1,
    "paymentMethodId": 1,
    "amount": 3000000,
    "status": "PENDING"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "transactionReference": "TXN1",
    "booking": {
      "id": 1,
      "bookingReference": "BK12345AB",
      "status": "PENDING"
    },
    "paymentMethod": {
      "id": 1,
      "name": "Credit Card",
      "type": "VISA/Mastercard payment gateway"
    },
    "amount": 3000000,
    "currency": "VND",
    "status": "PENDING",
    "paymentDate": "2023-04-15T15:45:30",
    "paymentDetails": "Payment for booking BK12345AB",
    "createdAt": "2023-04-15T15:45:30",
    "updatedAt": "2023-04-15T15:45:30"
  }
  ```

#### Cập nhật trạng thái thanh toán
- **Endpoint**: `PUT /api/v1/payment-transactions/{id}/status?status=COMPLETED`
- **Response**:
  ```json
  "Payment status updated successfully"
  ```

#### Lấy giao dịch thanh toán theo booking
- **Endpoint**: `GET /api/v1/payment-transactions/booking/{bookingId}`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "transactionReference": "TXN1",
      "booking": {
        "id": 1,
        "bookingReference": "BK12345AB",
        "status": "CONFIRMED"
      },
      "paymentMethod": {
        "id": 1,
        "name": "Credit Card",
        "type": "VISA/Mastercard payment gateway"
      },
      "amount": 3000000,
      "currency": "VND",
      "status": "COMPLETED",
      "paymentDate": "2023-04-15T15:45:30",
      "paymentDetails": "Payment for booking BK12345AB",
      "createdAt": "2023-04-15T15:45:30",
      "updatedAt": "2023-04-15T15:50:10"
    }
  ]
  ``` 