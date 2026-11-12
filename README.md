# Garena Tool Pro – Android starter

**Cấu hình:** Android / đăng nhập thủ công / giao diện gaming đỏ-đen.

## Tính năng trong bản mẫu
- Màn hình theo dõi Chung sức.
- Màn hình ghi chú sự kiện mới nhất.
- Màn hình theo dõi sự kiện 400 QH.
- Ghi chú tên tài khoản/biệt danh, không lưu mật khẩu hoặc OTP.
- Mở trang tài khoản Garena chính thức.

## Build APK
1. Cài Android Studio và Android SDK Platform 35.
2. Mở thư mục dự án này trong Android Studio.
3. Chờ Gradle Sync.
4. Chọn **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. APK debug nằm tại `app/build/outputs/apk/debug/app-debug.apk`.

## Trạng thái
Đây là **bản khởi đầu giao diện**, chưa kết nối API sự kiện Liên Quân và chưa phải APK đã biên dịch/ký. Nội dung sự kiện cần được kiểm chứng qua nguồn chính thức. Ứng dụng không tự động farm, vượt CAPTCHA/OTP hoặc né giới hạn bảo mật.

## Auto Create Account (assisted)
- Generates suggested usernames and strong password drafts locally.
- Supports preparing 1–5 registration drafts per action.
- Copies the draft text and opens the official Garena account page.
- Registration, CAPTCHA, OTP and eligibility checks remain manual.
- Drafts are not saved by the app; store credentials only in a trusted password manager.
