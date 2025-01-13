# Grocery Stores Project Application

Ứng dụng **Grocery Stores Project** là một ứng dụng Android cho phép người dùng tìm kiếm, quản lý và mua sắm tại các cửa hàng tạp hóa trực tuyến. Dự án được phát triển với giao diện người dùng bằng Kotlin và backend sử dụng Spring Boot.

## 🔓 Mô tả dự án

Ứng dụng này cung cấp các tính năng chính như:
- Tìm kiếm và duyệt các sản phẩm tạp hóa.
- Quản lý giỏ hàng và đặt hàng trực tuyến.
- Đăng nhập/Đăng ký tài khoản.
- Theo dõi lịch sử giao dịch.

## 🛠️ Công nghệ sử dụng

### Frontend
- **Ngôn ngữ**: Kotlin
- **Nền tảng**: Android SDK
- **Thư viện**:
  - Jetpack Compose / XML (tùy vào cách bạn triển khai giao diện).
  - Retrofit để giao tiếp với backend.
  - Glide hoặc Coil để tải hình ảnh.

### Backend
- **Ngôn ngữ**: Java / Kotlin (Spring Boot)
- **Framework**: Spring Boot
- **Cơ sở dữ liệu**: MySQL

## 📦 Hướng dẫn cài đặt

### 1. Backend
1. Cài đặt [Java 11+](https://adoptium.net/), [Apache Tomcat 9.0](https://tomcat.apache.org/download-90.cgi) và [Maven](https://maven.apache.org/).
2. Clone repository:
   ```bash
   git clone https://github.com/your-repository/grocery-backend.git
   cd grocery-backend
   ```
3. Cấu hình file `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/grocery
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
4. Chạy dự án Spring Boot:
   ```bash
   mvn spring-boot:run
   ```

### 2. Android App
1. Mở dự án trong Android Studio.
2. Cấu hình URL API trong file `Constants.kt`:
   ```kotlin
   const val BASE_URL = "http://<your-backend-host>:8080/api/"
   ```
3. Build và chạy ứng dụng trên máy ảo hoặc thiết bị thật.

## 📚 Hướng dẫn sử dụng

1. **Đăng nhập/Đăng ký**: Người dùng cần đăng nhập để truy cập đầy đủ tính năng.
2. **Duyệt sản phẩm**: Tìm kiếm sản phẩm theo danh mục hoặc tên.
3. **Quản lý giỏ hàng**: Thêm, xóa hoặc cập nhật số lượng sản phẩm.
4. **Đặt hàng**: Xác nhận và theo dõi trạng thái đơn hàng.

## 💡 Đóng góp

Nếu bạn muốn đóng góp vào dự án, vui lòng:
1. Fork repository.
2. Tạo nhánh mới: `git checkout -b feature/your-feature-name`.
3. Commit thay đổi: `git commit -m "Add your feature"`.
4. Push nhánh của bạn: `git push origin feature/your-feature-name`.
5. Tạo Pull Request trên GitHub.

## 📄 Giấy phép

Dự án này sử dụng giấy phép [MIT](LICENSE).

## 📨 Liên hệ

Nếu bạn có câu hỏi hoặc góp ý, hãy liên hệ:
- **Email**: your-email@example.com
- **GitHub**: [your-github-profile](https://github.com/your-username)

